package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.Player;

public class StartCard extends Cell {
	private static final int START_MONEY = 1500;
	private static final int START_POSITION = 1;

	
	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(START_MONEY);
		player.setPosition(START_POSITION);
		player.setHasFreeCard(false);
	}


	@Override
	public String info() {
		return "You can start: ";
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
