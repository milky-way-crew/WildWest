package com.web.app.worldgames.domain.monopoly;

import java.util.Random;

public class CommunityChestCard extends Cell {

	public void chestActivity(Player player) {
		boolean check = true;
		CommunityChest chest = getRandomChestCard();
		int price = chest.getMoney();
		if(!chest.isAdd()){
			if (player.checkMoney(player, price)) {
				service(player, chest);
			} else {
				while (check) {
					if (!player.listPropertyForMortage(player).isEmpty()){
						
					player.mortageAction(player);
					if (player.checkMoney(player, price)) {
						service(player, chest);
						check = false;
					} else {
						check = true;
					}
					}else {
						System.out.println("you haven't object");
						player.setLoss(true);
						System.out.println("loss: "+player.isLoss());
						check = false;
					}
					
				}
			}
		}
	}

	public CommunityChest getRandomChestCard() {
		Random randChest = new Random();
		int chestIndex = randChest.nextInt(7);
		for (CommunityChest chest : CommunityChest.values()) {
			if (chest.ordinal() == chestIndex) {
				System.out.println(chest.getMessage());
				return chest;
			}
		}
		return null;
	}

	public void service(Player player, CommunityChest chest) {
		StartGame playersInGame = new StartGame();
		if (chest.getWhoIsGet().equals("player")) {
			if (chest.isAdd()) {
				player.setMoney(player.getMoney() + chest.getMoney());
			} else {
				player.setMoney(player.getMoney() - chest.getMoney());
			}
		} else if (chest.getWhoIsGet().equals("allPlayers")) {
			if (chest.isAdd()) {
				// for (Player players : playersInGame.playersList()) {
				for (Player players : playersInGame.playersPermanentlyList()) {
					if (!players.equals(player)) {
						players.setMoney(players.getMoney() - chest.getMoney());
						player.setMoney(player.getMoney() + chest.getMoney());
					}
				}
			} else {
				// for (Player players : playersInGame.playersList()) {
				for (Player players : playersInGame.playersPermanentlyList()) {
					if (!players.equals(player)) {
						players.setMoney(players.getMoney() + chest.getMoney());
						player.setMoney(player.getMoney() - chest.getMoney());
					}
				}
			}
		} else if (chest.getWhoIsGet().equals("coliseum")) {
			player.setHasFreeCard(true);
		}
	}

	@Override
	void effectOnPlayer(Player player) {
		System.out.println("community chest: ");
		chestActivity(player);
	}
}
