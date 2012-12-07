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
	String msg = null;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	/**
	 * This method define if player has free card, he release from prison
	 * else he pay ransom or roll dices three times
	 */
	public void effectOnPlayer(Player player) {
		player.setInJail(true);
		if (player.getNumberFreeCard() > 0) {
			player.setInJail(false);
			player.setNumberFreeCard((player.getNumberFreeCard()) - 1);
			this.setMsg("You had a free card and you are going from jail");
		}
		player.setRolled(false);
	}

	/**
	 * This method define if player can pay ransom
	 * 
	 * @param player
	 * @return true if he can
	 */
	public boolean canPayRansom(Player player) {
		return player.checkMoney(CardPrices.RANSOM_FROM_JAIL)
				&& player.isInJail();
		// return (player.checkMoney(CardPrices.RANSOM_FROM_JAIL) && player
		// .isInJail()) ? true : false;
	}

	public void payRansom(Player player) {
		player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_JAIL);
		this.setMsg("You paid ransom");
		player.setInJail(false);
		player.setRolled(false);
	}

	/**
	 * Method executes roll dices if player refuse or cann't pay ransom
	 * 
	 * @param player
	 * @param dicePoint
	 * 
	 */
	public Map<String, Object> rollAndWait(Player player, int dicePoint) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		String message = null;
		boolean move = false;
		if (player.doublePoints() || player.getCircleInJail() == 3) {
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			player.setCircleInJail(0);
			player.setInJail(false);
			move = true;
			response.put("was", CellPositions.JAIL);
			response.put("dice1", player.getDiceOne());
			response.put("dice2", player.getDiceTwo());
//			response.put("dice1", dicePoint);
//			response.put("dice2", 0);
			response.put("move", move);
			message = "You are going from jail";
			buttons.put(ButtonsLabel.PAY, false);
			player.setPosition(dicePoint);
			log.info("[PLAYER POSITION AFTER JAIL] : " + player.getPosition());
		} else {
			player.setPosition(CellPositions.JAIL);
			player.setInJail(true);
			player.addCircleInJail();
			message = "You stay in jail";
			log.info("[CIRCLE IN JAIL] : " + player.getCircleInJail());
			log.info("[JAIL] : You stay in jail");
			response.put("dice1", player.getDiceOne());
			response.put("dice2", player.getDiceTwo());
			response.put("move", false);
			buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
			buttons.put(ButtonsLabel.SELL, player.canSell());
			buttons.put(ButtonsLabel.PAY, false);
		}
		buttons.put(ButtonsLabel.DONE, true);
		response.put("buttons", buttons);
		response.put("messages", message);
		response.put("player", player.getColor());
		response.put("money", player.getMoney());
		return response;
	}

	@Override
	public String info() {
		return "In jail";
	}

}
