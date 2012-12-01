package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;
/**
 * 
 * @author Inna
 *
 */
public class CardFactory {
	private static final Logger log=Logger.getLogger(CardFactory.class);
	public static Cell chooseCard(Player player) {
		Cell cell = null;
		if (StartGame.boardCities.containsKey(player.getPosition())) {
			cell = StartGame.boardCities.get(player.getPosition());
			log.info("In card factory+++++++++++++++++++   "+ cell.info());
			return cell;
		} else if ((player.getPosition() == CellPositions.TAX2)
				|| (player.getPosition() == CellPositions.TAX1)) {
			cell = new TaxCard();
			cell.setName("Tax Card");
			return cell;
		} else if (StartGame.boardRails.containsKey(player.getPosition())) {
			cell = StartGame.boardRails.get(player.getPosition());
			log.info("In card factory+++++++++++++++++++   "+ cell.info());
			cell.setName("Rail Card");
			return cell;
		} else if ((player.getPosition() == CellPositions.CHANCE1)
				|| (player.getPosition() == CellPositions.CHANCE2)
				|| (player.getPosition() == CellPositions.CHANCE3)) {
			cell = new ChanceCard();
			cell.setName("Chance Card");
			return cell;
		} else if (player.getPosition() == CellPositions.GO_TO_JAIL) {
			cell = new GoToJailCard();
			cell.setName("Jo To Jail Card");
			return cell;
		} else if ((player.getPosition() == CellPositions.COMMUNITY_CHEST1)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST2)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST3)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST4)
				|| (player.getPosition() == CellPositions.COMMUNITY_CHEST5)) {
			cell = new CommunityChestCard();
			cell.setName("Community Chest");
			return cell;
		} else if (player.getPosition() == CellPositions.JAIL) {
			cell = new JailCard();
			cell.setName("Jail Card");
			return cell;
		} else if (player.getPosition() == CellPositions.START) {
			cell = new StartCard();
			cell.setName("Start");
			return cell;
		} else if (player.getPosition() == CellPositions.FREE_STATION) {
			cell = new FreeStation();
			cell.setName("Free Station");
			return cell;
		}
		return null;
	}
}
