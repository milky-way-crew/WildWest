package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public class JailCard extends Cell {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	Map<String, Object> result = new HashMap<String, Object>();

	@Override
	public void effectOnPlayer(Player player) {
		player.setInJail(true);
		if (player.getNumberFreeCard() > 0) {
			log.info("[IN JAIL: ] use card");
			player.setInJail(false);
			player.setNumberFreeCard((player.getNumberFreeCard()) - 1);
		}
	}

	public boolean canPayRansom(Player player) {
		return (player.checkMoney(CardPrices.RANSOM_FROM_JAIL)) ? true : false;
	}

	public void payRansom(Player player) {
		player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_JAIL);
		player.setInJail(false);
	}

	public Map<String, Object> rollAndWait(Player player, int dicePoint) {
		Map<String, Object> result = new HashMap<String, Object>();
		String message = null;
		Map<String, Object> buttons = new HashMap<String, Object>();
		int go = 0;
		if (player.doublePoints() || player.getCircleInJail() == 3) {
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			player.setPosition(dicePoint);
			log.info("[PLAYER POSITION AFTER JAIL] : " + player.getPosition());
			// Cell cell = CardFactory.chooseCard(player);
			// cell.effectOnPlayer(player);
			player.setCircleInJail(0);
			player.setInJail(false);
			go = dicePoint;
			message = "You are going from jail";
			log.info("[JAIL_MESSAGE] : you are going from jail");
			buttons.put(ButtonsLabel.DONE, true);
		} else {
			player.setPosition(CellPositions.JAIL);
			player.setInJail(true);
			player.addCircleInJail();
			// result.put("Move to:", 0);
			message = "You saty in jail";
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			log.info("[JAIL] : You stay in jail");
			buttons.put(ButtonsLabel.DONE, player.canContinueGame());
			buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
			buttons.put(ButtonsLabel.SELL, false);
		}
		result.put("buttons", buttons);
		result.put("messages", message);
		result.put("player", player.getColor());
		result.put("money", player.getMoney());
		result.put("go", go);
		return result;
	}

	@Override
	public String info() {
		return "Player is in jail";
	}

}
