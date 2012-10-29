package com.web.app.worldgames.domain.monopoly;

public class TaxCard extends Cell {
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private TaxCard(int price) {
		super();
		this.price = price;
	}

	@Override
	void effectOnPlayer(Player player) {
		player.setMoney(player.getMoney() - getPrice());
	}

}
