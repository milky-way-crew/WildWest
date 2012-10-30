package com.web.app.worldgames.domain.monopoly;

import java.util.Random;

public class ChanseCard extends Cell {

	@Override
	void effectOnPlayer(Player player) {
		Random randChance = new Random();
		int chanceChoose = randChance.nextInt(8)+1;
		for(Chance chance: Chance.values()){
			if(chance.ordinal()==chanceChoose){
				player.setPosition(chance.getPosition());
			}
		}
	}

}
