package com.web.app.worldgames.domain.monopoly;

public class GoToJailCard extends Cell {

	@Override
	void effectOnPlayer(Player player) {
		System.out.println("You are going to jail. ");
		player.setPosition(CellPositions.JAIL);
		JailCard jailCard = new JailCard();
		jailCard.effectOnPlayer(player);
	}

}
