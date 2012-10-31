package com.web.app.worldgames.domain.monopoly;

import java.util.Random;

public class Dices {
	private static Random randDice = new Random();

	public static int throwDiceOne() {
		return randDice.nextInt(6) + 1;
	}

	public static int throwDiceTwo() {
		return randDice.nextInt(6) + 1;
	}
	public static int dicePosition(){
		return throwDiceOne()+throwDiceTwo();
	}
	public static boolean doublePoints(){
		return (throwDiceOne()==throwDiceTwo())?true:false;
	}
}
