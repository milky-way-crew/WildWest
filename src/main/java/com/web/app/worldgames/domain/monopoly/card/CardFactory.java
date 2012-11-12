package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class CardFactory {
	public static Cell chooseCard(Player player) {
		Cell cell = null;
		if (StartGame.boardCities.containsKey(player.getPosition())) {
			cell = StartGame.boardCities.get(player.getPosition());
			return cell;
		} else if ((player.getPosition() == CellPositions.TAX2)
				|| (player.getPosition() == CellPositions.TAX1)) {
			cell = new TaxCard();
			return cell;
		} else if (StartGame.boardRails.containsKey(player.getPosition())) {
			cell = StartGame.boardRails.get(player.getPosition());
			return cell;
		} else if ((player.getPosition() == CellPositions.CHANCE1)
				|| (player.getPosition() == CellPositions.CHANCE2)
				|| (player.getPosition() == CellPositions.CHANCE3)
				|| (player.getPosition() == CellPositions.CHANCE4)) {
			cell = new ChanseCard();
			return cell;
		} else if (player.getPosition() == CellPositions.GO_TO_JAIL) {
			cell = new GoToJailCard();
			return cell;
		} else if ((player.getPosition() == CellPositions.COMMUNITY_CHEST1)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST2)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST3)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST4)) {
			cell = new CommunityChestCard();
			return cell;
		} else if (player.getPosition() == CellPositions.JAIL) {
			cell = new JailCard();
			return cell;
		} else if (player.getPosition() == CellPositions.START) {
			cell = new GoCard();
			return cell;
		} else if (player.getPosition() == CellPositions.FREE_STATION) {
			cell = new FreeStation();
			return cell;
		}
		return null;
	}
	
}
