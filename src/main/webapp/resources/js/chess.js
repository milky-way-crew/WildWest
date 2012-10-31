var GAME = {
		options : {
			ajax_url : 'chess2',
			owner: 1,
		},
		
		sendMessage : function(data, callback) {
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
					for (var i = 0; i < board.length; i++) {
						var cell = board[i];
						if (cell.type != null) {
							var id = "#" + cell.position.x + "" + cell.position.y;
							var img = cell.type[0];

							if (cell.owner === null) {
								//pass
							} else if (cell.owner.id != GAME.options.owner) {
								$(id + ' a').html("?");
							} else {
								$(id + ' a').html(img);
							}
						}
					}
				});
		},
		
};


$(document).ready(function() {
	var board = $('#chess_board');
	board = {
		width : board.width(),
		height : board.height()
	};

	var Util = {
		isValid : function(from, to) {
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
			console.log('Click on td');
			console.log($(this).attr('id'));
			var figure = $(this).find('.figure');

			if (figure.length > 0) {
				$('.selected').removeClass('selected');
		    	$(figure).addClass('selected');
				return false;
			} 
			var $that = $(this);

			console.log('Empty cell');
			$('.selected').fadeOut(500, function() {
				var $from = $('.selected').parent(); 
				$($from).effect("highlight", {}, 1000);
				$that.html($('.selected'));
				$('.selected').fadeIn(500, function() {
					$that.effect("highlight", {}, 1000);
				});
				$('.selected').removeClass('selected');
			});
		});
	});

	$('.figure').each(function() { 
		$(this).click(function() {
			$(this).effect("bounce", { times:3 }, 300);
		});
	});
	
	GAME.initBoard();
});