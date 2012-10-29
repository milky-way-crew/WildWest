package com.web.app.worldgames.domain.monopoly;

public enum Roulette {
	ROULETTE1("You've won $500", 500, true, "player"), ROULETTE2(
			"You've won at lottery $600", 300, true, "player"), ROULETTE3(
			"You've lost $400", 400, false, "player"), ROULETTE4(
			"You've gift $600 all players", 600, false, "allPlayers"), ROULETTE5(
			"Each players have gave $100 for you", 100, true, "allPlayers"), ROULETTE6(
			"You are unlucky. You haven't get money", 0, false, "none");
	private final String message;
	private final int money;
	private final boolean isAdd;
	private final String whoIsGet;

	Roulette(String message, int money, boolean isAdd, String whoIsGet) {
		this.message = message;
		this.money = money;
		this.isAdd = isAdd;
		this.whoIsGet = whoIsGet;
	}

	public String getMessage() {
		return message;
	}

	public int getMoney() {
		return money;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public String getWhoIsGet() {
		return whoIsGet;
	}

}
