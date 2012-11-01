package com.web.app.worldgames.domain.monopoly;

public class TaxCard extends Cell {
	private int tax;

	public int getPrice() {
		return tax;
	}

	public void setPrice(int tax) {
		this.tax = tax;
	}

	public TaxCard(int tax) {
		super();
		this.tax = tax;
	}

	@Override
	void effectOnPlayer(Player player) {
		if (player.getPosition() == CellPositions.TAX1 || player.getPosition() == CellPositions.TAX2) {
			player.setMoney(player.getMoney() - CardPrices.TAX);
		}

	}

}
