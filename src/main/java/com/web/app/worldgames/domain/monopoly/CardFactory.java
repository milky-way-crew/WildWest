package com.web.app.worldgames.domain.monopoly;

public class CardFactory {
	public Cell createCard(String type) {
		if (type.equals("ChanseCard")) {
			return new ChanseCard();
		} else if (type.equals("PortCard")) {
			return new PortCard();
		} else if (type.equals("RouletterCard")) {
			return new RouletteCard();
		} else if (type.equals("StartCard")) {
			return new StartCard();
		} else if (type.equals("TaxCard")) {
			return new TaxCard();
		}
		return null;
	}
	
	
}
