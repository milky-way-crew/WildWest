package com.web.app.worldgames.domain.monopoly;

public enum Rails {
RAIL1("Stonehenge star", CardPrices.RAIL1, CellPositions.STONES1),
RAIL2("Stonehenge star", CardPrices.RAIL2,CellPositions.STONES2),
RAIL3("Stonehenge star", CardPrices.RAIL3,CellPositions.STONES3),
RAIL4("Stonehenge star", CardPrices.RAIL4,CellPositions.STONES4);
	private final String name;
	private final int price;
	private final int position;
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
