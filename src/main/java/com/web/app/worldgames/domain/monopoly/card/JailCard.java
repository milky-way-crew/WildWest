package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.PlayGame;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public class JailCard extends Cell {
	// private boolean payMoney = false;
	// private boolean hasFreeCard = false;
	// private boolean wait = false;
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	Map<String, Object> result = new HashMap<String, Object>();

	public void entranceFromJailAndMove(Player player) {
		PlayGame game = new PlayGame();
		player.rollDicesAndMove();
		game.chooseCell(player);

	}

	@Override
	public void effectOnPlayer(Player player) {
		player.setInJail(true);
		//if (player.isHasFreeCard()) {
		if (player.getNumberFreeCard()>0) {
			log.info("[IN JAIL: ] use card");
			player.setInJail(false);
			//player.setHasFreeCard(false);
			player.setNumberFreeCard((player.getNumberFreeCard())-1);
		}
	}

	public boolean canPayRansom(Player player) {
		return (player.checkMoney(CardPrices.RANSOM_FROM_JAIL)) ? true : false;
	}

	public void payRansom(Player player) {
		player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_JAIL);
		player.setInJail(false);
	}

	public boolean rollAndWait(Player player, int dicePoint) {
		if (Player.doublePoints() || player.getCircleInJail() == 3) {
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			player.setPosition(dicePoint);
			log.info("[PLAYER POSITION AFTER JAIL] : " + player.getPosition());
			Cell cell = CardFactory.chooseCard(player);
			cell.effectOnPlayer(player);
			player.setCircleInJail(0);
			player.setInJail(false);
			log.info("[JAIL_MESSAGE] : you are going from jail");
		} else {
			player.setPosition(CellPositions.JAIL);
			player.setInJail(true);
			player.addCircleInJail();
			result.put("Move to:", 0);
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			log.info("[JAIL] : You stay in jail");
		}
		return !player.isInJail();
	}

	@Override
	public String info() {
		return "Player is in jail";
	}

}
