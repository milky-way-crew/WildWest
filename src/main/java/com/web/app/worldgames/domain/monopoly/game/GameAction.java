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
import com.web.app.worldgames.domain.monopoly.card.GoToJailCard;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.domain.monopoly.card.StartCard;
import com.web.app.worldgames.domain.monopoly.card.TaxCard;

/**
 * 
 * @author Inna
 * 
 */
public class GameAction {
	private static final int CELL_NUMBER = 40;

	/**
	 * Method check players position and define active buttons for him
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
		boolean move = false;
		if (cell instanceof SellableCard) {
			if (cell instanceof CityCard) {
				try {
					CityCard city = (CityCard) cell;
					city.effectOnPlayer(player);
					if (!player.equals(city.getOwner())
							&& city.getOwner() != null && !city.isMortage()) {
						// if (!player.equals(city.getOwner()) &&
						// !city.getOwner().equals(null)) {
						buttons.put(ButtonsLabel.BUY, false);
						messages = "Pay rent $"
								+ city.getRent(player, city.getOwner())
								+ " to " + city.getOwner().getName();
						state.put("owner", city.getOwner().getColor());
						state.put("owner_money", city.getOwner().getMoney());
					} else if (player.equals(city.getOwner())) {
						messages = "You are owner!";
					} else {
						buttons.put(ButtonsLabel.BUY, city.canBuy(player));
					}
					if (city.getOwner() == null) {
						buttons.put(ButtonsLabel.AUCTION, true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				buttons.put(ButtonsLabel.PAY, false);
			} else if (cell instanceof RailCard) {
				RailCard rail = (RailCard) cell;
				try {

					if (!player.equals(rail.getOwner())
							&& rail.getOwner() != null && !rail.isMortage()) {
						// if (!player.equals(rail.getOwner()) &&
						// !rail.getOwner().equals(null)) {
						buttons.put(ButtonsLabel.BUY, false);
						messages = "Pay rent $"
								+ rail.getRent(player, rail.getOwner())
								+ " to " + rail.getOwner().getName();
						state.put("owner", rail.getOwner().getColor());
						state.put("owner_money", rail.getOwner().getMoney());
					} else {
						buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
					}
					if (player.equals(rail.getOwner())) {
						messages = "You are owner!";
					}
					if (rail.getOwner() == null) {
						buttons.put(ButtonsLabel.AUCTION, true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				buttons.put(ButtonsLabel.PAY, false);
			}
		} else {
			if (cell instanceof StartCard) {
				cell.effectOnPlayer(player);
			} else if (cell instanceof TaxCard) {
				cell.effectOnPlayer(player);
				messages = "You payed tax " + CardPrices.TAX;
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				messages = ((JailCard) cell).getMsg();
				buttons.put(ButtonsLabel.ROLL, true);
				if (player.isInJail()) {
					// buttons.put(ButtonsLabel.ROLL, true);
					buttons.put(ButtonsLabel.PAY,
							((JailCard) cell).canPayRansom(player));
					messages = "You may pay a ransom or roll dices";
				} else {
					// buttons.put(ButtonsLabel.ROLL, true);
					buttons.put(ButtonsLabel.PAY, false);
				}
			} else if (cell instanceof FreeStation) {
				cell.effectOnPlayer(player);
				messages = "You stay at Free Station";
			} else if (cell instanceof GoToJailCard) {
				cell.effectOnPlayer(player);
				messages = "You are going to jail";
				move = true;
				state.put("move", move);
				state.put("was", CellPositions.GO_TO_JAIL);
				state.put("dice1", (CELL_NUMBER - CellPositions.GO_TO_JAIL)
						+ CellPositions.JAIL);
				state.put("dice2", 0);
			} else if (cell instanceof ChanceCard) {
				cell.effectOnPlayer(player);
				messages = ((ChanceCard) cell).getInformation();
				move = true;
				int start = player.getPosition();
				int end = ((ChanceCard) cell).getMovePosition();
				int dice1 = 0;
				if (end > start) {
					dice1 = end - start;
				} else if (start > end) {
					dice1 = (CELL_NUMBER - start) + end;
				}
				state.put("move", move);
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
		buttons.put(ButtonsLabel.ROLL, player.doublePoints());
		state.put("buttons", buttons);
		state.put("messages", messages);
		state.put("player", player.getColor());
		state.put("player_money", player.getMoney());
		return state;
	}
}
