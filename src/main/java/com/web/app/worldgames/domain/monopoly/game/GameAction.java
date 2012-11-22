package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.ChanceCard;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.CommunityChestCard;
import com.web.app.worldgames.domain.monopoly.card.FreeStation;
import com.web.app.worldgames.domain.monopoly.card.GoCard;
import com.web.app.worldgames.domain.monopoly.card.GoToJailCard;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.domain.monopoly.card.TaxCard;

public class GameAction {
	private static final int CELL_NUMBER = 40;

	/**
	 * 
	 * @param cell
	 * @param player
	 * @return map of active buttons depending on the position and players
	 *         possibility
	 */

	public static Map<String, Object> action(Cell cell, Player player) {
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		String messages = null;
		if (cell instanceof SellableCard) {
			if (cell instanceof CityCard) {
				CityCard card = (CityCard) cell;
				card.effectOnPlayer(player);
				if (player != card.getOwner() && card.getOwner() != null) {
					buttons.put(ButtonsLabel.BUY, false);
					messages = "Pay rent $"
							+ card.getRent(player, card.getOwner()) + " to "
							+ card.getOwner().getName();
				} 
//				else if (card.getOwner() != null
//						&& card.getOwner() != player
//						&& !card.canPayRent(player,
//								card.getRent(player, card.getOwner()))) {
//					messages = "You haven't money. You may mortage or sell your property.";
//				} 
				else if (card.getOwner() == player) {
					messages = "You are owner!";
				} else {
					buttons.put(ButtonsLabel.BUY, card.canBuy(player));
				}

				buttons.put(ButtonsLabel.PAY, false);
				buttons.put(ButtonsLabel.DONE, true);
			} else if (cell instanceof RailCard) {
				RailCard card = (RailCard) cell;
				if (player != card.getOwner() && card.getOwner() != null) {
					buttons.put(ButtonsLabel.BUY, false);
					messages = "Pay rent $"
							+ card.getRent(player, card.getOwner()) + " to "
							+ card.getOwner().getName();
				} else {
					buttons.put(ButtonsLabel.BUY, card.canBuy(player));
				}
//				if (card.getOwner() != null
//						&& card.getOwner() != player
//						&& !card.canPayRent(player,
//								card.getRent(player, card.getOwner()))) {
//					messages = "You haven't money. You may mortage or sell your property.";
//				}
				if (card.getOwner() == player) {
					messages = "You are owner!";
				}
				buttons.put(ButtonsLabel.PAY, false);
				buttons.put(ButtonsLabel.DONE, true);
			}
		} else {
			if (cell instanceof GoCard) {
				cell.effectOnPlayer(player);
				buttons.put(ButtonsLabel.DONE, true);
			} else if (cell instanceof TaxCard) {
//				if (((TaxCard) cell).canPayTax(player)) {
//					cell.effectOnPlayer(player);
//					messages = "You payed tax " + CardPrices.TAX;
//					buttons.put(ButtonsLabel.DONE, true);
//				} else {
//					messages = "You haven't money. You may mortage or sell your property.";
//					buttons.put(ButtonsLabel.DONE, false);
//				}
				cell.effectOnPlayer(player);
				messages = "You payed tax " + CardPrices.TAX;
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				messages = ((JailCard) cell).getMsg();
				//buttons.put(ButtonsLabel.ROLL, true);
				if (player.isInJail()) {
					//buttons.put(ButtonsLabel.ROLL, true);
					buttons.put(ButtonsLabel.PAY,
							((JailCard) cell).canPayRansom(player));
					messages = "You may pay a ransom or roll dices";
				} else {
					//buttons.put(ButtonsLabel.ROLL, true);
					buttons.put(ButtonsLabel.PAY, false);
				}
				buttons.put(ButtonsLabel.DONE, true);
			} else if (cell instanceof FreeStation) {
				cell.effectOnPlayer(player);
				messages = "You stay at Free Station";
				buttons.put(ButtonsLabel.DONE, true);
			} else if (cell instanceof GoToJailCard) {
				cell.effectOnPlayer(player);
				messages = "You are going to jail";
				state.put("was", CellPositions.GO_TO_JAIL);
				state.put("dice1", (CELL_NUMBER - CellPositions.GO_TO_JAIL)
						+ CellPositions.JAIL);
				state.put("dice2", 0);
				buttons.put(ButtonsLabel.DONE, true);
			} else if (cell instanceof ChanceCard) {
				cell.effectOnPlayer(player);
				messages = ((ChanceCard) cell).getInformation();
				int start = player.getPosition();
				int end = ((ChanceCard) cell).getMovePosition();
				int dice1 = 0;
				if (end > start) {
					dice1 = end - start;
				} else if (start > end) {
					dice1 = (CELL_NUMBER - start) + end;
				}
				state.put("was", start);
				state.put("dice1", dice1);
				state.put("dice2", 0);
			} else if (cell instanceof CommunityChestCard) {
				cell.effectOnPlayer(player);
				messages = "You have a community chest: "
						+ ((CommunityChestCard) cell).getMsg();
			}

		}
		buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
		buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
		buttons.put(ButtonsLabel.BUILD, player.canBuild());
		buttons.put(ButtonsLabel.SELL, player.canSell());
		buttons.put(ButtonsLabel.DONE, true);
//		if(player.isInJail()){
//			buttons.put(ButtonsLabel.ROLL, true);
//		}else{
			buttons.put(ButtonsLabel.ROLL, player.doublePoints());
//		}
		state.put("buttons", buttons);
		state.put("messages", messages);
		state.put("player", player.getColor());
		state.put("player_money", player.getMoney());
		return state;
	}
}
