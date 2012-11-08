package com.web.app.worldgames.domain.monopoly;

public enum CommunityChest {
	COMMUNITY_CHEST1("You've won $300", 300, true, "player"), COMMUNITY_CHEST2(
			"You've won at lottery $500", 500, true, "player"), COMMUNITY_CHEST3(
			"You've lost $200", 200, false, "player"), COMMUNITY_CHEST4(
			"You've gift $100 all players", 100, false, "allPlayers"), COMMUNITY_CHEST5(
			"Each players have gave $100 for you", 100, true, "allPlayers"), COMMUNITY_CHEST6(
			"Pay poor tax of $30", 30, false, "player"), COMMUNITY_CHEST7(
			"You've got free card to entrace from Coliseum ", 0, true,
			"coliseum");
	private final String message;
	private final int money;
	private final boolean isAdd;
	private final String whoIsGet;

	CommunityChest(String message, int money, boolean isAdd, String whoIsGet) {
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
