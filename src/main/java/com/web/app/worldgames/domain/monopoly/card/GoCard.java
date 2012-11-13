package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;

public class GoCard extends Cell {
	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(player.getMoney() + CardPrices.GO);
	}

	@Override
	public String info() {
		return "You stay at Go. You have $200";
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
