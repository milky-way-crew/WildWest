/*jslint browser: true*/
/*global jQuery, $, WebSocket, BOARD, console, prompt, bootbox*/

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
            $("#controls .btn").each(

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
            price:0
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
                var json = JSON.parse(event.data);
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
                            active: BOARD.getPlayer(color) - 1
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
                    if (json.player === MONO.config.color) {
                        ui.refreshButtons(buttons);                        
                    } 
                    if (json.position && json.player) {
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
                'auction':function(json){
                	console.log('[auction] event');
                	var price;
                	price = parseInt(prompt('set price' ));
                	if (price) {
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
                    $('.btn').animate({
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
                BOARD.updateMoney(who, money);
            },
            move: function(who, d1, d2, was) {
                log('Animating [move]');
                console.log('Moving to: ' + (d1 + d2));
                chat.append('Moving ' + who + ' to offset: ' + (d1 + d2));
                BOARD.animate.stepOnBoard(who, d1, d2, was);
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
            $('#menu button').each(initButton);
        }
    };

    /*global MONO */

    var BOARD = {};

    BOARD = {

        CONSTANT: { /* Value of shift in MOVE methods */
            SHIFT: {
                LEFT_BIG_SHIFT: '-=70%',
                LEFT_SMALL_SHIFT: '-=51%',
                TOP_BIG_SHIFT: '-=550%',
                TOP_SMALL_SHIFT: '-=370%',
                RIGHT_BIG_SHIFT: '+=70%',
                RIGHT_SMALL_SHIFT: '+=51%',
                DOWN_BIG_SHIFT: '+=550%',
                DOWN_SMALL_SHIFT: '+=370%'
            },
            /* Value of shift in JUMP methods */
            JUMP: {
                LEFT_BIG_JUMP: '-=100%',
                LEFT_SMALL_JUMP: '-=74%',
                TOP_BIG_JUMP: '-=110%',
                TOP_SMALL_JUMP: '-=74%',
                RIGHT_BIG_JUMP: '+=100%',
                RIGHT_SMALL_JUMP: '+=74%',
                DOWN_BIG_JUMP: '+=110%',
                DOWN_SMALL_JUMP: '+=74%'
            },
            DURATION: 500,

            COLOR_PLAYER: {
                "BROWN": '#player1',
                "GREEN": '#player2',
                "RED": '#player3',
                "VIOLET": '#player4'

            },
            PLAYER_NUMBER: {
                "BROWN": '#first',
                "GREEN": '#second',
                "RED": '#third',
                "VIOLET": '#four'
            }

        },
        animate: { /**** Player move on the left or on the right ***/
            playerLeftAnimate: function(player, shift) {
                $(player).animate({
                    left: shift
                }, BOARD.CONSTANT.DURATION);
            },
            /**** Player move on the top or on the down ***/
            playerTopAnimate: function(player, shift) {
                $(player).animate({
                    top: shift
                }, BOARD.CONSTANT.DURATION);
            },
            /**** Player jump on the left or on the right ***/
            playerLeftJump: function(player, shift) {
                $(player).css({
                    left: shift
                });
            },
            /**** Player jump on the top or on the down ***/
            playerTopJump: function(player, shift) {
                $(player).css({
                    top: shift
                });
            },
            /* Move the player */
            stepOnBoard: function(playerColor, dice1, dice2, startCell) {
                var start = startCell;
                var DICE = dice1 + dice2;
                var player = BOARD.CONSTANT.COLOR_PLAYER[playerColor];
                for(var i = 0; i < DICE; i++) {
                    if(start >= 1 && start < 11) {
                        if(start == 1) {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_BIG_SHIFT);
                        } else if(start == 10) {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_BIG_SHIFT);
                        } else {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_SMALL_SHIFT);
                        }
                    } else if(start >= 11 && start < 21) {
                        if(start == 11) {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_BIG_SHIFT);
                        } else if(start == 20) {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_BIG_SHIFT);
                        } else {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_SMALL_SHIFT);
                        }

                    } else if(start >= 21 && start < 31) {
                        if(start == 21) {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_BIG_SHIFT);
                        } else if(start == 30) {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_BIG_SHIFT);
                        } else {
                            BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_SMALL_SHIFT);
                        }
                    } else {
                        if(start == 31) {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_BIG_SHIFT);
                        } else if(start == 40) {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_BIG_SHIFT);
                        } else {
                            BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_SMALL_SHIFT);
                        }
                    }

                    start++;
                    if(start > 40) start -= 40;
                }
            },

            /* JUMP the player */
            jumpOnBoard: function(playerColor, dice1, dice2, startCell) {
                var start = startCell;
                var DICE = dice1 + dice2;
                var player = BOARD.CONSTANT.COLOR_PLAYER[playerColor];

                for(var i = 0; i < DICE; i++) {
                    if(start >= 1 && start < 11) {
                        if(start == 1) {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
                        } else if(start == 10) {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
                        } else {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_SMALL_JUMP);
                        }
                    } else if(start >= 11 && start < 21) {
                        if(start == 11) {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
                        } else if(start == 20) {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
                        } else {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_SMALL_JUMP);
                        }

                    } else if(start >= 21 && start < 31) {
                        if(start == 21) {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
                        } else if(start == 30) {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
                        } else {
                            BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_SMALL_JUMP);
                        }
                    } else {
                        if(start == 31) {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
                        } else if(start == 40) {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
                        } else {
                            BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_SMALL_JUMP);
                        }
                    }

                    start++;
                    if(start > 40) start -= 40;
                }
            }

        },
        houseManipulation: { /**** Build the house ****/
            buildHouse: function(houseCell) {
                houseCell = "#house_cell" + houseCell;
                if($(houseCell).attr('src') == "resources/img/board/emptyhouse.png") {
                    $(houseCell).attr('src', 'resources/img/board/house1.png');
                } else if($(houseCell).attr('src') == "resources/img/board/house1.png") {
                    $(houseCell).attr('src', 'resources/img/board/house2.png');
                } else if($(houseCell).attr('src') == "resources/img/board/house2.png") {
                    $(houseCell).attr('src', 'resources/img/board/house3.png');
                } else {
                    $(houseCell).attr('src', 'resources/img/board/big_hotel.png');
                }
            },
            /**** Sell the house ****/
            sellHouse: function(houseCell) {
                houseCell = "#house_cell" + houseCell;
                if($(houseCell).attr('src') == "resources/img/board/big_hotel.png") {
                    $(houseCell).attr('src', 'resources/img/board/house3.png');
                } else if($(houseCell).attr('src') == "resources/img/board/house3.png") {
                    $(houseCell).attr('src', 'resources/img/board/house2.png');
                } else if($(houseCell).attr('src') == "resources/img/board/house2.png") {
                    $(houseCell).attr('src', 'resources/img/board/house1.png');
                } else if($(houseCell).attr('src') == "resources/img/board/house1.png") {
                    $(houseCell).attr('src', "resources/img/board/emptyhouse.png");
                }
            }
        },
        sellAll: function(player, cell) {
            var houseCell = "#house_cell" + cell;
            var ownerCell = "#ownerCell" + cell;
            var number = BOARD.getPlayerNumber(player);
            var playerNumber = BOARD.getPlayer(player);
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
                $(ownerCell).removeClass("setColorPlayer" + playerNumber);
                $(number + "MiniCell" + miniCell).removeClass("setMiniImagePlayer" + playerNumber);

            }
        },

        /* Mortage and unmartage manipolation*/
        cellManipulation: function(cellArr, player) {
            var number = BOARD.getPlayerNumber(player);
            for(var i = 0; i < cellArr.length; i++) {
                var cell = '' + number + "MiniCell" + cellArr[i];
                $(cell).addClass('visibleCell');
            }
        },

        unmortageSelect: function(cellArr, player) {
            var number = BOARD.getPlayerNumber(player);
            for(var i = 0; i < cellArr.length; i++) {
                var cell = number + "MiniCell" + cellArr[i];
                $(cell).addClass('unmortageSelected');
            }
        },
        mortage: function(cellArr, player, messages) {
            BOARD.cellManipulation(cellArr, player);

            var playerNumber = BOARD.getPlayerNumber(player);
            var playerOrder = BOARD.getPlayer(player);
            for(var i = 0; i < cellArr.length; i++) {
                var cell = "" + playerNumber + "MiniCell" + cellArr[i];
                $(cell).unbind('click');
                $(cell).bind('click');

                // WARNING: experimental feature
                // add cost to cell as tooltip title
                ui.attachTooltip(cell, messages[cellArr[i]]);

                $(cell).click(function() {
                    if($(cell).hasClass('visibleCell')) {
                        var $selected = $(this),
                            pos = parseInt($selected.attr('id').match(/\d+$/)[0], 10);

                        MONO.transport.send('mortage', {
                            position: pos
                        });

                        $('.visibleCell').filter(function(i, e) {
                            return $selected.attr('id') != $(e).attr('id');
                        }).each(function(i, e) {
                            $(e).removeClass('visibleCell');
                        });

                        // we don't need tooltips anymore
                        $(this).tooltip('hide');
                        ui.clearTooltipsIn('#playerInfo [rel=tooltip]');
                    }
                });

            }

        },
        unmortage: function(cellArr, player, messages) {
            BOARD.unmortageSelect(cellArr, player);
            var number = BOARD.getPlayerNumber(player);
            var playerOrder = BOARD.getPlayer(player);

            for(var i = 0; i < cellArr.length; i++) {
                var cell = number + "MiniCell" + cellArr[i];
                $(cell).unbind('click');
                $(cell).bind('click');

                ui.attachTooltip(cell, messages[cellArr[i]]);

                $(cell).click(function() {
                    if($(cell).hasClass('unmortageSelected')) {
                        var $selected = $(this),
                            pos = parseInt($selected.attr('id').match(/\d+$/)[0], 10);

                        MONO.transport.send('unmortage', {
                            position: pos
                        });

                        $('.unmortageSelected').filter(function(i, e) {
                            return $selected.attr('id') != $(e).attr('id');
                        }).each(function(i, e) {
                            $(e).removeClass('unmortageSelected');
                        });
                        
                        $(this).tooltip('hide');
                        ui.clearTooltipsIn('#playerInfo [rel=tooltip]');
                    }
                });

            }
        },

        build: function(cellArr, player, messages) {
            var number = BOARD.getPlayerNumber(player);
            BOARD.cellManipulation(cellArr, player);

            $.each(cellArr, function(i, e) {
                var cell = number + "MiniCell" + e;
                $(cell).unbind('click');
                $(cell).bind('click');
                ui.attachTooltip(cell, messages[cellArr[i]]);

                $(cell).click(function() {
                    if($(this).hasClass('visibleCell')) {
                        var $selected = $(this),
                            pos = parseInt($selected.attr('id').match(/\d+$/)[0], 10);

                        $(this).removeClass('visibleCell');

                        MONO.transport.send('build', {
                            position: pos
                        });

                        $('.visibleCell').filter(function(i, e) {
                            return $selected.attr('id') != $(e).attr('id');
                        }).each(function(i, e) {
                            $(e).removeClass('visibleCell');
                        });
                        
                        $(this).tooltip('hide');
                        ui.clearTooltipsIn('#playerInfo [rel=tooltip]');
                    }
                });

            });
        },
        sell: function(cellArr, player, messages) {
            var number = BOARD.getPlayerNumber(player);
            BOARD.cellManipulation(cellArr, player);

            $.each(cellArr, function(i, e) {
                var cell = number + "MiniCell" + e;

                $(cell).unbind('click');
                $(cell).bind('click');

                ui.attachTooltip(cell, messages[cellArr[i]]);

                $(cell).click(function() {
                    if($(this).hasClass('visibleCell')) {
                        var $selected = $(this),
                            pos = parseInt($selected.attr('id').match(/\d+$/)[0], 10);

                        $(this).removeClass('visibleCell');

                        MONO.transport.send('sell', {
                            position: pos
                        });

                        $('.visibleCell').filter(function(i, e) {
                            return $selected.attr('id') != $(e).attr('id');
                        }).each(function(i, e) {
                            $(e).removeClass('visibleCell');
                        });
                        
                        $(this).tooltip('hide');
                        ui.clearTooltipsIn('#playerInfo [rel=tooltip]');
                    }
                });
            });
        },
        rollDice: function(dice1, dice2) {
            $("#diceImg1").attr("src", "resources/img/board/die" + dice1 + ".gif");
            $("#diceImg2").attr("src", "resources/img/board/die" + dice2 + ".gif");
        },
        buy: function(player, cell) {
            var number = BOARD.getPlayerNumber(player);
            var playerNumber = BOARD.getPlayer(player);
            var miniCell = cell;

            cell = "#ownerCell" + cell;
            $(number + "MiniCell" + miniCell).addClass("setMiniImagePlayer" + playerNumber);
            $(cell).addClass("setColorPlayer" + playerNumber);
        },
        updateMoney: function(who, money) {
            var colorToMoney = {
                'BROWN': '1',
                'GREEN': '2',
                'RED': '3',
                'VIOLET': '4'
            };
            $("#MoneyPlayer" + colorToMoney[who]).html(money + "$");
        },
        getPlayerNumber: function(player) {
            return BOARD.CONSTANT.PLAYER_NUMBER[player];
        },
        getPlayer: function(player) {
            var id;
            if(player == "BROWN") {
                id = 1;
            } else if(player == "GREEN") {
                id = 2;
            } else if(player == "RED") {
                id = 3;
            } else {
                id = 4;
            }

            return id;
        },

        draw: {
            mortage: function(cell, player) {
                var playerNumber = BOARD.getPlayerNumber(player);
                var playerOrder = BOARD.getPlayer(player);
                cell = playerNumber + "MiniCell" + cell;
                $(cell).addClass('setMortageCell').removeClass("setMiniImagePlayer" + playerOrder);
            },
            unmortage: function (cell, player) {
            	var playerNumber = BOARD.getPlayerNumber(player);
				var playerOrder = BOARD.getPlayer(player); 
				cell=playerNumber+"MiniCell"+cell;
				$(cell).removeClass('setMortageCell').addClass("setMiniImagePlayer"+playerOrder);
            },
            build:function(cell){
        		
				BOARD.houseManipulation.buildHouse(cell);
        	},
        	sell:function(cell, player){
				
        		BOARD.sellAll(player,cell);
        	}
            

        },

        init: function() { /*--Accardion--*/
            $(function() {
                $("#accordion").accordion();
            }); /*--BUTTON--*/
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
