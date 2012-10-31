package com.web.app.worldgames.domain.monopoly;

public class Player {
	private String name;
	private int position;
	private int money;

	public Player(String name, int position, int money) {
		this.name = name;
		this.position = position;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int nextPosition() {
		int position = getPosition() + Dices.dicePosition();
		if (position > 40) {
			position = position - 40;
		}
		return position;
	}

	public void action(Cell cell) {
	};

	public static Player getPlayer(Player player) {
		return player;
	}
}