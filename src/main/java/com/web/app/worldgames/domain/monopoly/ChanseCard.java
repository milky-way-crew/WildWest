package com.web.app.worldgames.domain.monopoly;

import java.util.Random;

public class ChanseCard extends Cell {

    @Override
    void effectOnPlayer(Player player) {
	System.out.println("You have a chance: ");
	Random randChance = new Random();
	int chanceChoose = randChance.nextInt(6);
	for (Chance chance : Chance.values()) {
	    if (chance.ordinal() == chanceChoose) {
		System.out.println(chance.getMessage());
		player.setPosition(chance.getPosition());
		if (player.getPosition() == CellPositions.JAIL) {
		    JailCard jailCard = new JailCard();
		    jailCard.effectOnPlayer(player);
		} else if (player.getPosition() == CellPositions.FREE_STATION) {
		    FreeStation freeStation = new FreeStation();
		    freeStation.effectOnPlayer(player);
		} else if (StartGame.boardRails.containsKey(player
			.getPosition())) {
		    SellableCard cell = StartGame.boardRails.get(player
			    .getPosition());
		    cell.effectOnPlayer(player);
		}
	    }
	}
    }

}
