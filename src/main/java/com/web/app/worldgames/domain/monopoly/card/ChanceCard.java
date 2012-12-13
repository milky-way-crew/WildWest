package com.web.app.worldgames.domain.monopoly.card;

import java.util.Random;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Chance;
import com.web.app.worldgames.domain.monopoly.Player;

public class ChanceCard extends Cell {
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
				setInformation(chance.getMessage());
				if (chance.getPosition() < player.getPosition()) {
					player.setMoney(player.getMoney() + CardPrices.CIRCLE_MONEY);
				}
				setMovePosition(chance.getPosition());
			}
		}
	}

	@Override
	public String info() {
		return "Chance card";
	}

}
