/*jslint browser: true*/
/*global jQuery, $, WebSocket, BOARD, console, prompt, bootbox*/
var BOARD = {};

$(document).ready(

function() {
    "use strict";

    var log = function(message) {
            console.log('[DEBUG] ' + message);
        },
        chat = {},
        MONO = {},
        ui = {};
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
                var opacityValue = buttons[btnName] ? "1" : "0.5";
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
        }
    };
    chat = {
        $chat: null,
        init: function() {
            console.log('Initing chat');
            chat.$chat = $('#chat');
        },
        append: function(what) {
            chat.$chat.prepend('<li>' + what + '</li>');
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
            position: 0,
            price: 0
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
                try {
                    var json = $.parseJSON(event.data);
                    if(typeof MONO.events.handle[json.type] === 'undefined') {
                        console.error('No handlers for ' + json.type + ' defined');
                    } else {
                        MONO.events.handle[json.type](json);
                    }
                } catch(err) {
                    console.error('Not object Received');
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
                        message = json.game_state.messages;
                    // Think about go-card
                    log('Was money=' + MONO.config.money);
                    log('After move money=' + money);
                    MONO.config.money = money;
                    MONO.animate.money(color, money);

                    MONO.config.position = (json.was + offset) % 40;
                    log('position on board [was] -> ' + json.was);
                    log('position on board [now] -> ' + MONO.config.position);

                    log('Starting animation of roll event');
                    BOARD.rollDice(dice1, dice2);
                    MONO.animate.move(color, dice1, dice2, json.was);
                    ui.clearTooltipsIn('#board [rel=tooltip]');

                    // this player moves
                    if(MONO.config.color === color) {
                        ui.refreshButtons(buttons);
                        if(message) {
                            chat.append("server: " + message);
                            ui.attachTooltip('#cell' + MONO.config.position, message);

                            // $('#cell' + MONO.config.position).tooltip({'placement' : 'bottom'});
                            // $('#cell' + MONO.config.position).tooltip('destroy');
                            $('#cell' + MONO.config.position).tooltip('show');
                        }
                        // experimental feature
                        $("#accordion").accordion({
                            active: BOARD.colorToIndex(color) - 1
                        });
                    }

                    // chat.append("player[" + color + "] is on cell: " + json.cell.name);
                },
                'buy': function(json) {
                    var msg = 'player= ' + json.player + ' bought cell with position= ' + MONO.config.position + ' now player money= ' + json.player_money;
                    chat.append(msg);
                    log(msg);
                    MONO.animate.buy(json.player, MONO.config.position);
                    MONO.animate.money(json.player, json.player_money);
                },
                'pay': function(json) {
                    log('Player= ' + json.player + ' payed money for go out from jail');
                    MONO.animate.pay(json.player, json.player_money);
                },
                'handler': function(json, draw, animate, list) {
                    var pos, buttons;
                    buttons = json.game_state.buttons;
                    MONO.animate.money(json.player, json.player_money);
                    if(json.player === MONO.config.color) {
                        ui.refreshButtons(buttons);
                    }
                    if(json.position && json.player) {
                        draw(json.position, json.player);
                    }
                    if(json.player === MONO.config.color && list) {
                        var onlyNumbers = $.map(Object.keys(list), function(e) {
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
                    var price;
                    price = parseInt(prompt('set price'));
                    if(price) {
                        MONO.transport.send('auction', {
                            price: price
                        });
                    }
                },
                'init': function(json) {
                    console.log('[init] event');
                    MONO.config.color = json.color;
                    MONO.config.money = json.money;
                    MONO.config.isCreator = json.isCreator;

                    MONO.animate.money(json.color, json.money);

                    if(MONO.config.isCreator) {
                        $('#start').show(100);
                    } else {
                        $('#start').hide(100);
                    }
                },
                'chat': function(json) {
                    chat.append(json.message);
                },
                'logic': function(json) {
                    log('game-status:' + json.game_status);
                    MONO.config.game_status = json.game_status;
                    if(json.game_status === "start") {
                        $('#ready').hide(100);
                        $('#start').hide(100);
                    }
                },
                'ready': function(json) {
                    chat.append(json.player + ' is ready=' + json.ready);
                },
                'turn': function(json) {
                    chat.append(json.player + ' turn=true');
                    $('button').animate({
                        "opacity": "0.5"
                    }, 100);
                    if(json.player === MONO.config.color) {
                        $('#roll').animate({
                            "opacity": "1"
                        }, 100);
                    }
                }
            }
        },
        animate: {
            money: function(who, money) {
                console.log('Setting money to', money, 'player=', who);
                BOARD.draw.updateMoney(who, money);
            },
            move: function(who, d1, d2, was) {
                log('Animating [move]');
                console.log('Moving to: ' + (d1 + d2));
                chat.append('Moving ' + who + ' to offset: ' + (d1 + d2));
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
            // each player to first cell

            var hideAfterClick = {
                pay: true,
                buy: true,
                start: true
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
                };
            $('#controls button').each(initButton);
            $('#menu a').each(initButton);
        }
    };

    BOARD = {
        CONST: {
            DURATION: 200,
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
                        offset.left += 80;
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
                } else if(cellId % 10 == 1) {
                    BOARD.CONST.OFFSET_MAP[who].corner(offset);
                } else {
                    console.error('Unknown cell position, cant move there.');
                }

                return offset;
            },
            goTo: function(who, from, offset) {
                var $player = $('#player' + BOARD.CONST.COLOR_TO_NUMBER[who]);

                while(offset-- >= 0) {
                    $player.animate(BOARD.animate.normalizeOffset(who, from++ % 41), BOARD.CONST.DURATION);
                }
            },
            move: function(playerColor, dice1, dice2, startCell) {
                var offset = dice1 + dice2;
                BOARD.animate.goTo(playerColor, startCell, offset);

            },
            jumpOnBoard: function(playerColor, dice1, dice2, startCell) {},
            setPlayerPosition: function(player, cell) {
                var dice = cell;
                BOARD.animate.jumpOnBoard(player, dice, 0, 1);
            }

        },
        houseManipulation: { /**** Build the house ****/
            buildHouse: function(houseCell) {
                $houseCell = $("#cell" + houseCell + ' img');
                if($houseCell.attr('src') == "resources/img/board/emptyhouse.png") {
                    $houseCell.attr('src', 'resources/img/board/house1.png');
                } else if($houseCell.attr('src') == "resources/img/board/house1.png") {
                    $houseCell.attr('src', 'resources/img/board/house2.png');
                } else if($houseCell.attr('src') == "resources/img/board/house2.png") {
                    $houseCell.attr('src', 'resources/img/board/house3.png');
                } else {
                    $houseCell.attr('src', 'resources/img/board/big_hotel.png');
                }
            },
            /**** Sell the house ****/
            sellHouse: function(houseCell) {
                $houseCell = $("#cell" + houseCell + ' img');
                if($houseCell.attr('src') == "resources/img/board/big_hotel.png") {
                    $houseCell.attr('src', 'resources/img/board/house3.png');
                } else if($houseCell.attr('src') == "resources/img/board/house3.png") {
                    $houseCell.attr('src', 'resources/img/board/house2.png');
                } else if($houseCell.attr('src') == "resources/img/board/house2.png") {
                    $houseCell.attr('src', 'resources/img/board/house1.png');
                } else if($houseCell.attr('src') == "resources/img/board/house1.png") {
                    $houseCell.attr('src', "resources/img/board/emptyhouse.png");
                }
            }
        },
        sellAll: function(player, cell) {
            var houseCell = "#house_cell" + cell;
            var ownerCell = "#ownerCell" + cell;
            var playerNumber = BOARD.colorToIndex(player);
            var miniCell = cell;

            if($(houseCell).attr('src') == "resources/img/board/big_hotel.png") {
                $(houseCell).attr('src', 'resources/img/board/house3.png');
            } else if($(houseCell).attr('src') == "resources/img/board/house3.png") {
                $(houseCell).attr('src', 'resources/img/board/house2.png');
            } else if($(houseCell).attr('src') == "resources/img/board/house2.png") {
                $(houseCell).attr('src', 'resources/img/board/house1.png');
            } else if($(houseCell).attr('src') == "resources/img/board/house1.png") {
                $(houseCell).attr('src', "resources/img/board/emptyhouse.png");
            } else {
                $(ownerCell).removeClass("color-player" + playerNumber);
                $(number + "MiniCell" + miniCell).removeClass("setMiniImagePlayer" + playerNumber);

            }
        },
        hightlightCells: function(cellArr, player, _class) {
            $.each(cellArr, function(i, e) {
                $('#miniCell' + e).addClass(_class);
            });
        },
        handler: function(player, messages, messageType, visibleClass) {
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
                            return $selected.attr('id') != $(e).attr('id');
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
                $bigCell = $('#cell' + cellId + ' .owner'), 
                $miniCell = $('#miniCell' + cellId);
            $bigCell.addClass("color-player-" + playerNumber, 1000);
            $miniCell.find('img').attr('src', 'resources/img/board/miniPlayer' + playerNumber + '.png');
        },
        colorToIndex: function(player) {
            return BOARD.CONST.COLOR_TO_NUMBER[player];
        },

        draw: {
            mortage: function(cell, player) {
                var index = BOARD.colorToIndex(player);
                $('#miniCell' + cell).addClass('setMortageCell').removeClass("setMiniImagePlayer" + index);
            },
            unmortage: function(cell, player) {
                var index = BOARD.colorToIndex(player);
                $('#miniCell' + cell).removeClass('setMortageCell').addClass("setMiniImagePlayer" + index);
            },
            build: function(cell) {
                BOARD.houseManipulation.buildHouse(cell);
            },
            sell: function(cell, player) {
                BOARD.sellAll(player, cell);

            },
            updateMoney: function(who, money) {
                $("#money-player-" + BOARD.colorToIndex(who)).html(money + "$");
            },
        },

        init: function() {
            console.log('Init -> Board');
            $.each(['BROWN', 'GREEN'], function(i, who) {
                BOARD.animate.goTo(who, 1, 0);
            });

            $(function() {
                $("#accordion").accordion();
            });
            $(function() {
                $("input[type=submit]").button().click(function(event) {
                    event.preventDefault();
                });
            });
        }
    };
    BOARD.init();
    MONO.init();
});
