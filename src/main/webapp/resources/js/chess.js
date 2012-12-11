/*global jQuery, console, alert, document, setInterval, setTimeout, clearInterval, prompt, bootbox*/

(function($) {
    "use strict";
    var GAME = {};

    GAME = {
        config: {
            ajax: 'chess/update',
            owner: null,
            isStarted: false,
            board: {},
            canMove: false,
            emptyFigure: {
                type: null,
                owner: null
            }
        },
        sendMessage: function(data, callback) {
            $.ajax({
                url: GAME.config.ajax,
                // cache: false,
                type: "POST",
                data: data,
                success: callback
            });
        },
        inform: function(text) {
            $('.info').fadeOut(500, function() {
                $(this).html(text);
                $(this).fadeIn(500);
            });
        },
        initBoard: function() {
            console.log('INIT REQUEST + whoami');
            this.sendMessage({
                'whoami': null,
                'init': null
            }, function(json) {
                GAME.config.owner = json.whoami;
                console.log('OWNER IN GAME ' + GAME.config.owner);
                console.log('OWNER IN JSON ' + json.whoami);
                GAME.updateBoard(json.init);
            });
        },
        updateBoard: function(board) {
            var i, cell, key;
            for(i = 0; i < board.length; i += 1) {
                cell = board[i];
                key = cell.position.x.toString() + cell.position.y.toString();

                // Saving requested board into local storage
                GAME.config.board[key] = {
                    type: cell.type,
                    owner: cell.owner
                };

                if(cell.owner === null && cell.type === null) {
                    GAME.view.animateShowType(key, '');
                } else if(cell.owner === GAME.config.owner) {
                    // Player cell
                    GAME.view.animateShowType(key, cell.type[0]);
                } else {
                    if(cell.shownToEnemy === true) {
                        GAME.view.animateShowType(key, cell.type[0]);
                    } else {
                        GAME.view.animateShowType(key, '?');
                    }
                }
            }
        },
        makeMove: function(idFrom, idTo) {
            GAME.config.canMove = false;
            console.log('EVENT: MOVE');
            console.log('Sending coordinates to server');
            GAME.inform('Sending your move to server');

            GAME.sendMove(idFrom, idTo, function(json) {
                // GAME.inform('Result of move:' + json.result);
                if(typeof GAME.handleEvent[json.result] !== 'undefined') {
                    console.log('Result of move from server: ' + json.result);
                    GAME.handleEvent[json.result](idFrom, idTo, json);
                } else {
                    console.error("*** Unknown server response");
                }
            });
        },


        handleEvent: {
            //***************************************//
            //******** BE AWARE OF DRAGONS **********//
            //***************************************//
            'EMPTY': function(idFrom, idTo, json) {
                console.log('RESULT: EMPTY CELL');
                var copy = GAME.config.board[idFrom];
                GAME.config.board[idFrom] = {
                    type: null,
                    owner: null
                };
                GAME.config.board[idTo] = copy;
                GAME.view.animateMove(idFrom, idTo);
            },
            'WIN': function(idFrom, idTo, json) {
                console.log('BATTLE: WIN');
                var copy = GAME.config.board[idFrom];


                console.log('Win. From: ' + idFrom + ", To:" + idTo);
                console.log('refreshing inner board positions');


                if(typeof json.invoker !== 'undefined') {
                    GAME.view.animateShowType(idFrom, json.invoker[0]);
                }
                if(typeof json.defender !== 'undefined') {
                    GAME.view.animateShowType(idTo, json.defender[0]);
                }

                GAME.config.board[idFrom] = {
                    type: null,
                    owner: null
                };
                GAME.config.board[idTo] = copy;


                if(GAME.isOwnerOf(idTo) === false) {
                    // This player is defender
                    // It's enemy win
                    console.log('Its enemy win');
                    console.log('invoker type in json: ' + json.invoker);
                    console.log('setting invoker type to inner board');
                    console.log('was: ' + GAME.config.board[idTo].type);
                    GAME.config.board[idTo].type = json.invoker[0];
                    console.log('now: ' + GAME.config.board[idTo].type);
                    console.log('refreshing html');
                    // GAME.view.animateShowType(idFrom, json.invoker[0]);
                    GAME.view.animateWin(idFrom, idTo);

                } else {
                    console.log('Its yours win');
                    GAME.config.board[idTo].type = json.invoker[0];
                    GAME.view.animateWin(idFrom, idTo);
                }
            },
            'LOOSE': function(idFrom, idTo, json) {
                console.log('BATTLE: LOOSE');

                console.error('DONT FORGET TO IMPLEMENT ENEMY TYPE');
                console.error(json.enemy);


                if(typeof json.invoker !== 'undefined') {
                    GAME.view.animateShowType(idFrom, json.invoker[0]);
                }
                if(typeof json.defender !== 'undefined') {
                    GAME.config.board[idTo].type = json.defender[0];
                    GAME.view.animateShowType(idTo, json.defender[0]);
                }


                // Setting looser figure to null
                GAME.config.board[idFrom] = {
                    type: null,
                    owner: null
                };

                // FIXME: Not sure if need that check
                if(GAME.isOwnerOf(idFrom) === true) {
                    // This player is invoker 
                    // And he is loose the battle
                    // Showing him enemy type
                    GAME.config.board[idTo].type = json.defender[0];
                }

                GAME.view.animateLoose(idFrom, idTo);
            },

            'DRAW': function(idFrom, idTo, json) {
                console.log('BATTLE: DRAW');
                //**************************//
                //******** DANGER **********//
                //**************************//
                clearInterval(GAME.updaterService);

                bootbox.dialog("It's draw, quickly choose the buttons!<img src='resources/img/all.png'>", [{
                    "label": "Rock!",
                    "class": "btn-group-vertical btn-success",
                    "callback": function() {
                        GAME.sendMessage({
                            'draw_choice': 'ROCK'
                        }, function() {
                            setInterval(GAME.updaterService, 5000);
                        });
                    }
                }, {
                    "label": "Paper!",
                    "class": "btn-group-vertical btn-danger",
                    "callback": function() {
                        GAME.sendMessage({
                            'draw_choice': 'PAPER'
                        }, function() {
                            setInterval(GAME.updaterService, 5000);
                        });
                    }
                }, {
                    "label": "Scissors!",
                    "class": "btn-group-vertical btn-primary",
                    "callback": function() {
                        GAME.sendMessage({
                            'draw_choice': 'SCISSORS'
                        }, function() {
                            setInterval(GAME.updaterService, 5000);
                        });
                    }
                }]);
            },
            "ABSOLUTE_WIN": function(idFrom, idTo, json) {
                // alert('abs win');
                GAME.view.animateWin(idFrom, idTo, json);

                if(GAME.isOwnerOf(idFrom) === true || typeof idFrom === 'undefined') {
                    $('#fin-label').html('Congradulations! You win the game.');
                    $('#fin img').attr("src", "./resources/img/chess/win.png");
                } else {
                    $('#fin img').attr("src", "./resources/img/chess/loose.png");
                    $('#fin-label').html('You loose the game, better luck next time.');
                }

                $('#fin').modal();
                
                clearInterval(GAME.updaterService);
                GAME.inform('redirecting in 5 seconds');
                setTimeout("window.location='./goHome'", 7000);
            },
            "END": function(json) {
                bootbox.alert('End of game, redirecting to home in 4 seconds');
                // window.location.href = "../chess/exit";
            }
            //***************************************//
            //******** END OF DRAGONS \0 ************//
            //***************************************//
        },
        updaterService: function() {
            GAME.sendMessage('changes', function(json) {
                if(json.mail) {
                    $.each(json.mail, function(i, mail) {
                        chat.prepend('<h4>' + mail + '</h4>');
                    });
                }


                GAME.config.canMove = json.move;
                var message = typeof json.move === 'undefined' ? 'Waiting for opponent' : (json.move ? 'Its your turn' : 'Opponent turn');
                GAME.inform(message);

                if(typeof json.result !== 'undefined') {
                    if(typeof json.enemyMove !== 'undefined') {
                        // Move event
                        var move = json.enemyMove,
                            idFrom = null,
                            idTo = null;

                        idFrom = move.start.x.toString() + move.start.y.toString();
                        idTo = move.end.x.toString() + move.end.y.toString();

                        GAME.inform('Opponent moved');
                        GAME.handleEvent[json.result](idFrom, idTo, json);
                    } else {
                        // Game event
                        GAME.handleEvent[json.result](json);
                    }
                } else {
                    console.log('undefined result');
                }
            });
        },
        isOwnerOf: function(id) {
            console.log("OWNER OF " + id + " is " + GAME.config.board[id].owner);
            console.log("PLAYER OWNER ID: " + GAME.config.owner);

            return GAME.config.board[id].owner === GAME.config.owner;
        },

        sendMove: function(idFrom, id_to, callback) {
            this.sendMessage({
                "move": idFrom + ":" + id_to
            }, callback, function(msg) {
                console.log('error while sending to server' + msg);
            });
        },

        view: {
            animateMove: function(from_id, to_id) {
                var $from = $('#' + from_id),
                    $to = $('#' + to_id),
                    $figure = $from.find('.figure');

                $figure.fadeOut(500, function() {
                    $from.effect("highlight", {}, 1000);
                    $to.html($figure);
                    $figure.fadeIn(500, function() {
                        $to.effect("highlight", {}, 500);
                    });
                    $figure.removeClass('selected');
                });
            },
            animateLoose: function(from_id, to_id) {
                var $from = $('#' + from_id),
                    $to = $('#' + to_id);


                $from.find('.figure').fadeOut(1000, function() {
                    $from.html('');
                });

                $to.find('.figure').effect("bounce", {
                    times: 3
                }, 1000);
            },
            animateWin: function(from_id, to_id) {
                console.log('starting refreshing html');
                console.log('id from: ' + from_id + "\nto: " + to_id);

                var $from = $('#' + from_id),
                    $to = $('#' + to_id),
                    $from_figure = $from.find('.figure'),
                    $to_figure = $to.find('.figure');

                console.log('from html value -> ' + $from_figure.html() + "\n" + 'to html value -> ' + $to_figure.html());
                console.log('starting animations');

                $from_figure.fadeOut(500);
                $to_figure.fadeOut(500, function() {
                    console.log('setting val from to ' + $from_figure.html());
                    $to.html($from_figure);

                    $from_figure.fadeIn(500);
                    $from_figure.removeClass('.selected');
                });
            },
            animateDraw: function() {
                console.error('*** animateDraw not supported');
            },
            animateShowType: function(id, type) {
                if(type === '') {
                    $('#' + id).html('');
                    return;
                }

                if($('#' + id).html() === '') {
                    console.log('td has no image inside -> adding <img>. td#id=' + id);
                    $('#' + id).html('<img class="figure">');
                }


                console.log('setting new type to html :' + id);
                console.log('new type is: ' + type);
                type = type.toLowerCase()[0];

                var resources = 'resources/img/chess/',
                    fileName, color, img, type2img = {
                        'r': 'rock',
                        's': 'scissors',
                        'p': 'paper',
                        't': 'trap',
                        'f': 'flag'
                    };

                if(type === '?') {
                    $('#' + id + ' .figure').attr('src', resources + 'unknown.png');
                } else {
                    color = GAME.config.board[id].owner.toLowerCase();
                    fileName = type2img[type];

                    img = resources + fileName + '-' + color + '.png';

                    $('#' + id + ' .figure').attr('src', img);
                }
            }
        },
        isValidMove: function(from, to) {
            if(GAME.config.board[from].owner === GAME.config.board[to].owner) {
                return false;
            }

            // Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
            var xDelta = Math.abs(from[0] - to[0]),
                yDelta = Math.abs(from[1] - to[1]);

            if(yDelta > 1 || xDelta > 1) {
                return false; // Moves on distant >1 cells not allowed
            } else if(yDelta - xDelta === 0) {
                return false; // Diagonal moves not allowed
            } else {
                return true; // Everything is OK.
            }
        }
    };

    $(document).ready(function() {
        $('#chess_board td').each(function() {
            $(this).click(function() {
                if($('.selected').length > 0) {
                    var $fromCell = $('.selected').parent(),
                        $toCell = $(this),
                        idFrom = $fromCell.attr('id'),
                        idClicked = $toCell.attr('id');

                    console.log(idFrom);
                    console.log(idClicked);

                    if(GAME.isValidMove(idFrom, idClicked)) {
                        GAME.makeMove(idFrom, idClicked);
                    } else {
                        GAME.inform('invalid move');
                        console.log('invalid move');
                    }
                }
            });
        });

        $('.figure').each(function() {
            $(this).click(function() {
                var $this = $(this),
                    $clicked = $this.parent().attr('id'),
                    clickedType = GAME.config.board[$clicked].type; //.toLowerCase()[0];
                if(clickedType !== 'TRAP' && clickedType !== 'FLAG' && GAME.isOwnerOf($clicked) && GAME.config.canMove) {
                    $('.selected').removeClass('selected');
                    $this.addClass('selected');
                    $this.effect("bounce", {
                        times: 3
                    }, 300);
                    return false;
                }
            });
        });

        $('#shuffle').click(function() {
            GAME.sendMessage({
                'shuffle': null
            }, function(json) {
                GAME.updateBoard(json.shuffle.board);
            });
        });

        var bfFadeIn = function () {
            $('#bf').fadeIn(300);
            $('#before_game img').attr('src', './resources/img/chess/dazz2.gif');
            $('#before_game img').animate({'margin-top' : '20px'}, 200);
        }, bfFadeOut = function () {
            $('#bf').fadeOut(500);
            $('#before_game img').attr('src', './resources/img/chess/cats.gif');
            $('#before_game img').animate({'margin-top' : '0px'}, 200);
        };

        $('#ready').click(function() {
            bfFadeOut();
            GAME.sendMessage({
                'ready': true
            }, function(json) {
            });
        });


        GAME.sendMessage({
            'is-started': null
        }, function(json) {
            if(json.started === false) {
                // $('#before_game').fadeIn(300);
                bfFadeIn();
            } else {
                bfFadeOut();
                // $('#before_game').fadeOut(500);
            }
        });

        GAME.initBoard();
                
        // ***********************
        chat.init($('#chat-history ul'));
        chat.MAX_MSG = 100;
        var send = function() {
                var msg = $('#chat-input').val();
                $('#chat-input').val('');
                if(msg.length > 0) {
                    chat.prepend('<h4>me : ' + msg.replace("<", '&lt;').replace('>','&gt;') + '</h4>');
                    GAME.sendMessage({
                        'chat': msg
                    }, function(argument) {

                    });
                }
                return false;
            };

        $('#send').click(send);
        $('#chat-input').keypress(function(event) {
            if(event.keyCode == '13') {
                return send();
            }
        });
        // ********************
        
        $(window).on("beforeunload", function() { 
            GAME.sendMessage({
                'chat': 'disconnected.'
            }, function(a) {});
            GAME.sendMessage({
                'disco' : true
            }, function(a) {});
        });
        
        setInterval(GAME.updaterService, 3000);
    });
})(jQuery);