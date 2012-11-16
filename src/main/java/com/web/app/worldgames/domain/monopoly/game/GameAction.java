package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.ChanseCard;
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
	/**
	 * 
	 * @param cell
	 * @param player
	 * @return map of active buttons depending on the position and players
	 *         possibility
	 */
	public static Map<String, Object> action(Cell cell, Player player) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (cell instanceof SellableCard) {
			if (cell instanceof CityCard) {
				CityCard card = (CityCard) cell;
				card.effectOnPlayer(player);
				result.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null
						&& !card.canPayRent(player,
								card.getRent(player, card.getOwner()))) {
					result.put("Problem", "No money");
				}
				result.put(ButtonsLabel.BUILD, card.canBuildHouse(player)
						|| card.canBuildHotel(player));
				result.put(ButtonsLabel.REFUSE, card.canRefuse(player));
				result.put(ButtonsLabel.MORTAGE, card.canMortage(player));
				result.put(ButtonsLabel.UNMORTAGE, card.canUnMortage(player));
			} else if (cell instanceof RailCard) {
				RailCard card = (RailCard) cell;
				result.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null
						&& !card.canPayRent(player,
								card.getRent(player, card.getOwner()))) {
					result.put("Problem", "No money");
				}
				result.put(ButtonsLabel.REFUSE, card.canRefuse(player));
				result.put(ButtonsLabel.MORTAGE, card.canMortage(player));
				result.put(ButtonsLabel.UNMORTAGE, card.canUnMortage(player));

			}
		} else {
			if (cell instanceof GoCard) {
				cell.effectOnPlayer(player);
				// result.put(ButtonsLabel.OK, true);
				result.put("player", player.getColor());
				result.put("player_money", player.getMoney());
			} else if (cell instanceof TaxCard) {
				if (((TaxCard) cell).canPayTax(player)) {
					cell.effectOnPlayer(player);
					result.put("player", player.getColor());
					result.put("player_money", player.getMoney());
				} else {
					result.put("Problem", "No money");
				}
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				result.put(ButtonsLabel.PAY,
						((JailCard) cell).canPayRansom(player));
				result.put(ButtonsLabel.ROLL, true);
			} else if (cell instanceof FreeStation) {
				cell.effectOnPlayer(player);
				result.put("player", player.getColor());
			} else if (cell instanceof GoToJailCard) {
				cell.effectOnPlayer(player);
				result.put("player", player.isInJail());
				result.put("Move to:", CellPositions.JAIL);
			} else if (cell instanceof ChanseCard) {
				cell.effectOnPlayer(player);
				result.put("Move to:", ((ChanseCard) cell).getMovePosition());
			} else if (cell instanceof CommunityChestCard) {
			}
		}
		return result;
	}
}
