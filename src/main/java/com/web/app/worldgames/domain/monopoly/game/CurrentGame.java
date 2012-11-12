package com.web.app.worldgames.domain.monopoly.game;

import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.Cell;

public class CurrentGame {
	private int diceOne;
	private int diceTwo;
	private Cell cell;
	private Player player;
	private String message;

	public CurrentGame(int diceOne, int diceTwo, Cell cell, Player player,
			String message) {
		super();
		this.diceOne = diceOne;
		this.diceTwo = diceTwo;
		this.cell = cell;
		this.player = player;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
