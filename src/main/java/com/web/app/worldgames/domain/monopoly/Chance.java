package com.web.app.worldgames.domain.monopoly;

public enum Chance {
	CHANCE1("Go directly to jail", CellPositions.JAIL), CHANCE2(
			"Go directly to free station", CellPositions.FREE_STATION), CHANCE3(
			"Go directly to Stonehenge star", CellPositions.STONES1), CHANCE4(
			"Go directly to Stonehenge star", CellPositions.STONES2), CHANCE5(
			"Go directly to Stonehenge star", CellPositions.STONES3), CHANCE6(
			"Go directly to Stonehenge star", CellPositions.STONES4);
	
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
