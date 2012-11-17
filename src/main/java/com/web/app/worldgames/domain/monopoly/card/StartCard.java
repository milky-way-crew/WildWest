package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;

public class StartCard extends Cell {

	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(CardPrices.START_MONEY);
		player.setPosition(CellPositions.START);
		// player.setHasFreeCard(false);
	}

	@Override
	public String info() {
		return "You can start: ";
	}
}
