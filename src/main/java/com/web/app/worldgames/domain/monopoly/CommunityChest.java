package com.web.app.worldgames.domain.monopoly;

public enum CommunityChest {
	COMMUNITY_CHEST1("Player has won $100", 100, true, "money"), COMMUNITY_CHEST2(
			"Player has won at lottery $200", 200, true, "money"), COMMUNITY_CHEST3(
					"Player was the victim of a robbery", 300, false, "money"), COMMUNITY_CHEST4(
							"Player must pay tax of $150", 150, false, "money"), COMMUNITY_CHEST5(
									"Player has got $50", 50, true, "money"), COMMUNITY_CHEST6(
											"Pay poor tax of $50", 50, false, "money"), COMMUNITY_CHEST7(
													"Player has got free card to entrace from Jail ", 0, true,
													"card");
	private final String message;
	private final int money;
	private final boolean isAdd;
	private final String whatIsGet;

	CommunityChest(String message, int money, boolean isAdd, String whatIsGet) {
		this.message = message;
		this.money = money;
		this.isAdd = isAdd;
		this.whatIsGet = whatIsGet;
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

	public String getWhatIsGet() {
		return whatIsGet;
	}

}