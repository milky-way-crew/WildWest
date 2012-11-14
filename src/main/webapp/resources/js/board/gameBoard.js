 function selected(){
 alert('Selected');
 };

$(document).ready(function(){
alert("Hello");

var DICE1=3;
var DICE2=2;

var DICE = DICE1+DICE2;
var JDICE = 1;
var start = 1;

var PLAYER_1 = '#player1';
var PLAYER_2 = '#player2';
var PLAYER_3 = '#player3';
var PLAYER_4 = '#player4';

var DURATION = 500;

/* Value of shift in MOVE methods */
var LEFT_BIG_SHIFT = '-=70%';
var LEFT_SMALL_SHIFT = '-=51%';
var TOP_BIG_SHIFT = '-=550%';
var TOP_SMALL_SHIFT = '-=370%';
var RIGHT_BIG_SHIFT = '+=70%';
var RIGHT_SMALL_SHIFT = '+=51%';
var DOWN_BIG_SHIFT = '+=550%';
var DOWN_SMALL_SHIFT = '+=370%';

/* Value of shift in JUMP methods */
var LEFT_BIG_JUMP = '-=100%';
var LEFT_SMALL_JUMP = '-=74%';
var TOP_BIG_JUMP = '-=110%';
var TOP_SMALL_JUMP = '-=74%';
var RIGHT_BIG_JUMP = '+=100%';
var RIGHT_SMALL_JUMP = '+=74%';
var DOWN_BIG_JUMP = '+=110%';
var DOWN_SMALL_JUMP = '+=74%';

/*  Color of player */

var COLOR_PLAYER_1 = "brown";
var COLOR_PLAYER_2 = "green";
var COLOR_PLAYER_3 = "red";
var COLOR_PLAYER_4 = "violer";


	function playerLeftAnimate(player, shift){
	$(player).animate({left : shift}, DURATION);
	}
	
	function playerTopAnimate(player, shift){
	$(player).animate({top : shift}, DURATION);
	}



	/* Move the player */
	function stepOnBoard(player){
		for (var i = 0; i<DICE; i++){
			if(start>=1 && start<11){
				if(start==1){
					playerLeftAnimate(player, LEFT_BIG_SHIFT);
				}else if(start==10){
					playerLeftAnimate(player, LEFT_BIG_SHIFT);
				}else{
					playerLeftAnimate(player, LEFT_SMALL_SHIFT);
				}
			}else if(start>=11 && start<21){
				if(start==11){
					playerTopAnimate(player, TOP_BIG_SHIFT);
				}else if(start==20){
					playerTopAnimate(player, TOP_BIG_SHIFT);
				}else{
					playerTopAnimate(player, TOP_SMALL_SHIFT);
				}

			}else if(start>=21 && start<31){
				if(start==21){
					playerLeftAnimate(player, RIGHT_BIG_SHIFT);
				}else if(start==30){
					playerLeftAnimate(player, RIGHT_BIG_SHIFT);
				}else{
					playerLeftAnimate(player, RIGHT_SMALL_SHIFT);
				}
			}else{
				if(start==31){
					playerTopAnimate(player, DOWN_BIG_SHIFT);
				}else if(start==40){
					playerTopAnimate(player, DOWN_BIG_SHIFT);
				}else{
					playerTopAnimate(player, DOWN_SMALL_SHIFT);
				}
			}

		start++;
		if(start>40)
		start-=40;
		}
	}
	
		function playerLeftJump(player, shift){
	$(player).css({left : shift});
	}
	
	function playerTopJump(player, shift){
	$(player).css({top : shift});
	}
	
	


	
	
	/* JUMP the player */
	function jumpOnBoard(player){
		for (var i = 0; i<JDICE; i++){
			if(start>=1 && start<11){
				if(start==1){
					playerLeftJump(player, LEFT_BIG_JUMP);
				}else if(start==10){
					playerLeftJump(player, LEFT_BIG_JUMP);
				}else{
					playerLeftJump(player, LEFT_SMALL_JUMP);
				}
			}else if(start>=11 && start<21){
				if(start==11){
					playerTopJump(player, TOP_BIG_JUMP);
				}else if(start==20){
					playerTopJump(player, TOP_BIG_JUMP);
				}else{
					playerTopJump(player, TOP_SMALL_JUMP);
				}

			}else if(start>=21 && start<31){
				if(start==21){
					playerLeftJump(player, RIGHT_BIG_JUMP);
				}else if(start==30){
					playerLeftJump(player, RIGHT_BIG_JUMP);
				}else{
					playerLeftJump(player, RIGHT_SMALL_JUMP);
				}
			}else{
				if(start==31){
					playerTopJump(player, DOWN_BIG_JUMP);
				}else if(start==40){
					playerTopJump(player, DOWN_BIG_JUMP);
				}else{
					playerTopJump(player, DOWN_SMALL_JUMP);
				}
			}

		start++;
		if(start>40)
		start-=40;
		}
	}
	
	/* Method of choosing the player */
	function choosePlayer(playerColor){
	if(playerColor== COLOR_PLAYER_1){
		stepOnBoard(player1);
	}
	else if(playerColor == COLOR_PLAYER_2){
		stepOnBoard(player2);
	}else if(playerColor == COLOR_PLAYER_3){
		stepOnBoard(player3);
	}else{
		stepOnBoard(player4);
	}
	}
	
	/*
	jumpOnBoard(PLAYER_2);
	*/
	

	/*
	stepOnBoard(player1);
	stepOnBoard(PLAYER_2);
	stepOnBoard(player3);
	stepOnBoard(player4);
	*/

	


/* Change the owner of the city!!*/

/*
var ownerCell = '#ownerCell2';

$('#ownerCell2').css('backgroundColor',player1);

var cellColor = function(Cell){
var t = $(Cell).css('backgroundColor');

};
/*

/*
var tt = cellColor(ownerCell);
alert(tt);
*/
/*
function(){
if(player==cellColor(ownerCell)){
alert("!");
}else if{
alert("1");
}
};
*/
/*
$('#ownerCell6').css('backgroundColor',cellColor(ownerCell));
*/

/*
var changeOwner = function (player, Cell){
if(player==cellColor(ownerCell)){
alert("It's you city!");
}else if((cellColor(Cell)==defaultColor){
alert("You can buy!");
}else{
alert("Pay the tax!");
}
};
changeOwner(player1, ownerCell);
*/


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

	
	
	/**** ROLL DICE ****/
	function roolDice(dice1, dice2, player){
	$("#diceButton").click(function(){
		$("#diceImg1").attr("src", "resources/img/board/die"+dice1+".gif");
		$("#diceImg2").attr("src", "resources/img/board/die"+dice2+".gif");
		
		choosePlayer(player);
	
	});
	};
	
	roolDice(DICE1, DICE2, COLOR_PLAYER_1);
	
	/**** BUY ****/
	$(function(){
	$("#buyButton").click(function(){
	$("#ownerCell"+(DICE+1)+"").css('backgroundColor', COLOR_PLAYER_1);
	
	});
	});
	
	
 
 
 /*
  $("#minicell").mouseover(function() {
        $(this).addClass("ui-state-active");
    });
    $("#minicell").mouseout(function() {
        $(this).removeClass("ui-state-active");
    });
    $('#minicell').click(function(event) {
        $(this).toggleClass('selectRow'); 
    });
 */

});

	
