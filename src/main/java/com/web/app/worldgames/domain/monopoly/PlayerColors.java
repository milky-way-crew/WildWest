package com.web.app.worldgames.domain.monopoly;

public enum PlayerColors {
	PLAYER1("RED"),PLAYER2("GREEN"),PLAYER3("BLUE"), PLAYER4("YELLOW");
	private String color;

	PlayerColors(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

}
