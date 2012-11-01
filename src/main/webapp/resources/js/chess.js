//var socket = new WebSocket("ws://localhost:8888/");
//socket.onopen = function() {
//	console.log("Connection opened");
//};
//socket.onclose = function() {
//	console.log("Connection closed");
//};
//socket.onmessage = function(event) {
//	console.log("Message:", event.data);
//	updater(event.data + "\n");
//};

var GAME = {
	options : {
		ajax_url : 'chess2',
		owner : 1,
		board : new Object(),
		can_move : true
	},

	sendMessage : function(data, callback) {
		// socket.send(data);
		$.ajax({
			url : GAME.options.ajax_url,
			type : "POST",
			data : data,
			success : callback,
		});
	},

	initBoard : function() {
		this.sendMessage({}, function(json) {
			var board = json.board;

			for ( var i = 0; i < board.length; i++) {
				var cell = board[i];
				var key = cell.position.x + "" + cell.position.y;
				GAME.options.board[key] = {
					type : cell.type,
					owner : cell.owner,
				};
				if (cell.owner == null) {
				} else if (cell.owner.id == GAME.options.owner) {
					$('#' + key + ' a').html(cell.type[0]);
				} else {
					$('#' + key + ' a').html('?');
				}
			}
		});
	},

	makeMove : function(from_id, to_id) {
		var GAMEBoard = GAME.options.board;
		var emptyCell = {
			type : null,
			owner : null,
		};
		if (GAMEBoard[to_id].owner == null) {
			// Its ok, empty cell
			console.log('MAKE_MOVE: Empty cell');
			var copy = GAMEBoard[from_id];
			GAMEBoard[from_id] = emptyCell;
			GAMEBoard[to_id] = copy;
			return GAME.view.animateMove(from_id, to_id);

		} else {
			// Its opponent cell
			console.log('MAKE_MOVE: Opponent cell');

			var battleResult = GAME.battle(from_id, to_id);
			// sendMessage( { MOVE }, function(data) onSucces {
			// 	LOOSE, DRAW, WIN
			// }  )

			console.log('RESULT: ' + battleResult);

			if (battleResult === "WIN") {
				var copy = GAMEBoard[from_id];
				GAMEBoard[from_id] = emptyCell;
				GAMEBoard[to_id] = copy;

				GAME.view.animateWin(from_id, to_id);
				return;
			} else if (battleResult === "LOOSE") {
				GAMEBoard[from_id] = emptyCell;
				// SHOW TYPE OF FIGURE DONT FORGET
				GAME.view.animateLoose(from_id, to_id);
				return;
			} else if (battleResult === "DRAW") {
				GAME.view.animateDraw(from_id, to_id);
			} else {
				console.error("*** Unknown battle result");
			}
		}
	},

	isOwnerOf : function(id) {
		console
				.log("OWNER OF " + id + " is "
						+ GAME.options.board[id].owner.id);
		console.log("PLAYER OWNER ID: " + GAME.options.owner);

		return GAME.options.board[id].owner.id == GAME.options.owner;
	},

	battle : function(id_from, id_to) {
		return "LOOSE";
		// return "WIN";
	},

	view : {
		animateMove : function(from_id, to_id) {
			$('.selected').fadeOut(500, function() {
				var $from = $('#' + from_id);
				var $to = $('#' + to_id);
				var $figure = $('.selected');

				$from.effect("highlight", {}, 1000);
				$to.html($('.selected'));
				$figure.fadeIn(500, function() {
					$to.effect("highlight", {}, 1000);
				});
				$figure.removeClass('selected');
			});
		},
		animateLoose : function(from_id, to_id) {
			var $from = $('#' + from_id);
			var $to = $('#' + to_id);

			$from.find('.figure').fadeOut(1000);
			$from.html('');
			$to.find('.figure').effect("bounce", {
				times : 3
			}, 1000);
			// THATS MUST BE NOT HERE
			$('#' + to_id + ' a').html(GAME.options.board[to_id].type[0]);
		},
		animateWin : function(from_id, to_id) {
			var $to = $('#' + to_id);
			var $from_figure = $('.selected');
			var $to_figure = $($to.find('a'));

			$from_figure.fadeOut(500);
			$to_figure.fadeOut(500, function() {
				$to.html($from_figure);
				$from_figure.fadeIn(500);
				$from_figure.removeClass('.selected');
			});
		},
		animateDraw : null,
	},

	isValid : function(from, to) {
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// FIXME
		// return true;
		var xDelta = Math.abs(from[0] - to[0]);
		var yDelta = Math.abs(from[1] - to[1]);

		if (yDelta > 1 || xDelta > 1) {
			return false; // Moves on distant >1 cells not allowed
		} else if (yDelta - xDelta == 0) {
			return false; // Diagonal moves not allowed
		} else {
			return true; // Everything is OK.
		}
		return true;
	}

};

$(document).ready(function() {

	var board = $('#chess_board');
	board = {
		width : board.width(),
		height : board.height()
	};

	var hoverIn = function() {
		if ($('.selected').length > 0) {
			var from = $('.selected').parent().attr('id');
			if (GAME.isValid(from, $(this).attr('id'))) {
				$(this).addClass('valid');
			} else {
				$(this).addClass('invalid');
			}
		}
	};

	var hoverOut = function() {
		$(this).removeClass('valid');
		$(this).removeClass('invalid');
	};

	$('#chess_board td').each(function() {
		$(this).hover(hoverIn, hoverOut);
	});

	var lock = false;

	$('#chess_board td').each(function() {
		$(this).click(function() {
			if ($('.selected').length > 0) {
				var $fromCell = $('.selected').parent();
				var $toCell = $(this);

				var id_from = $fromCell.attr('id');
				var id_clicked = $toCell.attr('id');

				console.log(id_from);
				console.log(id_clicked);
				GAME.makeMove(id_from, id_clicked);
			}
		});
	});

	$('.figure').each(function() {
		$(this).click(function() {
			var $clicked = $(this).parent().attr('id');
			if (GAME.isOwnerOf($clicked)) {
				console.log('CLICK ON OWN FIGURE');
				$('.selected').removeClass('selected');
				$(this).addClass('selected');

				$(this).effect("bounce", {
					times : 3
				}, 300);
				return false;
			} else {

			}
		});
	});

	GAME.initBoard();
});