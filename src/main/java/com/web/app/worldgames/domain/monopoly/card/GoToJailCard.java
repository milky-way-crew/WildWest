package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;

public class GoToJailCard extends Cell {
	private static final Logger log = Logger.getLogger(GoToJailCard.class);

	@Override
	public void effectOnPlayer(Player player) {
		//player.setPosition(CellPositions.JAIL);
		log.info("[-----PLAYER:-------] " + player.getColor()
				+ " GET CIRCLE MONEY +$200");
		player.setMoney(player.getMoney() + 200);
	}

	@Override
	public String info() {
		return "You are going to jail. ";
	}

}
