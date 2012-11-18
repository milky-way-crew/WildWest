package com.web.app.worldgames.domain.monopoly.card;

import java.util.Random;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CommunityChest;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class CommunityChestCard extends Cell {
	private final static Logger log = Logger.getLogger(CommunityChestCard.class);

//	public void chestActivity(Player player) {
//		boolean check = true;
//		CommunityChest chest = getRandomChestCard();
//		int price = chest.getMoney();
//	}

	public CommunityChest getRandomChestCard() {
		Random randChest = new Random();
		int chestIndex = randChest.nextInt(7);
		for (CommunityChest chest : CommunityChest.values()) {
			if (chest.ordinal() == chestIndex) {
				log.info("[MESSAGE (from community chest)]: " + chest.getMessage());
				return chest;
			}
		}
		return null;
	}

	public void service(Player player, CommunityChest chest) {
	//	StartGame playersInGame = new StartGame();
		if (chest.getWhoIsGet().equals("player")) {
			if (chest.isAdd()) {
				player.setMoney(player.getMoney() + chest.getMoney());
			} else {
				player.setMoney(player.getMoney() - chest.getMoney());
			}
		} 
//			else if (chest.getWhoIsGet().equals("allPlayers")) {
//			if (chest.isAdd()) {
//				for (Player players : playersInGame.playersPermanentlyList()) {
//					if (!players.equals(player)) {
//						players.setMoney(players.getMoney() - chest.getMoney());
//						player.setMoney(player.getMoney() + chest.getMoney());
//					}
//				}
//			} else {
//				for (Player players : playersInGame.playersPermanentlyList()) {
//					if (!players.equals(player)) {
//						players.setMoney(players.getMoney() + chest.getMoney());
//						player.setMoney(player.getMoney() - chest.getMoney());
//					}
//				}
//			}
//		} 
		else if (chest.getWhoIsGet().equals("coliseum")) {
			player.setNumberFreeCard((player.getNumberFreeCard())+1);
		}
	}

	@Override
	public void effectOnPlayer(Player player) {
		service(player, getRandomChestCard());
	}

	@Override
	public String info() {
		return "Community chest: "+getRandomChestCard().getMessage();
	}

	// public static void main(String[] args) {
	// Player p1 = new Player("sdf", 23, 1200, PlayerColors.PLAYER1);
	// CommunityChestCard c = new CommunityChestCard();
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// c.effectOnPlayer(p1);
	// System.out.println(p1.getMoney()+" card "+p1.getNumberFreeCard());
	// }
}
