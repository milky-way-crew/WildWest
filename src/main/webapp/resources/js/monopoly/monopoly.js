/*jslint browser: true*/
/*global jQuery, $, WebSocket, BOARD, console, prompt, bootbox*/
var BOARD;

$(document).ready(

function() {
    "use strict";

    var log = null, 
        chat = {},
        MONO = {},
        ui = {};
    log = function(message) {
        console.log('[DEBUG] ' + message);
    };
    ui = {
        minOpacityToButtons: function() {
            $("#controls button").each(

            function(i, btn) {
                $(btn).animate({
                    "opacity": "0.5"
                });
            });
        },
        refreshButtons: function(buttons) {
            ui.minOpacityToButtons();
            $.each(buttons, function(btnName) {
                var opacityValue = buttons[btnName.toLowerCase()] ? "1" : "0.5";
                $('#' + btnName).animate({
                    "opacity": opacityValue
                }, 100);

                if(btnName === "buy" && buttons[btnName]) {
                    $('#buy').show(100);
                }
                if(btnName === "pay" && buttons[btnName]) {
                    $('#pay').show(100);
                }
            });
        },
        attachTooltip: function(element, message) {
            var $element = $(element);
            $element.attr('rel', 'tooltip');
            $element.attr('data-original-title', message);
            $element.tooltip();
        },
        clearTooltipsIn: function(area) {
            $(area).attr('data-original-title', '');
            $(area).tooltip('hide');
        },
        makeLabel: function(content, playerColor, color) {
            return '<span' + (color ? (' style="background-color:' + color + ';"') : '') 
                + ' class="label color-player-'+ BOARD.colorToIndex(playerColor) 
                + '">' + content + '</span>';
        }
    };
    chat = {
        $chat: null,
        init: function() {
            console.log('Initing chat');
            chat.$chat = $('#chat');
        },
        append: function(what) {
            chat.checkNewMessages();
            chat.$chat.prepend('<li>' + what + '</li>');
        },
        appendWithColor: function(what, color) {
            chat.checkNewMessages();
            chat.$chat.prepend('<li style="color:' + color + ';">' + what + "</li>");
        },
        checkNewMessages: function() {
            if (!$('a[href=#chat-tab]').parent().hasClass('active')) {
                var title = $('a[href=#chat-tab]').html(),
                    newTitle = 1;
                title = title.replace(new RegExp('\\D', 'g'),'');
                if (title.length > 0) {
                    newTitle = parseInt(title, 10) + 1;
                }
                $('a[href=#chat-tab]').html('Chat ' + ui.makeLabel(newTitle, '')   );
            }
        }
    };
    MONO = {
        config: {
            idGame: null,
            idUser: null,
            socketUrl: 'ws://' + location.hostname + ':8888/' || "ws://localhost:8888/",
            ajaxUrl: "mono-ajax",
            isCreator: false,
            money: 0,
            color: '',
            game_status: "",
            position: 1,
            price: 0,
            positions: { 'RED': 1, 'GREEN':1, 'BROWN':1, 'VIOLET':1 }
        },
        transport: {
            socket: null,
            send: function(type, json) {
                var jsonString = JSON.stringify({
                    'type': type,
                    'id': {
                        'game-id': MONO.config.idGame,
                        'user-id': MONO.config.idUser
                    },
                    'data': json
                });
                console.log('sending message: ' + jsonString);
                MONO.transport.socket.send(jsonString);
            },
            init: function() {
                $.ajax({
                    url: MONO.config.ajaxUrl,
                    type: 'post',
                    success: function(init_id) {
                        console.log('Inner id [idUser=', init_id.idUser, '] [idGame=', init_id.idGame, ']');
                        MONO.config.idUser = init_id.idUser;
                        MONO.config.idGame = init_id.idGame;

                        console.log('Initing web-sockets');
                        MONO.transport.socket = new WebSocket(MONO.config.socketUrl);
                        MONO.transport.socket.onopen = MONO.events.onConnectEstablished;
                        MONO.transport.socket.onclose = MONO.events.onConnectClosed;
                        MONO.transport.socket.onmessage = MONO.events.onMessage;
                    }
                });
            }
        },
        events: {
            onConnectEstablished: function() {
                console.log("Connection opened, bind-websocket request");
                MONO.transport.send('bind-websocket', {});
                MONO.transport.send('init', {});
            },
            onConnectClosed: function() {
                console.log("Connection closed");
            },
            onMessage: function(event) {
                console.log("Received message: ", event.data);
                var json;
                try {
                    json = $.parseJSON(event.data);
                } catch(err) {
                    console.error('Not object Received', err);
                    return;
                }
                if(typeof MONO.events.handle[json.type] === 'undefined') {
                    console.error('No handlers for ' + json.type + ' defined');
                } else {
                    MONO.events.handle[json.type](json);
                }
            },
            handle: {
                'undefined': function(json) {
                    console.log('Unknown response');
                },
                'roll': function(json) {
                    console.log('[roll] event');
                    var dice1 = json.dice1,
                        dice2 = json.dice2,
                        offset = parseInt(dice1, 10) + parseInt(dice2, 10),
                        buttons = json.game_state.buttons,
                        color = json.game_state.player,
                        money = json.game_state.player_money,
                        message = json.game_state.messages,
                        d1 = json.game_state.dice1 || 0, 
                        d2 = json.game_state.dice2 || 0;
                    // Think about go-card
                    log('Was money=' + MONO.config.money);
                    log('After move money=' + money);
                    MONO.config.money = money;
                    MONO.animate.money(color, money);

                    MONO.config.position = (json.was + offset) % 40; // FIXME: nosure
                    MONO.config.positions[color] = (json.was + offset) % 40;
                    log('position on board [was] -> ' + json.was);
                    log('position on board [now] -> ' + MONO.config.position);

                    chat.appendWithColor('>> ' + ui.makeLabel(color.toLowerCase(), color) 
                        + ' rolled [' + dice1 + ' : ' + dice2 
                        + '] and moving to ' 
                        + ui.makeLabel($('#cell' + MONO.config.position + ' .town-title').html(), '') 
                        + ' on position #' + MONO.config.position
                    ,'#08C');

                    log('Starting animation of roll event');
                    if (typeof dice1 !== 'undefined' && typeof dice2 !== 'undefined') {
                        BOARD.rollDice(dice1, dice2);
                    } 
                    if (json.move && json.move === true) {
                        MONO.animate.move(color, dice1, dice2, json.was);
                        // if chance or so on
                        if (typeof json.game_state.dice1 !== 'undefined') {
                            log('bonus moving, chance, jail, etc');
                            MONO.config.position = (MONO.config.position + d1 + d2) % 40; // FIXME: nosure
                            MONO.config.positions[color] = (MONO.config.positions[color] + d1 + d2) % 40;
                            BOARD.animate.goTo(color, json.game_state.was, d1 + d2);
                            if (d1 > 0 && d2 > 0) {
                                BOARD.rollDice(d1, d2);
                            }
                        }
                    }
                    if (json.game_state && json.game_state.owner) {
                        MONO.animate.money(json.game_state.owner, json.game_state.owner_money);
                    }
                    ui.clearTooltipsIn('#game-table .cell[rel=tooltip]');
                    // this player moves
                    if(MONO.config.color === color) {
                        ui.refreshButtons(buttons);
                    }
                    if(message) {
                        chat.append("server: " + message);
                        ui.attachTooltip('#cell' + MONO.config.position, message);
                        $('#cell' + MONO.config.position).tooltip('show');
                    }
                },
                'buy': function(json) {
                    var cellTitle = $('#cell' + MONO.config.position + ' .town-title').html();

                    chat.appendWithColor('>> Player <span class="label label-info color-player-' 
                        + BOARD.colorToIndex(json.player) 
                        +'">' + json.player.toLowerCase() + '</span>'
                        + ' bought '+ ui.makeLabel(cellTitle, '') 
                        +' on cell#' + MONO.config.position, '#08C');

                    if (json.player === MONO.config.color) {
                        ui.refreshButtons(json.game_state.buttons);
                    }
                    MONO.animate.buy(json.player, MONO.config.position);
                    MONO.animate.money(json.player, json.player_money);
                },
                'pay': function(json) {
                    log('Player= ' + json.player + ' payed money for go out from jail');
                    MONO.animate.pay(json.player, json.player_money);
                },
                'handler': function(json, draw, animate, list) {
                    var pos, buttons, onlyNumbers;
                    buttons = json.game_state.buttons;
                    MONO.animate.money(json.player, json.player_money);
                    if(json.player === MONO.config.color) {
                        ui.refreshButtons(buttons);
                    }
                    if(json.position && json.player) {
                        draw(json.position, json.player);
                    }
                    if(json.player === MONO.config.color && list) {
                        onlyNumbers = $.map(Object.keys(list), function(e) {
                            return parseInt(e, 10);
                        });
                        animate(json.player, onlyNumbers, list);
                        if(json.message) {
                            chat.append("server: " + json.message);
                        }
                    }
                },
                'mortage': function(json) {
                    console.log('[mortage] event');
                    MONO.events.handle.handler(json, BOARD.draw.mortage, MONO.animate.mortage, json.mortage_list);
                },
                'unmortage': function(json) {
                    console.log('[unmortage] event');
                    MONO.events.handle.handler(json, BOARD.draw.unmortage, MONO.animate.unmortage, json.unmortage_list);
                },
                'build': function(json) {
                    console.log('[build] event');
                    MONO.events.handle.handler(json, BOARD.draw.build, MONO.animate.build, json.build_list);
                },
                'sell': function(json) {
                    console.log('[sell] event');
                    MONO.events.handle.handler(json, BOARD.draw.sell, MONO.animate.sell, json.sell_list);
                },
                'auction': function(json) {
                    console.log('[auction] event');
                    var auctionImage, receivedImage, colorClass;
                    $('#myTab a:last').tab('show');

                    if (json.card) {
                        $('.lot-name').html('card #' + json.card);
                        auctionImage = $('#auction-tab img').attr('src');
                        receivedImage = $('#cell' + json.card + ' .town-image img').attr('src');
                        if (auctionImage !== receivedImage) {
                            $('#auction-tab img').attr('src', receivedImage);
                        }
                    }
                    if (json.seconds_left) {
                        $('.lot-name+.badge').html(json.seconds_left + 's');
                    }
                    if (json.game_state) {
                        if (json.game_state.onwner) {
                            BOARD.draw.updateMoney(json.game_state.onwner, json.game_state.onwner_money);
                        }
                        if (json.game_state.buttons) {
                            ui.refreshButtons(json.game_state.buttons);
                        }                        
                    }
                    if (json.invoker) {
                        $('#auction-tab .invoker').html(json.invoker);
                        $('#auction-tab .invoker').addClass('color-player-' + BOARD.colorToIndex(json.invoker));
                    }

                    if (json.rates) {
                        $('#auction-tab .rates .label.color-player-' + BOARD.colorToIndex(json.rates.player)).html(json.rates.rates + '$');
                    }

                    if (json.highest && !$.isEmptyObject(json.highest)) {
                        colorClass = 'color-player-' + BOARD.CONST.COLOR_TO_NUMBER[json.highest.who];

                        $.each([1,2,3,4], function(i, number) {
                            $('.label.price-caller').removeClass('color-player-' + number);
                            $('.label.price').removeClass('color-player-' + number);
                        });

                        $('.label.price-caller').addClass(colorClass);
                        $('.label.price').addClass(colorClass); 

                        $('.label.price-caller').html(json.highest.who);
                        $('.label.price').html(json.highest.money + '$');                       
                    }
                    if (json.auction_status) {
                        $('.lot-name+.badge').html('Sold to: ' + $('.label.price-caller').html());
                        // reset auction
                        $('#auction-tab .rates small').each(function() {
                            $(this).html('0$');
                        });
                        $('.label.price').html('0$');
                        $('.price-caller').html('-');
                        $('.invoker').html('-');
                        BOARD.draw.updateMoney(json.auction_creator, json.auction_creator_money);
                    }
                    if (json.player) {
                        $('.label.price-caller').html(json.player);
                    }
                },
                'init': function(json) {
                    console.log('[init] event');
                    MONO.config.color = json.color;
                    MONO.config.money = json.money;
                    MONO.config.isCreator = json.isCreator;
                    $.each(json.players, function(color, stats) {
                        $('#player' + BOARD.colorToIndex(color)).show(300);
                        BOARD.animate.jump(color, stats.position);
                        MONO.animate.money(color, stats.money);
                        ui.attachTooltip('#money .label.color-player-' 
                            + BOARD.colorToIndex(color), 'nick-name : ' + stats.nick);
                    });
                    
                    if (json.buttons) {
                        ui.refreshButtons(json.buttons);
                    }

                    if(json.game_status && !json.game_status.game_started) {
                        if (MONO.config.isCreator) {
                            $('#start').show(300).css({'display':'inline-block'});
                        }
                        $('#ready').show(300).css({'display':'inline-block'});
                    } else if (json.game_status && json.game_status.game_started === true) {
                        $('#start').hide(300);
                        $('#ready').hide(300);
                    } 

                    if (!$.isEmptyObject(json.board)) {
                        $.each(json.board, function(pos, stats) {
                            BOARD.buy(stats.owner, stats.position);
                            if (stats.mortage) {
                                BOARD.draw.mortage(stats.position, stats.owner);
                            }
                        });                        
                    }
                },
                'chat': function(json) {
                    chat.append(json.message);
                },
                'logic': function(json) {
                    var playerIndex;
                    log('game-status:' + json.game_status);
                    MONO.config.game_status = json.game_status;
                    if(json.game_status === "start") {
                        $('#ready').hide(100);
                        $('#start').hide(100);
                        $('#roll').animate({"opacity":"1"}, 1000);
                        chat.appendWithColor('>> Sooo, game is started. Good-luck to all!', '#08A');
                    }
                    if (json.loser) {
                        chat.appendWithColor('>> Player' + ui.makeLabel(json.loser.toLowerCase(), json.loser)
                            + ' loose the game, so sad :(', '#08C');
                    } 
                    if (json.winner) {
                        chat.appendWithColor('>> And we have a winner! Its a ' 
                            + ui.makeLabel(json.winner.toLowerCase(), json.winner)
                            + ' Woohoo! :)', '#08C');
                    }
                    if (json.subType === 'connect') {
                        playerIndex = BOARD.colorToIndex(json.player);
                        BOARD.draw.updateMoney(json.player, json.money);
                        ui.attachTooltip('#money .label.color-player-' 
                            + playerIndex, 'nick-name: ' + json.nick);
                        $('#player' + playerIndex).show(300);
                        chat.appendWithColor('>> Whoa, someone with nick ' 
                            + ui.makeLabel(json.nick, json.player) 
                            + ' connected.', '#095');
                    }
                },
                'ready': function(json) {
                    chat.appendWithColor('>> Player <span class="label label-info color-player-' 
                        + BOARD.colorToIndex(json.player) 
                        +'">' + json.player.toLowerCase() + '</span>'
                        + ' is ready to start.', '#08C');
                },
                'turn': function(json) {
                    chat.appendWithColor('>> <span class="label label-info color-player-' 
                        + BOARD.colorToIndex(json.player) 
                        +'">' + json.player.toLowerCase() + '</span> hey its your turn.', '#08C');

                    $('button').animate({
                        "opacity": "0.5"
                    }, 100);

                    if(json.player === MONO.config.color) {
                        if (json.game_state && json.game_state.buttons) {
                            ui.refreshButtons(json.game_state.buttons);
                        } else {
                            $('#roll').animate({"opacity":"1"}, 1000);
                        }
                        $('#done').html('done');
                        $('#done').removeClass('wait');
                    } else {
                        $('#done').html(json.player + ' moving <img src="resources/img/board/busy.gif"/>');
                        $('#done').addClass('wait');
                    }
                }
            }
        },
        animate: {
            money: function(who, money) {
            	if (money) {
            		chat.appendWithColor('>> now <span class="label label-info color-player-' 
            				+ BOARD.colorToIndex(who) 
            				+'">' + who.toLowerCase() + '</span>'
            				+ ' money is - ' + ui.makeLabel(money + '$', '', '#095'), '#08C');
            		console.log('Setting money to', money, 'player=', who);
            		BOARD.draw.updateMoney(who, money);
            	}
            },
            move: function(who, d1, d2, was) {
                log('Animating [move]');
                BOARD.animate.move(who, d1, d2, was);
            },
            buy: function(who, position) {
                log('Animating [buy]');
                BOARD.buy(who, position);
            },
            pay: function(player, money) {
                log('Animating ~not supported~ [pay]');
                MONO.animate.money(player, money);
            },
            mortage: function(who, positions, messages) {
                log('*Animating [mortage]');
                BOARD.mortage(positions, who, messages);
            },
            unmortage: function(who, positions, messages) {
                log('*Animating [unmortage]');
                BOARD.unmortage(positions, who, messages);
            },
            sell: function(who, positions, messages) {
                log('*Animating [sell]');
                BOARD.sell(positions, who, messages);
            },
            build: function(who, positions, messages) {
                log('*Animating [build]');
                BOARD.build(positions, who, messages);
            }
        },
        init: function() {
            MONO.transport.init();
            chat.init();

            var hideAfterClick = {
                start: true,
                ready: true
                // done : true
            },
                initButton = function() {
                    $(this).click(function() {
                        var id = $(this).attr('id');
                        console.log('sending [', id, '] message to server');
                        if(hideAfterClick[id]) {
                            $(this).hide(100);
                        }
                        MONO.transport.send(id, {});
                    });
                },
                sendChatMessage = function() {
                    var msg = $('#usermsg').val();
                    if (msg.length > 0) {
                        $('#usermsg').val('');
                        MONO.transport.send('chat', {
                            "message": msg
                        });   
                    }
                },
                miniHover = function() { 
                    var id = parseInt($(this).attr('id').match(/\d+$/)[0], 10);
                    $('#cell' + id  +' .tip').toggleClass('hover'); 
                };
            $('#controls button').each(initButton);
            $('#controls2 button').each(initButton);
            $('#menu a').each(initButton);

            $('#buy').click(function() {
                $(this).animate({"opacity": "0.5"}, 1000);
            });
            $('#pay').click(function() {
                $(this).animate({"opacity": "0.5"}, 1000);
            });

            $('#up10').click(function() {
                var price = $('#auction-tab .rates .label.color-player-' 
                    + BOARD.CONST.COLOR_TO_NUMBER[MONO.config.color]).html(),
                    newPrice = parseInt(price, 10) + 10;
                if (newPrice) {
                    MONO.transport.send('auction', {
                        "price": newPrice
                    });                    
                }
            });

            $('#up50').click(function() {
                var price = $('#auction-tab .rates .label.color-player-' 
                    + BOARD.CONST.COLOR_TO_NUMBER[MONO.config.color]).html(),
                    newPrice = parseInt(price, 10) + 50;
                if (newPrice) {
                    MONO.transport.send('auction', {
                        "price": newPrice
                    });                    
                }
            });

            $('#send').click(sendChatMessage);
            $("#usermsg").keypress(function(event) {
                if ( event.which === 13 ) {
                    sendChatMessage();
                    event.preventDefault();
                }
            });

            $('a[href=#chat-tab]').click(function() {
                $(this).html('Chat');
            });

            $(window).resize(function() {
                $.each(MONO.config.positions, function(color, pos) {
                    BOARD.animate.jump(color, pos);
                });
            });

            // $('.cell .tip').click(function() { 
            //     $(this).toggleClass('hover');
            // });

            $('.cell .tip').unbind('click');
            $('.cell .tip').hover(function() { 
                $(this).toggleClass('hover');
            });

            $('.mini').hover(miniHover, miniHover);
        }
    };

    BOARD = {
        CONST: {
            DURATION: 500,
            COLOR_TO_NUMBER: {
                'BROWN': '1',
                'GREEN': '2',
                'RED': '3',
                'VIOLET': '4'
            },
            OFFSET_MAP: {
                BROWN: {
                    bottom: function(offset) {
                        offset.top += 35;
                    },
                    left: function(offset) {
                        offset.left += 35;
                    },
                    up: function(offset) {
                        offset.top += 35;
                    },
                    right: function(offset) {
                        offset.left += 35;
                    },
                    corner: function(offset) {
                        offset.top += 30;
                        offset.left += 30;
                    }
                },
                GREEN: {
                    bottom: function(offset) {
                        offset.top += 83;
                        offset.left += 60;
                    },
                    left: function(offset) {
                        offset.top += 45;
                        offset.left += 124;
                    },
                    up: function(offset) {
                        offset.top += 85;
                        offset.left += 60;
                    },
                    right: function(offset) {
                        offset.top += 45;
                        offset.left += 120;
                    },
                    corner: function(offset) {
                        offset.top += 80;
                        offset.left += 80;
                    }
                },
                RED: {
                    bottom: function(offset) {
                        offset.top += 30;
                        offset.left += 80;
                    },
                    left: function(offset) {
                        offset.left += 35;
                    },
                    up: function(offset) {
                        offset.top += 35;
                    },
                    right: function(offset) {
                        offset.left += 35;
                    },
                    corner: function(offset) {
                        offset.top += 30;
                        offset.left += 80;
                    }
                },
                VIOLET: {
                    bottom: function(offset) {
                        offset.top += 83;
                        offset.left += 60;
                    },
                    left: function(offset) {
                        offset.top += 45;
                        offset.left += 124;
                    },
                    up: function(offset) {
                        offset.top += 85;
                        offset.left += 60;
                    },
                    right: function(offset) {
                        offset.top += 45;
                        offset.left += 120;
                    },
                    corner: function(offset) {
                        offset.top += 80;
                        offset.left += 30;
                    }
                }
                // to be continued...
            }
        },
        animate: {
            normalizeOffset: function(who, cellId) {
                var offset = $('#cell' + cellId).offset();

                if(cellId > 1 && cellId < 11) {
                    BOARD.CONST.OFFSET_MAP[who].bottom(offset);
                } else if(cellId > 11 && cellId < 21) {
                    BOARD.CONST.OFFSET_MAP[who].left(offset);
                } else if(cellId > 21 && cellId < 31) {
                    BOARD.CONST.OFFSET_MAP[who].up(offset);
                } else if(cellId > 31 && cellId <= 40) {
                    BOARD.CONST.OFFSET_MAP[who].right(offset);
                } else if(cellId % 10 === 1) {
                    BOARD.CONST.OFFSET_MAP[who].corner(offset);
                } else if (cellId === 0) {
                    offset = $('#cell40').offset();
                    BOARD.CONST.OFFSET_MAP[who].right(offset);
                } else {
                    console.error('Unknown cell position, cant move there ->', cellId);
                }

                return offset;
            },
            goTo: function(who, from, offset, duration) {
                var $player = $('#player' + BOARD.CONST.COLOR_TO_NUMBER[who]);

                while(offset >= 0) {
                    $player.animate(BOARD.animate.normalizeOffset(who, from % 40), duration);
                    offset -= 1;
                    from += 1;
                }
            },
            move: function(playerColor, dice1, dice2, startCell) {
                var offset = dice1 + dice2;
                BOARD.animate.goTo(playerColor, startCell, offset, BOARD.CONST.DURATION);
            },
            jump: function(whoColor,  whereCell) {
                // instant
                BOARD.animate.goTo(whoColor, whereCell, 0, 0);
            }
        },
        houseManipulation: {
            buildHouse: function(houseCell) {
                var $houseCell = $("#cell" + houseCell + ' .house img'),
                    newHouse = "resources/img/board/emptyhouse.png";
                // '(\\d+)\\..{2,4}$' regex for cath index of house, TODO rewrite
                if($houseCell.attr('src') === "resources/img/board/emptyhouse.png") {
                    newHouse = 'resources/img/board/house1.png';
                } else if($houseCell.attr('src') === "resources/img/board/house1.png") {
                    newHouse = 'resources/img/board/house2.png';
                } else if($houseCell.attr('src') === "resources/img/board/house2.png") {
                    newHouse = 'resources/img/board/house3.png';
                } else {
                    newHouse = 'resources/img/board/big_hotel.png';
                }
                $houseCell.fadeOut(300, function() {
                    $houseCell.attr('src', newHouse);
                    $houseCell.fadeIn(300);
                });
            },
            sellHouse: function(houseCell) {
                var $houseCell = $("#cell" + houseCell + ' .house img'),
                    newHouse = "resources/img/board/emptyhouse.png";
                if($houseCell.attr('src') === "resources/img/board/big_hotel.png") {
                    newHouse = 'resources/img/board/house3.png';
                } else if($houseCell.attr('src') === "resources/img/board/house3.png") {
                    newHouse = 'resources/img/board/house2.png';
                } else if($houseCell.attr('src') === "resources/img/board/house2.png") {
                    newHouse = 'resources/img/board/house1.png';
                } else if($houseCell.attr('src') === "resources/img/board/house1.png") {
                    newHouse = "resources/img/board/emptyhouse.png";
                }
                $houseCell.fadeOut(300, function() {
                    $houseCell.attr('src', newHouse);
                    $houseCell.fadeIn(300);
                });
            }
        },
        sellAll: function(player, cell) {
            var houseCell = "#cell" + cell + " .house img",
                ownerCell = "#cell" + cell + " .owner",
                playerNumber = BOARD.colorToIndex(player);

            if($(houseCell).attr('src') === "resources/img/board/big_hotel.png") {
                $(houseCell).attr('src', 'resources/img/board/house3.png');
            } else if($(houseCell).attr('src') === "resources/img/board/house3.png") {
                $(houseCell).attr('src', 'resources/img/board/house2.png');
            } else if($(houseCell).attr('src') === "resources/img/board/house2.png") {
                $(houseCell).attr('src', 'resources/img/board/house1.png');
            } else if($(houseCell).attr('src') === "resources/img/board/house1.png") {
                $(houseCell).attr('src', "resources/img/board/emptyhouse.png");
            } else {
                $(ownerCell).removeClass("color-player-" + playerNumber);
                $('#miniCell' + cell + ' img').hide(300, function() {
                    $('#miniCell' + cell).html('<img/>');
                });
            }
        },
        hightlightCells: function(cellArr, player, cls) {
            $.each(cellArr, function(i, e) {
                $('#miniCell' + e).addClass(cls);
            });
        },
        handler: function(player, messages, messageType, visibleClass) {
            $('#myTab [href=#minimap-tab]').tab('show');
            BOARD.hightlightCells(Object.keys(messages), player, visibleClass);

            $.each(messages, function(index, message) {
                var id = '#miniCell' + index,
                    $cell = $(id);
                $cell.unbind('click');
                ui.attachTooltip(id, message);

                $cell.click(function() {
                    if($(this).hasClass(visibleClass)) {
                        var $selected = $(this),
                            pos = parseInt($selected.attr('id').match(/\d+$/)[0], 10);

                        MONO.transport.send(messageType, {
                            position: pos
                        });

                        $('.' + visibleClass).filter(function(i, e) {
                            return $selected.attr('id') !== $(e).attr('id');
                        }).each(function(i, e) {
                            $(e).removeClass(visibleClass);
                        });

                        // we don't need tooltips anymore
                        $(this).tooltip('hide');
                        ui.clearTooltipsIn('#info [rel=tooltip]');
                    }
                });
            });
        },
        mortage: function(cellArr, player, messages) {
            BOARD.handler(player, messages, 'mortage', 'visibleCell');
        },
        unmortage: function(cellArr, player, messages) {
            BOARD.handler(player, messages, 'unmortage', 'unmortageSelected');
        },
        build: function(cellArr, player, messages) {
            BOARD.handler(player, messages, 'build', 'visibleCell');
        },
        sell: function(cellArr, player, messages) {
            BOARD.handler(player, messages, 'sell', 'visibleCell');
        },
        rollDice: function(dice1, dice2) {
            $("#diceImg1").attr("src", "resources/img/board/die" + dice1 + ".gif");
            $("#diceImg2").attr("src", "resources/img/board/die" + dice2 + ".gif");
        },
        buy: function(player, cellId) {
            var playerNumber = BOARD.colorToIndex(player), 
                $bigCell = $('#cell' + cellId), 
                $miniCell = $('#miniCell' + cellId),
                notifier;
            // change color of big cell
            $bigCell.find('.owner').addClass("color-player-" + playerNumber, 1000);
            // add img to mini-map
            $miniCell.find('img').attr('src', 'resources/img/board/miniPlayer' + playerNumber + '.png');
            // change owner if popup-tip 
            // TODO: Implement getnick by color
            $bigCell.find('.tip-header .tip-owner').html('bought by: ' + player);
            // show mortage and build buttons
            if (MONO.config.color === player) {
                $bigCell.find('.tip-controls').show(100);
                $bigCell.find('.tip-controls .mortage').unbind('click');
                $bigCell.find('.tip-controls .build').unbind('click');
                notifier = function() {
                    var pos = parseInt($bigCell.attr('id').match(/\d+$/)[0], 10);

                    MONO.transport.send($(this).html(), {
                        position: pos
                    });
                };
                $bigCell.find('.tip-controls .mortage').click(notifier);
                $bigCell.find('.tip-controls .build').click(notifier);
            }
        },
        colorToIndex: function(player) {
            return BOARD.CONST.COLOR_TO_NUMBER[player];
        },
        draw: {
            mortage: function(cell, player) {
                var index = BOARD.colorToIndex(player), 
                    mortageBtn = $('#cell' + cell + ' .tip-controls .mortage');
                $('#miniCell' + cell).find('img').attr('src', '');
                $('#miniCell' + cell).addClass('setMortageCell');
                // for player who owner of mortaged property change buttons
                mortageBtn.removeClass('btn-info');
                mortageBtn.addClass('btn-success');
                mortageBtn.html('unmortage');
                // change outline
                BOARD.draw.toggleOutline(cell, 'sketch');
            },
            unmortage: function(cell, player) {
                var index = BOARD.colorToIndex(player),
                            unmortageBtn = $('#cell' + cell + ' .tip-controls .mortage');
                $('#miniCell' + cell).find('img').attr('src', 'resources/img/board/miniPlayer' + index + '.png');
                $('#miniCell' + cell).removeClass('setMortageCell');

                unmortageBtn.removeClass('btn-success');
                unmortageBtn.addClass('btn-info');
                unmortageBtn.html('mortage');
                // change outline
                BOARD.draw.toggleOutline(cell, 'full');
            },
            build: function(cell) {
                BOARD.houseManipulation.buildHouse(cell);
            },
            sell: function(cell, player) {
                BOARD.sellAll(player, cell);
            },
            updateMoney: function(who, money) {
                if (money) {
                    $("#money-player-" + BOARD.colorToIndex(who))
                        .fadeOut(300)
                        .html(money + "$")
                        .css({'display':'inline'})
                        .fadeIn(300)
                    ;                    
                }
            },
            toggleOutline: function(cell, mode) {
                // change outline of img in cell & tip
                var $imgTown = $('#cell' + cell + ' .town-image img'),
                    $imgTip = $('#cell' + cell + ' span img'),
                    path = $imgTown.attr('src');

                console.log('changing outline');
                if (mode === 'sketch') {
                    if (path.indexOf('_outline.svg') === -1) {
                        path = path.replace('.svg', '_outline.svg');
                    }
                } else if (mode === 'full') {                      
                    path = path.replace('_outline.svg', '.svg');
                } else {
                    console.log('Unknown outline mode, aborting');
                    return;
                }
                $imgTown.fadeOut(300, function() {
                    $imgTown.attr('src', path).fadeIn(300);
                    $imgTip.attr('src', path).fadeIn(300);
                });
            }
        },
        cancelAllEffects: function() {
            $('.visibleCell').removeClass('visibleCell');
            $('.unmortageSelect').removeClass('unmortageSelect');
            $('#info td').unbind('click');
        },
        init: function() {
            console.log('Init -> Board');
            $.each(['BROWN', 'GREEN', 'RED', 'VIOLET'], function(i, who) {
                BOARD.animate.goTo(who, 1, 0);
            });
            $(window).bind('beforeunload', function() {    
                var msg = 'disconnected for some reasons.';
                MONO.transport.send('chat', {
                    "message": msg
                });   
            }); 
            // $('#myTab [href=#chat-tab]').tab('show');
        }
    };
    BOARD.init();
    MONO.init();
});