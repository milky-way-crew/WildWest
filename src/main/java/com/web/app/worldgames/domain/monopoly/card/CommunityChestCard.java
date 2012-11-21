package com.web.app.worldgames.domain.monopoly.card;

import java.util.Random;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CommunityChest;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class CommunityChestCard extends Cell {
	private final static Logger log = Logger
			.getLogger(CommunityChestCard.class);
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

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

	public void service(Player player, CommunityChest chest) {
		if (chest.getWhoIsGet().equals("player")) {
			if (chest.isAdd()) {
				player.setMoney(player.getMoney() + chest.getMoney());
			} else {
				player.setMoney(player.getMoney() - chest.getMoney());
			}
		} else if (chest.getWhoIsGet().equals("coliseum")) {
			player.setNumberFreeCard((player.getNumberFreeCard()) + 1);
		}
		this.setMsg(chest.getMessage());
		log.info("[CHANCE:] "+msg);
	}

	@Override
	public void effectOnPlayer(Player player) {
		log.info("Player money before chest"+player.getMoney());
		service(player, getRandomChestCard());
		log.info("Player money after chest"+player.getMoney());
	}

	@Override
	public String info() {
		return "Community chest ";
	}
}
