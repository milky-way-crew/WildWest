package com.web.app.worldgames.domain.monopoly;

public class ColiseumCard extends Cell {
	private boolean payMoney = false;
	private boolean buyFreeCard = false;
	private boolean hasFreeCard = false;

	public void payMoney() {

	}

	@Override
	void effectOnPlayer(Player player) {
		// TODO Auto-generated method stub
		int numberOfCourses = 0;
		if (payMoney) {
			player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_COLISEUM);
		} else if (buyFreeCard) {
			// Find PlayerList and buy card
		} else if (hasFreeCard) {

		} else {
			player.setPosition(player.getPosition());
			numberOfCourses++;
			if ((numberOfCourses == 3) || (Dices.doublePoints())) {
				player.setPosition(player.nextPosition());
			}
		}
	}
}
