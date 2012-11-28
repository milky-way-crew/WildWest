package com.web.app.worldgames.domain.monopoly;

public enum CommunityChest {
	COMMUNITY_CHEST1("Bank paid $100 to you", 100, true, "money"), COMMUNITY_CHEST2(
			"You've won at lottery $200", 200, true, "money"), COMMUNITY_CHEST3(
					"You've lost $300", 300, false, "money"), COMMUNITY_CHEST4(
							"You must pay tax of $150", 150, false, "money"), COMMUNITY_CHEST5(
									"Bank paid $50 to you", 50, true, "money"), COMMUNITY_CHEST6(
											"Pay poor tax of $50", 50, false, "money"), COMMUNITY_CHEST7(
													"You've got free card to entrace from Jail ", 0, true,
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
