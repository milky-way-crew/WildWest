package com.web.app.worldgames.domain.monopoly;

public class FreeStation extends Cell {
	public FreeStation() {
	}

	@Override
	void effectOnPlayer(Player player) {
		System.out.println("You are at the free sation: ");
		player.setMoney(player.getMoney());
		player.setPosition(player.getPosition());
	}

}
