package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.Player;

public class FreeStation extends Cell {
	public FreeStation() {
	}

	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(player.getMoney());
		player.setPosition(player.getPosition());
	}

	@Override
	public String info() {
		return "You are at the free sation:";
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
