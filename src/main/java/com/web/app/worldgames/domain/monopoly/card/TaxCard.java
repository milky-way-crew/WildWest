package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;

public class TaxCard extends Cell {
	
	private final static Logger log = Logger.getLogger(TaxCard.class);
	
	@Override
	public void effectOnPlayer(Player player) {
		log.info("[PLAYER]: " +player.getColor()+" money before tax: " +player.getMoney());
		player.setMoney(player.getMoney() - CardPrices.TAX);
		log.info("[PLAYER]: " +player.getColor()+" money after tax " +player.getMoney());
	}

//	public boolean canPayTax(Player player) {
//		return (player.checkMoney(CardPrices.TAX)) ? true : false;
//	}

	@Override
	public String info() {
		return "You must pay a tax: " + CardPrices.TAX;
	}

}
