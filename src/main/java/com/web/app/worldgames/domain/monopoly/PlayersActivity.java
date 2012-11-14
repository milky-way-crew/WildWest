package com.web.app.worldgames.domain.monopoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class PlayersActivity {
	

	public Player winner(List<Player> players) {
		System.out.println(players.get(0).getName() + " is winner. ");
		return players.get(0);
	}

	public boolean isWinner(List<Player> players) {
		if (players.size() == 1) {
			return true;
		} else
			return false;
	}

	public static void cityOrRailSale(Player seller, Player buyer,
			SellableCard card) {
		if (card.isMortage()) {
			System.out.println("unMortage this city: ");
		} else {
			seller.setMoney(seller.getMoney() + card.getPrice());
			buyer.setMoney(buyer.getMoney() - card.getPrice());
			card.setOwner(buyer);
			if (card instanceof CityCard) {
				seller.listRegions(seller).remove(
						seller.getRegion(card.getPosition()));
				buyer.addRegionsSellActivity(buyer, (CityCard) card);
			} else if (card instanceof RailCard) {
				seller.subNumberOfRAils();
				buyer.addNumberOfRails();
			}
			buyer.addSelledProperty(card);
			seller.deleteProperty(seller, card);
			System.out.println("You sale city " + card.getName() + " to "
					+ buyer.getName());
		}

	}

	public static void saleAction(Player player) {
		StartGame game = new StartGame();
		int i = 0;
		int pl=0;
		System.out.println("You want to sale city or rail: Choose: ");
		for(SellableCard c:player.playerProperty()){
			System.out.println(i+": "+c.getName());
			i++;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int index = Integer.valueOf(key);
		SellableCard cityToSale = player.playerProperty().get(index);
		System.out.println("choose player: ");
		for (Player p : game.playersPermanentlyList()) {
			if(!p.equals(player)){
				System.out.println(pl + ": " + p.getName());
				pl++;
			}
		}
		try {
			key = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		index = Integer.valueOf(key);
		Player buyer = game.playersPermanentlyList().get(index);
		cityOrRailSale(player, buyer, cityToSale);
	}
	public void playerAction(Cell cell, Player player){
		if(cell instanceof SellableCard){
			
		}else{
			cell.effectOnPlayer(player);
		}
	}
}
