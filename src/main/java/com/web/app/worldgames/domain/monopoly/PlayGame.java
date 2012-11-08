package com.web.app.worldgames.domain.monopoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayGame {
	public void chooseCell(Player player) {
		if (StartGame.boardCities.containsKey(player.getPosition())) {
			SellableCard cell = StartGame.boardCities.get(player.getPosition());
			cell.effectOnPlayer(player);
		} else if ((player.getPosition() == CellPositions.TAX2)
				|| (player.getPosition() == CellPositions.TAX1)) {
			TaxCard tax = new TaxCard(CardPrices.TAX);
			tax.effectOnPlayer(player);
		} else if (StartGame.boardRails.containsKey(player.getPosition())) {
			SellableCard cell = StartGame.boardRails.get(player.getPosition());
			cell.effectOnPlayer(player);
		} else if ((player.getPosition() == CellPositions.CHANCE1)
				|| (player.getPosition() == CellPositions.CHANCE2)
				|| (player.getPosition() == CellPositions.CHANCE3)
				|| (player.getPosition() == CellPositions.CHANCE4)) {
			ChanseCard chance = new ChanseCard();
			chance.effectOnPlayer(player);
		} else if (player.getPosition() == CellPositions.GO_TO_JAIL) {
			GoToJailCard jail = new GoToJailCard();
			jail.effectOnPlayer(player);
		} else if ((player.getPosition() == CellPositions.COMMUNITY_CHEST1)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST2)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST3)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST4)) {
			CommunityChestCard rouletteCard = new CommunityChestCard();
			rouletteCard.effectOnPlayer(player);
		} else if (player.getPosition() == CellPositions.JAIL) {
			JailCard jailCard = new JailCard();
			jailCard.effectOnPlayer(player);
		} else if (player.getPosition() == CellPositions.START) {
			GoCard goCard = new GoCard();
			goCard.effectOnPlayer(player);
		} else if (player.getPosition() == CellPositions.FREE_STATION) {
			FreeStation freeStation = new FreeStation();
			freeStation.effectOnPlayer(player);
		}
	}

	public void start() throws IOException {
		StartGame start = new StartGame();
		PlayersActivity activity = new PlayersActivity();
		// Room room = new Room();
		StartGame.initCities();
		StartGame.initRails();
		// StartGame.start(room);
		StartGame.startTemp();
		System.out.println("Players in the game: "
				+ start.playersPermanentlyList());
		String key = null;
		for (;;) {
			for (Player player : start.playersPermanentlyList()) {
				int doublePoints=0;
				System.out.println();
				System.out.println("---------------------------"
						+ player.getName() + " position: "
						+ player.getPosition() + "---money: "
						+ player.getMoney() + "-----------");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.println("\t 1 - Exit\t 2 - roll\t 3 - sale \t4 - mortage\t5 - unMortage \t6 - leave game");
				int position = 1;
				key = in.readLine();
				if (key.equals("1")) {
					StartGame.exit();
				} else if (key.equals("2")) {
					if(activity.isWinner(start.playersPermanentlyList())){
//						activity.winner(start.playersPermanentlyList());
						System.out.println(activity.winner(start.playersPermanentlyList()));
						StartGame.exit();
					}
					if(player.isInJail()){
						chooseCell(player);
					}
					if(player.isLoss()){
						System.out.println("You loss game");
						player.exitPlayer(player);
					}else{
						position = player.rollDicesAndMove();
						System.out.println("roll dices: " + position);
						//player.setPosition(position);
						chooseCell(player);
						while (Player.doublePoints()) {
							doublePoints++;
							player.rollDicesAndMove();
							chooseCell(player);
							if(doublePoints==3){
								player.setPosition(CellPositions.JAIL);
								chooseCell(player);
							}
						}
					}
				} else if (key.equals("3")) {
					PlayersActivity.saleAction(player);
				} else if(key.equals("4")){
					player.mortageAction(player);
				}else if(key.equals("5")){
					player.unMortageAction(player);
				}else if(key.equals("6")){
					player.exitPlayer(player);
				}else {
					player.setPosition(player.getPosition());
				}
			}

		}
	}
}
