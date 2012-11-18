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
				"VIOLET" : '#player4'
			
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
				jumpOnBoard:	function (player, dice1, dice2, startCell, endCell){
					var start=startCell;
					var DICE=dice1+dice2;
					for (var i = 0; i<DICE; i++){
						if(start>=1 && start<11){
							if(start==1){
								playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
							}else if(start==10){
								playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_BIG_JUMP);
							}else{
								playerLeftJump(player, BOARD.CONSTANT.JUMP.LEFT_SMALL_JUMP);
							}
							}else if(start>=11 && start<21){
							if(start==11){
								playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
							}else if(start==20){
								playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_BIG_JUMP);
							}else{
								playerTopJump(player, BOARD.CONSTANT.JUMP.TOP_SMALL_JUMP);
							}

							}else if(start>=21 && start<31){
							if(start==21){
								playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
							}else if(start==30){
								playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_BIG_JUMP);
							}else{
								playerLeftJump(player, BOARD.CONSTANT.JUMP.RIGHT_SMALL_JUMP);
							}
							}else{
							if(start==31){
								playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
							}else if(start==40){
								playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_BIG_JUMP);
							}else{
								playerTopJump(player, BOARD.CONSTANT.JUMP.DOWN_SMALL_JUMP);
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
						alert('Mortage');
					});
					}),
				
			unmortageButton:$(function(){
			
				$('#unmortageButton').click(function() {
					alert('Unmortage');
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
				
			roolDiceButton: $(function(){
				$('#diceButton').click(function() {
					$("#diceImg1").attr("src", "resources/img/board/die"+1+".gif");
					$("#diceImg2").attr("src", "resources/img/board/die"+2+".gif");
					});
			}),
									
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
			// alert("Hello");
			
			BOARD.button.mortageButton;
			BOARD.button.unmortageButton;
			BOARD.button.buildButton;
			BOARD.button.sellButton;
			BOARD.mortageManipolation.mortage;
			BOARD.button.roolDiceButton;

		},
		
		houseManipulation:{
							/**** Build the house  ****/
							buildHouse:function (houseCell){
								if($(houseCell).attr('src')==""){
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
								if($(houseCell).attr('src')=="resources/img/board/big_hotel.png"){
									$(houseCell).attr('src', 'resources/img/board/house3.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house3.png"){
									$(houseCell).attr('src', 'resources/img/board/house2.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house2.png"){
									$(houseCell).attr('src', 'resources/img/board/house1.png');
								}else if($(houseCell).attr('src')=="resources/img/board/house1.png"){
									$(houseCell).attr('src', "");
								}
							}
		},
		
		/* Mortage and unmartage manipolation*/
		mortageManipolation:{
						
				mortage:function (){
					$('#miniCityCell2').click(function() {
						if($(this).hasClass("selectedCell")){
						$(this).removeClass("selectedCell");
						BOARD.button.setEnableButton("#buyButton");
						BOARD.button.setEnableButton("#unmortageButton");
						BOARD.button.setEnableButton("#buildButton");
						BOARD.button.setEnableButton("#diceButton");
						BOARD.button.setEnableButton("#doneButton");
						}else{
						$(this).addClass("selectedCell");
						BOARD.button.setDisableButton("#buyButton");
						BOARD.button.setDisableButton("#unmortageButton");
						BOARD.button.setDisableButton("#buildButton");
						BOARD.button.setDisableButton("#diceButton");
						BOARD.button.setDisableButton("#doneButton");
						}
					});
				}				
		
			}
	};
	BOARD.init();

 
	
