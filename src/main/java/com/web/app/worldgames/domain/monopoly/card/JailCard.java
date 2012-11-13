package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.CardPrices;
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
		player.rollDicesAndMove();
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
		return (player.checkMoney( CardPrices.RANSOM_FROM_JAIL)) ? true
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


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
