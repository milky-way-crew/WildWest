
var GAME = {};

	GAME ={
	
		
	}

$(document).ready(function(){
alert("Hello");



var DICE = 2;
var start = 1;

var PLAYER_1 = '#player1';
var PLAYER_2 = '#player2';
var PLAYER_3 = '#player3';
var PLAYER_4 = '#player4';

var DURATION = 500;
var LEFT_BIG_SHIFT = '-=70%';
var LEFT_SMALL_SHIFT = '-=51%';
var TOP_BIG_SHIFT = '-=550%';
var TOP_SMALL_SHIFT = '-=370%';
var RIGHT_BIG_SHIFT = '+=70%';
var RIGHT_SMALL_SHIFT = '+=51%';
var DOWN_BIG_SHIFT = '+=550%';
var DOWN_SMALL_SHIFT = '+=370%';



	function playerLeftAnimate(player, shift){
	$(player).animate({left : shift}, DURATION);
	}
	
	function playerTopAnimate(player, shift){
	$(player).animate({top : shift}, DURATION);
	}



	/* Move the player in %*/
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
	
/*
stepOnBoard(player1);
*/
stepOnBoard(PLAYER_2);
	function playerPosition(){
	$(PLAYER_2).hide('slow');
	}
		playerPosition();
	function playerPosition(){	
			$(PLAYER_2).show('slow');
	}
		playerPositionShow();
/*
stepOnBoard(player3);
stepOnBoard(player4);
*/




/* Change the owner of the city!!
var defaultColor = "rgb(204,231,208)";
var COLOR_PLAYER_1 = "rgb(188,127,60)";
var COLOR_PLAYER_2 = "rgb(181,185,61)";
var COLOR_PLAYER_3 = "rgb(185,60,71)"
var COLOR_PLAYER_4 = "rgb(168,61,186)"

var ownerCell = '#ownerCell2';

$('#ownerCell2').css('backgroundColor',player1);

var cellColor = function(Cell){
var t = $(Cell).css('backgroundColor');
alert(t);
return t;
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



});