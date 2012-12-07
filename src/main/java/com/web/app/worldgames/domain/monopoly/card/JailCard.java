package com.web.app.worldgames.domain.monopoly.card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.util.logging.PlatformLogger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.PlayGame;
import com.web.app.worldgames.domain.monopoly.Player;

public class JailCard extends Cell {
	private boolean payMoney = false;
	private boolean hasFreeCard = false;
	private boolean wait = false;

	public void entranceFromJailAndMove(Player player) {
		PlayGame game = new PlayGame();
		player.rollDicesAndMove();
		game.chooseCell(player);

	}

	@Override
	public void effectOnPlayer(Player player) {
		player.setInJail(true);
		if(player.isHasFreeCard()){
			player.setInJail(false);
			player.setHasFreeCard(false);
		}
//		System.out
//				.println("You are in the jail: you may --pay-1- a ransom or use a --card-2- or --roll dices-3: ");
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String key = null;
//		try {
//			key = in.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if (key.equals("1")) {
//			payMoney = true;
//		} else if (key.equals("2")) {
//			hasFreeCard = true;
//		} else if (key.equals("3")) {
//			wait = true;
//		}
//
//		if (payMoney) {
//			if (player.checkMoney(player, CardPrices.RANSOM_FROM_JAIL)) {
//				player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_JAIL);
//				player.setInJail(false);
//				System.out.println("you are going from jail");
//				entranceFromJailAndMove(player);
//			} else {
//				System.out.println("You haven't money. Stay in the jail.");
//				player.setInJail(true);
//			}
//		} else if (hasFreeCard) {
//			if (player.isHasFreeCard()) {
//				System.out.println("You use the free card to exit from jail");
//				System.out.println("you are going from jail");
//				player.setInJail(false);
//				player.setHasFreeCard(false);
//			} else {
//				player.setInJail(true);
//				System.out.println("You haven't card");
//			}
//		} else if (wait) {
//			int points = player.rollDicesAndWait();
//			if ((Player.doublePoints()) || (player.getCircleInJail()) == 3) {
//				player.setPosition(points);
//				player.setInJail(false);
//				System.out.println("you are going from jail");
//			} else {
//				player.setPosition(CellPositions.JAIL);
//				player.setInJail(true);
//				player.circleInJail();
//				System.out.println("You stay in jail. ");
//			}
//		}
	}

	public boolean canPayRansom(Player player) {
		return (player.checkMoney(player, CardPrices.RANSOM_FROM_JAIL)) ? true
				: false;
	}

	public void payRansom(Player player) {
		player.setMoney(player.getMoney() - CardPrices.RANSOM_FROM_JAIL);
		player.setInJail(false);
		// System.out.println("you are going from jail");
		// entranceFromJailAndMove(player);
	}
//	public void useCard(Player player){
//		player.setInJail(false);
//		player.setHasFreeCard(false);
//	}
	public void rollAndWait(Player player){
		
	}
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
}
=======
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
