package com.web.app.worldgames.domain.monopoly;

import java.util.Random;

public class RouletteCard extends Cell {

	@Override
	void effectOnPlayer(Player player) {
		// TODO Auto-generated method stub
		Random randRoulette = new Random();
		int roulleteIndex = randRoulette.nextInt(6)+1;
		for (Roulette r : Roulette.values()) {
			if (r.ordinal() == roulleteIndex) {
				if (r.getWhoIsGet().equals("player")) {
					if (r.isAdd()) {
						player.setMoney(player.getMoney() + r.getMoney());
					} else {
						player.setMoney(player.getMoney() - r.getMoney());
					}
				} else if (r.getWhoIsGet().equals("allPlayers")) {

				} else if (r.getWhoIsGet().equals("allPlayersToYou")) {

				} else if (r.getWhoIsGet().equals("none")) {

				}
			}
		}

	}

}
