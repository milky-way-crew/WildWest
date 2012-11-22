	var BOARD = {};
	
	BOARD = {
	
		CONSTANT:{
			/* Value of shift in MOVE methods */
			SHIFT: {
				LEFT_BIG_SHIFT : '-=70%',
				LEFT_SMALL_SHIFT : '-=51%',
				TOP_BIG_SHIFT : '-=550%',
				TOP_SMALL_SHIFT : '-=370%',
				RIGHT_BIG_SHIFT : '+=70%',
				RIGHT_SMALL_SHIFT : '+=51%',
				DOWN_BIG_SHIFT : '+=550%',
				DOWN_SMALL_SHIFT : '+=370%'
			},
			/* Value of shift in JUMP methods */
			JUMP: {
				LEFT_BIG_JUMP : '-=100%',
				LEFT_SMALL_JUMP : '-=74%',
				TOP_BIG_JUMP : '-=110%',
				TOP_SMALL_JUMP : '-=74%',
				RIGHT_BIG_JUMP : '+=100%',
				RIGHT_SMALL_JUMP : '+=74%',
				DOWN_BIG_JUMP : '+=110%',
				DOWN_SMALL_JUMP : '+=74%'
			},
			DURATION : 500,
			
			COLOR_PLAYER:{
				"BROWN" : '#player1',
				"GREEN" : '#player2',
				"RED" 	: '#player3',
				"VIOLET" :'#player4'
			
			}
			
		},
		animate:{
				/**** Player move on the left or on the right ***/
				playerLeftAnimate: function (player, shift){
								$(player).animate({left : shift}, BOARD.CONSTANT.DURATION);
							},
				/**** Player move on the top or on the down ***/			
				playerTopAnimate:function (player, shift){
								$(player).animate({top : shift}, BOARD.CONSTANT.DURATION);
							},
				/**** Player jump on the left or on the right ***/			
				playerLeftJump: function (player, shift){
								$(player).css({left : shift});
							},
				/**** Player jump on the top or on the down ***/			
				playerTopJump: function (player, shift){
								$(player).css({top : shift});
							},
				/* Move the player */
				stepOnBoard:function (playerColor, dice1, dice2, startCell){
						var start=startCell;
						var DICE=dice1+dice2;
						var player = BOARD.CONSTANT.COLOR_PLAYER[playerColor];
							for (var i = 0; i<DICE; i++){
							if(start>=1 && start<11){
								if(start==1){
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_BIG_SHIFT);
								}else if(start==10){
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_BIG_SHIFT);
								}else{
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.LEFT_SMALL_SHIFT);
								}
							}else if(start>=11 && start<21){
								if(start==11){
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_BIG_SHIFT);
								}else if(start==20){
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_BIG_SHIFT);
								}else{
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.TOP_SMALL_SHIFT);
								}

							}else if(start>=21 && start<31){
								if(start==21){
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_BIG_SHIFT);
								}else if(start==30){
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_BIG_SHIFT);
								}else{
									BOARD.animate.playerLeftAnimate(player, BOARD.CONSTANT.SHIFT.RIGHT_SMALL_SHIFT);
								}
							}else{
								if(start==31){
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_BIG_SHIFT);
								}else if(start==40){
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_BIG_SHIFT);
								}else{
									BOARD.animate.playerTopAnimate(player, BOARD.CONSTANT.SHIFT.DOWN_SMALL_SHIFT);
								}
							}

							start++;
							if(start>40)
							start-=40;
						}
					},
				
				/* JUMP the player */
				jumpOnBoard:	function (playerColor, dice1, dice2, startCell){
					var start=startCell;
						var DICE=dice1+dice2;
						var player = BOARD.CONSTANT.COLOR_PLAYER[playerColor];
					
					for (var i = 0; i<DICE; i++){
						if(start>=1 && start<11){
							if(start==1){
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
							}else if(start==10){
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
							}else{
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_SMALL_JUMP);
							}
							}else if(start>=11 && start<21){
							if(start==11){
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
							}else if(start==20){
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
							}else{
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_SMALL_JUMP);
							}

							}else if(start>=21 && start<31){
							if(start==21){
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
							}else if(start==30){
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
							}else{
								BOARD.animate.playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_SMALL_JUMP);
							}
							}else{
							if(start==31){
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
							}else if(start==40){
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
							}else{
								BOARD.animate.playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_SMALL_JUMP);
							}
						}

					start++;
					if(start>40)
					start-=40;
					}
				}

			},
		button:{
			buyButton:$(function(){
			/*
					$("#buyButton").click(function(){
					$("#ownerCell"+(DICE+1)+"").addClass('setColorPlayer1');
					$("#miniCityCell"+(DICE+1)+"").addClass('setColorPlayer1');
					});
					*/
				}),
				
			mortageButton:$(function(){
			
				$('#mortageButton').click(function() {
						
						BOARD.cellManipalation([2,4,6]);
						BOARD.mortage([2,4,6]);
						
					});
					}),
				
			unmortageButton:$(function(){
			
				$('#unmortageButton').click(function() {
						BOARD.unmortageSelect([2,6]);
						BOARD.unmortage([2,6]);
					
				});
				}),
				
			buildButton : $(function(){
			
					$('#buildButton').click(function() {
						BOARD.houseManipulation.buildHouse('#house_cell2');
					});
				}),
			sellButton: $(function(){
				$('#sellButton').click(function() {
						BOARD.houseManipulation.sellHouse('#house_cell2');
					});
				}),
				
			rollDiceButton: function(){
				$('#diceButton').click(function() {
							
					alert('rool');
					});
			},
									
			doneButton:$(function(){
					$('#doneButton').click(function() {
						alert('Done');
						});
					}),
			/**** Change the button at disable ****/
			setDisableButton:function (button){
				$(button).attr('disabled',true);
				$(button).addClass('disableButton');
			},
			/**** Change the button at enable ****/
			setEnableButton:function (button){
				$(button).attr('disabled',false);
				$(button).removeClass('disableButton');
			}
		}, 
		
		
		houseManipulation:{
							/**** Build the house  ****/
							buildHouse:function (houseCell){
							houseCell = "#house_cell"+houseCell;
								if($(houseCell).attr('src')=="resources/img/board/emptyhouse.png"){
									$(houseCell).attr('src', 'resources/img/board/house1.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house1.png"){
									$(houseCell).attr('src', 'resources/img/board/house2.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house2.png"){
									$(houseCell).attr('src', 'resources/img/board/house3.png');
								}else{
									$(houseCell).attr('src', 'resources/img/board/big_hotel.png');
								}
							},
							/**** Sell the house  ****/
							sellHouse:function (houseCell){
							houseCell = "#house_cell"+houseCell;
								if($(houseCell).attr('src')=="resources/img/board/big_hotel.png"){
									$(houseCell).attr('src', 'resources/img/board/house3.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house3.png"){
									$(houseCell).attr('src', 'resources/img/board/house2.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house2.png"){
									$(houseCell).attr('src', 'resources/img/board/house1.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house1.png"){
									$(houseCell).attr('src', "resources/img/board/emptyhouse.png");
								}
							}
		},
								sell:function(player,cell){
								var houseCell = "#house_cell"+cell;
								var ownerCell = "#ownerCell"+cell;
			
								if($(houseCell).attr('src')=="resources/img/board/big_hotel.png"){
									$(houseCell).attr('src', 'resources/img/board/house3.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house3.png"){
									$(houseCell).attr('src', 'resources/img/board/house2.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house2.png"){
									$(houseCell).attr('src', 'resources/img/board/house1.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house1.png"){
									$(houseCell).attr('src', "resources/img/board/emptyhouse.png");
								}else{
									if(player=="BROWN"){
									$(ownerCell).removeClass("setColorPlayer1");
									}else if(player=="GREEN"){
									$(ownerCell).removeClass("setColorPlayer2");
									}else if(player=="RED"){
									$(ownerCell).removeClass("setColorPlayer3");
									}else{
									$(ownerCell).removeClass("setColorPlayer4");
									}
								
								}
								
								
		
		},
		
		/* Mortage and unmartage manipolation*/
		cellManipalation:function(cellArr){
		for(var i = 0; i<cellArr.length; i++){
			cell="#firstMiniCell"+cellArr[i];
			$(cell).addClass('visibleCell');
		}
		
		/*
						cell="#miniCityCell"+cell;
						$(cell).addClass('visibleCell');
		*/
									
		},
		
		unmortageSelect:function(cellArr){
			for(var i = 0; i<cellArr.length; i++){
			cell="#firstMiniCell"+cellArr[i];
			$(cell).addClass('unmortageSelected');
		}
		},
		mortage:function(cellArr){
		
		for(var i = 0; i<cellArr.length; i++){
				cell="#firstMiniCell"+cellArr[i];
				
				
				
				$(cell).click(function(){
				
				
				if($(cell).hasClass('visibleCell')){
					var $selected = $(this);
					$(this).addClass('setMortageCell').removeClass('visibleCell');
					
					$('.visibleCell').filter(function(i, e) {
						return $selected.attr('id') != $(e).attr('id');
					}).each(function(i, e) {
						$(e).removeClass('visibleCell');
					});
						
					
					}
				});
				
			}
						
		},
		unmortage:function(cellArr){
			for(var i = 0; i<cellArr.length; i++){
				cell="#firstMiniCell"+cellArr[i];
				
				$(cell).click(function(){
				if($(cell).hasClass('unmortageSelected')){
				
					var $selected = $(this);
					$(this).removeClass('setMortageCell').removeClass('unmortageSelected');
					
					$('.unmortageSelected').filter(function(i, e) {
						return $selected.attr('id') != $(e).attr('id');
					}).each(function(i, e) {
						$(e).removeClass('unmortageSelected');
					});			
					
					}
				});
			
			}
		},
		rollDice:function(dice1, dice2){
			$("#diceImg1").attr("src", "resources/img/board/die"+dice1+".gif");
			$("#diceImg2").attr("src", "resources/img/board/die"+dice2+".gif");
		},
				
		buy:function(player, cell){
			cell = "#ownerCell"+cell;
			if(player=="BROWN"){
			$(cell).addClass("setColorPlayer1");
			}else if(player=="GREEN"){
			$(cell).addClass("setColorPlayer2");
			}else if(player=="RED"){
			$(cell).addClass("setColorPlayer3");
			}else{
			$(cell).addClass("setColorPlayer4");
			}
		
		},
		
		
		setMoney:function(message){
			$("#secondMoney").text(message);
		},
		init: function(){
		
			
			/*--Accardion--*/
			$(function() {
				$( "#accordion" ).accordion();
			});
			/*--BUTTON--*/
			$(function() {
				$( "input[type=submit]" )
					.button()
					.click(function( event ) {
						event.preventDefault();
					});
			});
			
			$(function() {
			$('#infoPlayer1').css('background', '#CCE7D0');
			});
			
			
			
			BOARD.button.mortageButton;
			BOARD.button.unmortageButton;
			BOARD.button.buildButton;
			BOARD.button.sellButton;
			BOARD.button.roolDiceButton;
			
			/*
			BOARD.mortageManipolation.mortage;
			*/
			
		
		}
	};
	BOARD.init();




 
	
