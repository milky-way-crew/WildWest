package com.web.app.worldgames.domain.monopoly;

public enum PlayerColors {
	PLAYER1("BROWN"),PLAYER2("GREEN"),PLAYER3("RED"), PLAYER4("VIOLET");
	private String color;

	PlayerColors(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

}
