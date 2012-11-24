package com.web.app.worldgames.domain.monopoly;

public enum CommunityChest {
//	COMMUNITY_CHEST1("Bank paid $100000 to you", 100000, true, "player"), COMMUNITY_CHEST2(
//			"You've won at lottery $200000", 200000, true, "player"), COMMUNITY_CHEST3(
//			"You've lost $300000", 300000, false, "player"), COMMUNITY_CHEST4(
//			"You must pay tax of $150000", 150000, false, "player"), COMMUNITY_CHEST5(
//			"Bank paid $50000 to you", 50000, true, "player"), COMMUNITY_CHEST6(
//			"Pay poor tax of $50000", 50000, false, "player"), COMMUNITY_CHEST7(
//			"You've got free card to entrace from Coliseum ", 0, true,
//			"coliseum");
	COMMUNITY_CHEST1("Bank paid $100 to you", 100, true, "player"), COMMUNITY_CHEST2(
			"You've won at lottery $200", 200, true, "player"), COMMUNITY_CHEST3(
					"You've lost $300", 300, false, "player"), COMMUNITY_CHEST4(
							"You must pay tax of $150", 150, false, "player"), COMMUNITY_CHEST5(
									"Bank paid $50 to you", 50, true, "player"), COMMUNITY_CHEST6(
											"Pay poor tax of $50", 50, false, "player"), COMMUNITY_CHEST7(
													"You've got free card to entrace from Jail ", 0, true,
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
