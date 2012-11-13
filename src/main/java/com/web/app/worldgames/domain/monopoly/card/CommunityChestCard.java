package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;
import java.util.Random;

import com.web.app.worldgames.domain.monopoly.CommunityChest;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class CommunityChestCard extends Cell {

	public void chestActivity(Player player) {
		boolean check = true;
		CommunityChest chest = getRandomChestCard();
		int price = chest.getMoney();
		if (!chest.isAdd()) {
			if (player.checkMoney(price)) {
				service(player, chest);
			} else {
				while (check) {
					player.listPropertyForMortage(player);
					if (player.canMortage()) {

						player.mortageAction(player);
						if (player.checkMoney( price)) {
							service(player, chest);
							check = false;
						} else {
							check = true;
						}
					} else {
						System.out.println("you haven't object");
						player.setLoss(true);
						System.out.println("loss: " + player.isLoss());
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
	public void effectOnPlayer(Player player) {
		System.out.println("community chest: ");
		chestActivity(player);
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
