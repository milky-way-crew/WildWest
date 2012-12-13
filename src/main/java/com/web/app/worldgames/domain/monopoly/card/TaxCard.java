package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;
/**
 * 
 * @author Inna
 *
 */
public class TaxCard extends Cell {
	
	private final static Logger log = Logger.getLogger(TaxCard.class);
	
	@Override
	public void effectOnPlayer(Player player) {
		log.info("[PLAYER]: " +player.getColor()+" money before tax: " +player.getMoney());
		player.setMoney(player.getMoney() - CardPrices.TAX);
		log.info("[PLAYER]: " +player.getColor()+" money after tax " +player.getMoney());
	}

	@Override
	public String info() {
		return "You must pay a tax: " + CardPrices.TAX;
	}

}
