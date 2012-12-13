package com.web.app.worldgames.domain.monopoly.card;

import java.util.Random;

import com.web.app.worldgames.domain.monopoly.CommunityChest;
import com.web.app.worldgames.domain.monopoly.Player;

/**
 * 
 * @author Inna
 * 
 */
public class CommunityChestCard extends Cell {
	private final static String PLAYER_GET_MONEY = "money";
	private final static String PLAYER_GET_CARG = "card";
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * @return random CommunityChest
	 */
	public CommunityChest getRandomChestCard() {
		Random randChest = new Random();
		int chestIndex = randChest.nextInt(7);
		for (CommunityChest chest : CommunityChest.values()) {
			if (chest.ordinal() == chestIndex) {
				return chest;
			}
		}
		return null;
	}

	/**
	 * The execute method add or subtract players money; or player wins a
	 * release from prison card
	 * 
	 * @param player
	 * @param chest
	 */
	public void service(Player player, CommunityChest chest) {
		if (chest.getWhatIsGet().equals(PLAYER_GET_MONEY)) {
			if (chest.isAdd()) {
				player.setMoney(player.getMoney() + chest.getMoney());
			} else {
				player.setMoney(player.getMoney() - chest.getMoney());
			}
		} else if (chest.getWhatIsGet().equals(PLAYER_GET_CARG)) {
			player.setNumberFreeCard((player.getNumberFreeCard()) + 1);
		}
		this.setMsg(chest.getMessage());
	}

	@Override
	public void effectOnPlayer(Player player) {
		service(player, getRandomChestCard());
	}

	@Override
	public String info() {
		return "";
	}
}
