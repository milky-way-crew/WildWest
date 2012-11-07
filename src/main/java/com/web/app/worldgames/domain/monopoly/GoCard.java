package com.web.app.worldgames.domain.monopoly;

public class GoCard extends Cell{
	@Override
	void effectOnPlayer(Player player) {
		System.out.println("You stay at Go. You have $200");
		player.setMoney(player.getMoney()+CardPrices.GO);
	}
}
