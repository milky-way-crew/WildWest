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


		houseManipulation: { /**** Build the house  ****/
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
			/**** Sell the house  ****/
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
				cell = number + "MiniCell" + cellArr[i];
				$(cell).addClass('visibleCell');
			}


		},

		unmortageSelect: function(cellArr, player) {

			var number = BOARD.getPlayerNumber(player);
			for(var i = 0; i < cellArr.length; i++) {
				cell = number + "MiniCell" + cellArr[i];
				$(cell).addClass('unmortageSelected');
			}
		},
		mortage: function(cellArr, player) {
			BOARD.cellManipulation(cellArr, player);

			var playerNumber = BOARD.getPlayerNumber(player);
			for(var i = 0; i < cellArr.length; i++) {
				cell = "" + playerNumber + "MiniCell" + cellArr[i];
				$(cell).unbind('click');
				$(cell).bind('click');


				$(cell).click(function() {
					if($(cell).hasClass('visibleCell')) {
						var $selected = $(this);
						$(this).addClass('setMortageCell').removeClass('visibleCell');

						MONO.transport.send('mortage', {
								position: cellArr[i]
						});

						$('.visibleCell').filter(function(i, e) {
							return $selected.attr('id') != $(e).attr('id');
						}).each(function(i, e) {
							$(e).removeClass('visibleCell');
						});


					}
				});

			}

		},
		unmortage: function(cellArr, player) {
			BOARD.unmortageSelect(cellArr, player);
			var number = BOARD.getPlayerNumber(player);

			for(var i = 0; i < cellArr.length; i++) {


				cell = number + "MiniCell" + cellArr[i];

				$(cell).unbind('click');
				$(cell).bind('click');

				$(cell).click(function() {
					if($(cell).hasClass('unmortageSelected')) {

						var $selected = $(this);
						$(this).removeClass('setMortageCell').removeClass('unmortageSelected');

						MONO.transport.send('unmortage', {
								position: cellArr[i]
						});
						
						$('.unmortageSelected').filter(function(i, e) {
							return $selected.attr('id') != $(e).attr('id');
						}).each(function(i, e) {
							$(e).removeClass('unmortageSelected');
						});

					}
				});

			}
		},

		build: function(cellArr, player) {
			var number = BOARD.getPlayerNumber(player);
			BOARD.cellManipulation(cellArr, player);

			$.each(cellArr, function(i, e) {
				cell = number + "MiniCell" + e;

				$(cell).unbind('click');
				$(cell).bind('click');
				$(cell).click(function() {
					if($(this).hasClass('visibleCell')) {
						var $selected = $(this);
						console.log('Build in cell', e);
						BOARD.houseManipulation.buildHouse(e);
						$(this).removeClass('visibleCell');

						MONO.transport.send('build', {
								position: e
						});		

						$('.visibleCell').filter(function(i, e) {
							return $selected.attr('id') != $(e).attr('id');
						}).each(function(i, e) {
							$(e).removeClass('visibleCell');
						});

					}
				});

			});


		},
		sell: function(cellArr, player) {
			var number = BOARD.getPlayerNumber(player);
			BOARD.cellManipulation(cellArr, player);

			$.each(cellArr, function(i, e) {
				cell = number + "MiniCell" + e;

				$(cell).unbind('click');
				$(cell).bind('click');
				$(cell).click(function() {
					if($(this).hasClass('visibleCell')) {
						var $selected = $(this);
						console.log('Sell in cell', e);
						BOARD.sellAll(player, e);
						$(this).removeClass('visibleCell');

						$('.visibleCell').filter(function(i, e) {
							return $selected.attr('id') != $(e).attr('id');
						}).each(function(i, e) {
							$(e).removeClass('visibleCell');
						});

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


		setMoney: function(message) {
			$("#MoneyPlayer1").text(message);
		},
		getPlayerNumber: function(player) {
			if(player == "BROWN") {
				return BOARD.CONSTANT.PLAYER_NUMBER["BROWN"];
			} else if(player == "GREEN") {
				return BOARD.CONSTANT.PLAYER_NUMBER["GREEN"];
			} else if(player == "RED") {
				return BOARD.CONSTANT.PLAYER_NUMBER["RED"];
			} else {
				return BOARD.CONSTANT.PLAYER_NUMBER["VIOLET"];
			}
		},
		getPlayer: function(player) {
			if(player == "BROWN") {
				return 1;
			} else if(player == "GREEN") {
				return 2;
			} else if(player == "RED") {
				return 3;
			} else {
				return 4;
			}
		},
		init: function() {


			/*--Accardion--*/
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