package com.web.app.worldgames.domain.monopoly;

public class StartCard extends Cell {
	private static final int START_MONEY = 500;
	private static final int START_POSITION = 1;

	
	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(START_MONEY);
		player.setPosition(START_POSITION);
	}

}
