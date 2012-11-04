/*global jQuery, console, alert, document, setInterval, clearInterval, prompt*/


(function($) {
	"use strict";
	var GAME = {};

	GAME = {
		config: {
			ajax: 'chess/update',
			owner: null,
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
		// ######################## NOT TESTED ################# 
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

				if(cell.owner !== null && cell.owner === GAME.config.owner) {
					// Player cell
					$('#' + key + ' a').html(cell.type[0]);
				} else {
					// Opponent cell
					$('#' + key + ' a').html('?');
				}
			}
		},
		// ######################## NOT TESTED ################# 
		makeMove: function(idFrom, idTo) {
			console.log('EVENT: MOVE');
			console.log('Sending coordinates to server');
			GAME.inform('Sending your move to server');

			GAME.sendMove(idFrom, idTo, function(json) {
				GAME.inform('Result of move:' + json.result);

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

				GAME.config.board[idFrom] = {
					type: null,
					owner: null
				};
				GAME.config.board[idTo] = copy;

				console.log('Win. From: ' + idFrom + ", To:" + idTo);
				console.log('refreshing inner board positions');


				if(typeof json.invoker !== 'undefined') {
					GAME.view.animateShowType(idFrom, json.invoker[0]);
				}
				if(typeof json.defender !== 'undefined') {
					GAME.view.animateShowType(idTo, json.defender[0]);
				}



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

				return GAME.view.animateLoose(idFrom, idTo);
			},

			'DRAW': function(idFrom, idTo, json) {
				console.log('BATTLE: DRAW');
				//**************************//
				//******** DANGER **********//
				//**************************//
				clearInterval(GAME.updaterService);
				var choice = prompt("It's draw - chooose your weapon: type [ROCK, SCISSORS, PAPER]");


				GAME.sendMessage({
					'draw_choice': choice
				}, function() {
					setInterval(GAME.updaterService, 5000);
				});
			},



			"DRAW_WIN": function(idFrom, idTo, json) {
				console.log('RESULT: DRAW RESOLVED');

				GAME.config.board[idFrom].type = json.own[0];
				GAME.view.animateShowType(idFrom, json.own[0]);



				GAME.view.animateWin(idFrom, idTo, json);
			}

			//***************************************//
			//******** END OF DRAGONS \0 ************//
			//***************************************//
		},


		updaterService: function() {
			GAME.sendMessage('changes', function(json) {
				GAME.config.canMove = json.move;
				GAME.inform('Move : ' + json.move);

				if(typeof json.result !== 'undefined') {
					var move = json.enemyMove,
						idFrom = move.start.x.toString() + move.start.y.toString(),
						idTo = move.end.x.toString() + move.end.y.toString();

					GAME.inform('Opponent moved');
					GAME.handleEvent[json.result](idFrom, idTo, json);
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
					$figure = $from.find('a');

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


				$from.find('.figure').fadeOut(1000);
				$from.html('');
				$to.find('.figure').effect("bounce", {
					times: 3
				}, 1000);
				// THATS MUST BE NOT HERE
				$('#' + to_id + ' a').html(GAME.config.board[to_id].type[0]);
			},
			animateWin: function(from_id, to_id) {
				console.log('starting refreshing html');
				console.log('id from: ' + from_id + "\nto: " + to_id);

				var $from = $('#' + from_id),
					$to = $('#' + to_id),
					$from_figure = $from.find('a'),
					$to_figure = $to.find('a');

				console.log('from html value -> ' + $from_figure.html() + "\n" + 'to html value -> ' + $to_figure.html());
				console.log('starting animations');

				$from_figure.fadeOut(500);
				$to_figure.fadeOut(500, function() {
					console.log('setting val from to ' + $from_figure.html());
					$to.html($from_figure);
					console.log('now val of $from is: ' + $from_figure.html());
					console.log('now val of $to is: ' + $to_figure.html());
					$from_figure.fadeIn(500);
					$from_figure.removeClass('.selected');
				});
			},
			animateDraw: function() {
				console.error('*** animateDraw not supported');
			},
			animateShowType: function(id, type) {
				console.log('setting new type to html :' + id);
				console.log('new type is: ' + type);

				// console.log('ID: ' + id);
				// console.log('New type: ' + type);
				$('#' + id + ' a').html(type[0]);
			}
		},
		isValidMove: function(from, to) {
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
		//		var hoverIn = function() {
		//				if($('.selected').length > 0) {
		//					var from = $('.selected').parent().attr('id');
		//					if(GAME.isValidMove(from, $(this).attr('id'))) {
		//						$(this).addClass('valid');
		//					} else {
		//						$(this).addClass('invalid');
		//					}
		//				}
		//			},
		//			hoverOut = function() {
		//				$(this).removeClass('valid');
		//				$(this).removeClass('invalid');
		//			},
		//			lock = false;
		// TODO: Its laggy
		// $('#chess_board td').each(function() {
		// $(this).hover(hoverIn, hoverOut);
		// });
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
				var $clicked = $(this).parent().attr('id');
				if(GAME.isOwnerOf($clicked) && GAME.config.canMove) {
					console.log('CLICK ON OWN FIGURE');
					$('.selected').removeClass('selected');
					$(this).addClass('selected');

					$(this).effect("bounce", {
						times: 3
					}, 300);
					return false;
				}
			});
		});

		GAME.initBoard();
		setInterval(GAME.updaterService, 5000);
	});
})(jQuery);
//var socket = new WebSocket("ws://localhost:8888/");
//socket.onopen = function() {
//	console.log("KAWABUNGA!");
//};
//socket.onclose = function() {
//	console.log("AGNUBAWAWK");
//};
//socket.onmessage = function(event) {
//	console.log("Msg:", event.data);
//	updater(event.data + "\n");
//};
// socket.send(data);