package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;

public class TaxCard extends Cell {
	@Override
	public void effectOnPlayer(Player player) {
//		boolean check = true;
//		if (player.checkMoney(player, CardPrices.TAX)) {
//			player.setMoney(player.getMoney() - CardPrices.TAX);
//			System.out.println("Now you have: " + player.getMoney());
//		} else {
//			while (check) {
//				player.listPropertyForMortage(player);
//				if (player.canMortage()) {
//					player.mortageAction(player);
//					if (player.checkMoney(player, CardPrices.TAX)) {
//						player.setMoney(player.getMoney() - CardPrices.TAX);
//						check = false;
//					} else {
//						check = true;
//					}
//				} else {
//					System.out.println("you haven't object");
//					player.setLoss(true);
//					System.out.println("loss: " + player.isLoss());
//					check = false;
//				}
//			}
//		}
		
		//if(canPayTax(player)){
			player.setMoney(player.getMoney() - CardPrices.TAX);
	//	}
		
	}

	public boolean canPayTax(Player player) {
		return (player.checkMoney( CardPrices.TAX)) ? true : false;
	}

	@Override
	public String info() {
		return "You must pay a tax: " + CardPrices.TAX;
	}

}
