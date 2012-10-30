package com.web.app.worldgames.domain.monopoly;

public class ColiseumCard extends Cell {
	private boolean payMoney = false;
	private boolean buyCard = false;
	private boolean hasCard = false;

	public void payMoney() {

	}

	@Override
	void effectOnPlayer(Player player) {
		// TODO Auto-generated method stub
		int numberOfCourses = 0;
		if (payMoney) {
			player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_COLISEUM);
		} else if (buyCard) {
			// Find PlayerList and buy card
		} else if (hasCard) {

		} else {
			player.setPosition(player.getPosition());
			i++;
			if(i==3 || )
		}
	}
}
