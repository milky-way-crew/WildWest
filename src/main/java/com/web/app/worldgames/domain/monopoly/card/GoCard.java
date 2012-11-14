package com.web.app.worldgames.domain.monopoly.card;

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

}
