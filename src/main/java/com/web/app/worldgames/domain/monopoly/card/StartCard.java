package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.Player;

public class StartCard extends Cell {
	private static final int START_MONEY = 1500;
	private static final int START_POSITION = 1;

	
	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(START_MONEY);
		player.setPosition(START_POSITION);
		//player.setHasFreeCard(false);
	}


	@Override
	public String info() {
		return "You can start: ";
	}
}
