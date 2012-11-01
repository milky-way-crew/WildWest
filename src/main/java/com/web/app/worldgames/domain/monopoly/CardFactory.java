package com.web.app.worldgames.domain.monopoly;

public class CardFactory {
	public Cell createCard(int position) {
		if (position == CellPositions.CHANCE1
				|| position == CellPositions.CHANCE2
				|| position == CellPositions.CHANCE3
				|| position == CellPositions.CHANCE4) {
			return new ChanseCard();
		} else if (type.equals("PortCard")) {
			return new PortCard();
		} else if (type.equals("RouletterCard")) {
			return new RouletteCard();
		} else if (type.equals("StartCard")) {
			return new StartCard();
		} else if (type.equals("TaxCard")) {
			return new TaxCard(CardPrices.TAX);
		}
		return null;
	}

}
