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
		boolean check = true;
		if (player.checkMoney(player, CardPrices.TAX)) {
			player.setMoney(player.getMoney() - CardPrices.TAX);
			System.out.println("You must pay a tax: " + CardPrices.TAX);
			System.out.println("Now you have: " + player.getMoney());
		} else {
			while (check) {
				if (!player.listPropertyForMortage(player).isEmpty()) {
					player.mortageAction(player);
					if (player.checkMoney(player, CardPrices.TAX)) {
						player.setMoney(player.getMoney() - CardPrices.TAX);
						check = false;
					} else {
						check = true;
					}
				} else {
					System.out.println("you haven't object");
					player.setLoss(true);
					System.out.println("loss: " + player.isLoss());
					check = false;
				}
			}
		}
	}

}
