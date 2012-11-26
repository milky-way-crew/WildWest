package com.web.app.worldgames.domain.monopoly;

public enum Rails {
RAIL1("Monopoly Rail 1", CardPrices.RAIL1, CellPositions.MONOPOLY_RAIL1),
RAIL2("Monopoly Rail 2", CardPrices.RAIL2,CellPositions.MONOPOLY_RAIL2),
RAIL3("Monopoly Rail 3", CardPrices.RAIL3,CellPositions.MONOPOLY_RAIL3),
RAIL4("Monopoly Rail 4", CardPrices.RAIL4,CellPositions.MONOPOLY_RAIL4);
	private final String name;
	private final int price;
	private int position;
	private Rails(String name, int price, int position) {
		this.name = name;
		this.price = price;
		this.position=position;
	}
	public String getName() {
		return name;
	}
	public  int getPrice() {
		return price;
	}
	public int getPosition() {
		return position;
	}
	
}
