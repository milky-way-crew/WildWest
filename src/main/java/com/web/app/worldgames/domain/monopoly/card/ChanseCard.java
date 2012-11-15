package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Chance;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class ChanseCard extends Cell {
	private static final Logger log = Logger.getLogger(ChanseCard.class);
	private Map<String, Chance> chanceInstance = new HashMap<String, Chance>();

	public Cell getDirectCard(Player player) {
		Map<String, Chance> chanceChoosen = getChanceInstance();
		for (Chance chance : Chance.values()) {
			if (chanceChoosen.containsValue(chance)) {
				if (chance.getPosition() == CellPositions.JAIL) {
					player.setPosition(CellPositions.JAIL);
					return new JailCard();
				} else if (chance.getPosition() == CellPositions.FREE_STATION) {
					player.setPosition(CellPositions.FREE_STATION);
					return new FreeStation();
				} else if (StartGame.boardRails.containsKey(chance
						.getPosition())) {
					SellableCard cell = StartGame.boardRails.get(chance
							.getPosition());
					player.setPosition(cell.getPosition());
					return cell;
				}
			}
		}
		return null;
	}

	public Map<String, Chance> getChanceInstance() {
		return chanceInstance;
	}

	public void clearChanceMap() {
		chanceInstance.clear();
	}

	@Override
	public void effectOnPlayer(Player player) {
		Random randChance = new Random();
		int chanceIndex = randChance.nextInt(6);
		for (Chance chance : Chance.values()) {
			if (chance.ordinal() == chanceIndex) {
				log.info("[CHANCE] " + chance.getMessage());
				chanceInstance.put(chance.getMessage(), chance);
			}
		}

	}

	@Override
	public String info() {
		for (Chance chance : Chance.values()) {
			if (getChanceInstance().containsValue(chance)) {
				return getChanceInstance().get(chance).getMessage();
			}
		}
		return null;
	}

//public static void main(String[] args) {
//	Player player = new Player("r1", 3, 1000, PlayerColors.PLAYER1);
//	ChanseCard c = new ChanseCard();
//	c.effectOnPlayer(player);
//	System.out.println(c.getChanceInstance());
//	c.getDirectCard(player);
//	c.clearChanceMap();
//	c.effectOnPlayer(player);
//	System.out.println(c.getChanceInstance());
//	c.getDirectCard(player);
//	
//}
}
