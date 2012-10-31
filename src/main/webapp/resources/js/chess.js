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
		board : new Object()
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
			// GAME.options.board = board;

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

			// for ( var i = 0; i < board.length; i++) {
			// var cell = board[i];
			// if (cell.type != null) {
			// var id = "#" + cell.position.x + "" + cell.position.y;
			// var img = cell.type[0];
			// // GAME.options.board[cell.position.x + "" +
			// // cell.position.y] = {
			// //
			// // owner : cell.owner,
			// // type : cell.type
			// //
			// // };
			// if (cell.owner === null) {
			// // pass
			// } else if (cell.owner.id != GAME.options.owner) {
			// $cell = $(id + ' a');
			// $cell.html("?");
			// } else {
			// $cell = $(id + ' a');
			// $cell.html(img);
			// // $($cell.find('a')).addClass('w');
			// }
			// }
			// }
		});
	},
	makeMove : function(from_id, to_id) {
		var gameBoard = GAME.options.board;
		
		if (gameBoard[to_id].owner == null) {
			console.log('MAKE_MOVE: empty cell');
			var copy = gameBoard[from_id];
			gameBoard[from_id] = {
					type : null,
					owner : null
			};
			gameBoard[to_id] = copy;
		} else {
			var copy = gameBoard[from_id];
			gameBoard[from_id] = {
					type : null,
					owner : null
			};
			gameBoard[to_id] = copy;
		}
	},

	isOwnerOf : function(id) {
		console.log("OWNER OF " + id + " is "
						+ GAME.options.board[id].owner.id);
		console.log("PLAYER OWNER ID: " + GAME.options.owner);

		return GAME.options.board[id].owner.id == GAME.options.owner;
	}

};

$(document).ready(function() {

	var board = $('#chess_board');
	board = {
		width : board.width(),
		height : board.height()
	};

	var Util = {
		isValid : function(from, to) {
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// FIXME
			return true;
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

	var hoverIn = function() {
		if ($('.selected').length > 0) {
			var from = $('.selected').parent().attr('id');
			if (Util.isValid(from, $(this).attr('id'))) {
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

	$('#chess_board td').each(function() {
		$(this).click(function() {
			if ($('.selected').length > 0) {
				var $fromCell = $('.selected').parent();
				var $toCell = $(this);

				var id_from = $fromCell.attr('id');
				var id_clicked = $toCell.attr('id');

				console.log('Empty cell');

				console.log(id_from);
				console.log(id_clicked);
				GAME.makeMove(id_from, id_clicked);

				$('.selected').fadeOut(500, function() {
					$($fromCell).effect("highlight", {}, 1000);
					$toCell.html($('.selected'));
					$('.selected').fadeIn(500, function() {
						$toCell.effect("highlight", {}, 1000);
					});
					$('.selected').removeClass('selected');
				});
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