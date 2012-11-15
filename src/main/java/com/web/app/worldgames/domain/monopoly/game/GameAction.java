package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
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
				result.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null) {
					result.put(
							ButtonsLabel.PAY,
							card.canPayRent(player,
									card.getRent(player, card.getOwner())));
				} else {
					result.put(ButtonsLabel.PAY, false);
				}
				result.put(ButtonsLabel.BUILD, card.canBuildHouse(player)
						|| card.canBuildHotel(player));
				result.put(ButtonsLabel.REFUSE, card.canRefuse(player));
				result.put(ButtonsLabel.MORTAGE, card.canMortage(player));
				result.put(ButtonsLabel.UNMORTAGE, card.canUnMortage(player));
			} else if (cell instanceof RailCard) {
				RailCard card = (RailCard) cell;
				result.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null) {
					result.put(
							ButtonsLabel.PAY,
							card.canPayRent(player,
									card.getRent(player, card.getOwner())));
				} else {
					result.put(ButtonsLabel.PAY, false);
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
					result.put(ButtonsLabel.PAY,
							((TaxCard) cell).canPayTax(player));
				}
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				result.put(ButtonsLabel.PAY,
						((JailCard) cell).canPayRansom(player));
				result.put(ButtonsLabel.ROLL, true);
			} else if (cell instanceof FreeStation) {
				cell.effectOnPlayer(player);
				result.put("player", player.getColor());
				// result.put(ButtonsLabel.OK, true);
			} else if (cell instanceof GoToJailCard) {
				cell.effectOnPlayer(player);
				result.put("player", player.isInJail());
				// result.put(ButtonsLabel.OK, true);
			} else if (cell instanceof ChanseCard) {
				cell.effectOnPlayer(player);
				((ChanseCard) cell).getDirectCard(player);
				result.put(ButtonsLabel.OK, true);
				((ChanseCard) cell).clearChanceMap();
			} else if (cell instanceof CommunityChestCard) {
				// cell.effectOnPlayer(player);
				// result.put(ButtonsLabel.OK, true);
			}
		}
		return result;
	}
}
