package com.web.app.worldgames.domain.monopoly.card;

import java.util.Random;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Chance;
import com.web.app.worldgames.domain.monopoly.Player;

public class ChanceCard extends Cell {
	private static final Logger log = Logger.getLogger(ChanceCard.class);
	String information;
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	private int movePosition;

	public int getMovePosition() {
		return movePosition;
	}

	public void setMovePosition(int movePosition) {
		this.movePosition = movePosition;
	}

	@Override
	public void effectOnPlayer(Player player) {
		Random randChance = new Random();
		int chanceIndex = randChance.nextInt(6);
		for (Chance chance : Chance.values()) {
			if (chance.ordinal() == chanceIndex) {
				log.info("[CHANCE] " + chance.getMessage());
				setInformation(chance.getMessage());
				if (chance.getPosition() < player.getPosition()) {
					log.info("[-----PLAYER:-------] " + player.getColor()
							+ " GET CIRCLE MONEY +$200");
					player.setMoney(player.getMoney() + 200);
				}
				setMovePosition(chance.getPosition());
				//player.setPosition(chance.getPosition());
			}
		}
	}

	@Override
	public String info() {
		return "Chance card";
	}

}
