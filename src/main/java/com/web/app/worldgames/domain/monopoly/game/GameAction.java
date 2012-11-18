package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Cities;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.Rails;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.ChanñeCard;
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
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		String messages = null;
		Integer go = 0 ;
		if (cell instanceof SellableCard) {
			if (cell instanceof CityCard) {
				CityCard card = (CityCard) cell;
				card.effectOnPlayer(player);
				buttons.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null
						&& !card.canPayRent(player,
								card.getRent(player, card.getOwner()))) {
					messages="You haven't money. You may mortage or sell your property.";
				}
				buttons.put(ButtonsLabel.BUILD, card.canBuildHouse(player)
						|| card.canBuildHotel(player));
				//buttons.put(ButtonsLabel.MORTAGE, card.canMortage(player));
				//buttons.put(ButtonsLabel.UNMORTAGE, card.canUnMortage(player));
				//buttons.put(ButtonsLabel.SELL, card.canSell(player));
//				buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
//				buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
//				buttons.put(ButtonsLabel.SELL, player.canSell());
				buttons.put(ButtonsLabel.PAY, false);
			} else if (cell instanceof RailCard) {
				RailCard card = (RailCard) cell;
				buttons.put(ButtonsLabel.BUY, card.canBuy(player));
				if (card.getOwner() != null
						&& !card.canPayRent(player,
								card.getRent(player, card.getOwner()))) {
					messages="You haven't money. You may mortage or sell your property.";
				}
				//buttons.put(ButtonsLabel.MORTAGE, card.canMortage(player));
				//buttons.put(ButtonsLabel.UNMORTAGE, card.canUnMortage(player));
			//buttons.put(ButtonsLabel.SELL, card.canSell(player));
				buttons.put(ButtonsLabel.PAY, false);
			}
		} else {
			if (cell instanceof GoCard) {
				cell.effectOnPlayer(player);
				//buttons.put(ButtonsLabel.DONE, true);
				messages="You got $200";
			} else if (cell instanceof TaxCard) {
				if (((TaxCard) cell).canPayTax(player)) {
					cell.effectOnPlayer(player);
					//buttons.put(ButtonsLabel.DONE, true);
					messages="You payed tax";
				} else {
					//buttons.put(ButtonsLabel.DONE, true);
					messages="You haven't money. You may mortage or sell your property.";
				}
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				buttons.put(ButtonsLabel.PAY,
						((JailCard) cell).canPayRansom(player));
				buttons.put(ButtonsLabel.ROLL, true);
				messages="You may pay a ransom or roll dices";
			} else if (cell instanceof FreeStation) {
				cell.effectOnPlayer(player);
				messages="You stay at Free Station";
			} else if (cell instanceof GoToJailCard) {
				cell.effectOnPlayer(player);
				messages="You are going to jail";
				go=CellPositions.JAIL;
			} else if (cell instanceof ChanñeCard) {
				cell.effectOnPlayer(player);
				messages="You have a chance";
				go=((ChanñeCard) cell).getMovePosition();
			} else if (cell instanceof CommunityChestCard) {
				cell.effectOnPlayer(player);
				messages="You have a community chest";
			}
			
		}
		buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
		buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
		buttons.put(ButtonsLabel.SELL, player.canSell());
		buttons.put(ButtonsLabel.DONE, true);
		buttons.put(ButtonsLabel.ROLL, player.doublePoints());
		state.put("buttons", buttons);
		state.put("go", go);
		state.put("messages", messages);
		state.put("player", player.getColor());
		state.put("player_money", player.getMoney());
		return state;
	}
//	 public static void main(String[] args) {
//	 Cell cell = new ChanseCard();
//	 Cell cell1 = new GoCard();
//	 Cell cell3 = new RailCard(Rails.RAIL1);
//	 Player p1 = new Player("sdf", 23, 1200, PlayerColors.PLAYER1);
//	 //cell.setOwner(p1);
//	 Player p = new Player("sdf", 23, 0, PlayerColors.PLAYER1);
//	 //cell.effectOnPlayer(p);
//	 System.out.println("chanse: "+GameAction.action(cell, p));
//	 System.out.println("Go: "+GameAction.action(cell1, p));
//	 System.out.println("Rail: "+GameAction.action(cell3, p));
//	 }
}
