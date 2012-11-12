package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Chance;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class ChanseCard extends Cell {
	Map<String, Chance> chanceInstance = new HashMap<String, Chance>();

	public Map<String, Chance> chance() {
		Random randChance = new Random();
		int chanceIndex = randChance.nextInt(6);
		for (Chance chance : Chance.values()) {
			if (chance.ordinal() == chanceIndex) {
				System.out.println(chance.getMessage());
				chanceInstance.put(chance.getMessage(), chance);
				return chanceInstance;
			}
		}
		return chanceInstance;
	}

	public Map<String, Chance> getChanceInstance() {
		return chanceInstance;
	}

	@Override
	public void effectOnPlayer(Player player) {
		// System.out.println("You have a chance: ");
		// Random randChance = new Random();
		// int chanceChoose = randChance.nextInt(6);
		Map<String, Chance> chanceChoosen = getChanceInstance();
		for (Chance chance : Chance.values()) {
			if (chanceChoosen.containsValue(chance)) {
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
		chanceChoosen.clear();
	}

	@Override
	public	String info() {
		for (Chance chance : Chance.values()) {
			if (getChanceInstance().containsValue(chance)) {
				return getChanceInstance().get(chance).getMessage();
			}
	}
		return null;
	}
}
