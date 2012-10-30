package com.web.app.worldgames.domain.monopoly;

public enum Chance {
	CHANCE1("You are going to Coliseum", CellPositions.COLISEUM),CHANCE2(
			"You must pay a tax", CellPositions.TAX1),  CHANCE3(
			"You are going to port", CellPositions.PORT1), CHANCE4(
			"You are going to port", CellPositions.PORT2), CHANCE5(
			"You are going to port", CellPositions.PORT3), CHANCE6(
			"You are going to port", CellPositions.PORT4);
	private final String message;
	private final int position;

	private Chance(String message, int position) {
		this.message = message;
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public int getPosition() {
		return position;
	}

}
