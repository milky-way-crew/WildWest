package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Player;

public class FreeStation extends Cell {
	private final static Logger log = Logger.getLogger(FreeStation.class);
	public FreeStation() {
	}

	@Override
	public void effectOnPlayer(Player player) {
		//player.setMoney(player.getMoney());
		player.setPosition(player.getPosition());
	}

	@Override
	public String info() {
		return "You are at the free sation:";
	}


}
