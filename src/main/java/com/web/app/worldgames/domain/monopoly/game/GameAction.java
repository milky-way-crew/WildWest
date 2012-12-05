package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
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
	private static final Logger log = Logger.getLogger(GameAction.class);
	static Map<String, Object> state = new HashMap<String, Object>();
	Map<String, Object> buttons = new HashMap<String, Object>();
	String messages = null;

	// public static Map<String, Object> checkAction(Player player,
	// Map<String, Object> buttons, Map<String, Object> state,
	// String messages) {
	// // Map<String, Object> state = new HashMap<String, Object>();
	// // Map<String, Object> buttons = new HashMap<String, Object>();
	// buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
	// buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
	// buttons.put(ButtonsLabel.BUILD, player.canBuild());
	// buttons.put(ButtonsLabel.SELL, player.canSell());
	// buttons.put(ButtonsLabel.DONE, true);
	// buttons.put(ButtonsLabel.ROLL, player.doublePoints());
	// state.put("buttons", buttons);
	// state.put("messages", messages);
	// state.put("player", player.getColor());
	// state.put("player_money", player.getMoney());
	// return state;
	// }

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
						messages = "Owner " + player.getName();
					} else if (!player.equals(city.getOwner())
							&& city.getOwner() != null && city.isMortage()) {
						buttons.put(ButtonsLabel.BUY, false);
						messages = "Object is mortage";
						state.put("owner", city.getOwner().getColor());
						state.put("owner_money", city.getOwner().getMoney());
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
					rail.effectOnPlayer(player);
//					if (!player.equals(rail.getOwner())
//							&& rail.getOwner() != null && !rail.isMortage()) {
//						// if (!player.equals(rail.getOwner()) &&
//						// !rail.getOwner().equals(null)) {
//						buttons.put(ButtonsLabel.BUY, false);
//						messages = "Pay rent $"
//								+ rail.getRent(player, rail.getOwner())
//								+ " to " + rail.getOwner().getName();
//						state.put("owner", rail.getOwner().getColor());
//						state.put("owner_money", rail.getOwner().getMoney());
//					} else if (!player.equals(rail.getOwner())
//							&& rail.getOwner() != null && rail.isMortage()) {
//						buttons.put(ButtonsLabel.BUY, false);
//						messages = "Object is mortage";
//						state.put("owner", rail.getOwner().getColor());
//						state.put("owner_money", rail.getOwner().getMoney());
//					} else {
//						buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
//					}
//					if (player.equals(rail.getOwner())) {
//						messages = "Owner " + player.getName();
//					}
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
				messages = "Payed tax " + CardPrices.TAX;
			} else if (cell instanceof JailCard) {
				cell.effectOnPlayer(player);
				messages = ((JailCard) cell).getMsg();
				// buttons.put(ButtonsLabel.ROLL, true);
				if (player.isInJail()) {
					// buttons.put(ButtonsLabel.ROLL, true);
					buttons.put(ButtonsLabel.PAY,
							((JailCard) cell).canPayRansom(player));
					messages = "You may pay a ransom or roll dices";
				} else {
					// buttons.put(ButtonsLabel.ROLL, true);
					messages = "You may roll dices";
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
				player.setPosition(CellPositions.JAIL);
				if (player.getPosition() == CellPositions.JAIL) {
					JailCard jailCard = (JailCard) CardFactory.chooseCard(player);
					jailCard.effectOnPlayer(player);
					if (player.isInJail()) {
						buttons.put(ButtonsLabel.PAY,
								jailCard.canPayRansom(player));
					} else {
						buttons.put(ButtonsLabel.PAY, false);
					}
				}
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
				player.setPosition(end);
				Cell chanceCell = CardFactory.chooseCard(player);
				if (chanceCell instanceof RailCard) {
					log.info(" CARD IS RAIL");
					RailCard rail = (RailCard) chanceCell;
					try {
						rail.effectOnPlayer(player);
						if (!player.equals(rail.getOwner())
								&& rail.getOwner() != null && !rail.isMortage()) {
							buttons.put(ButtonsLabel.BUY, false);
							messages = "Pay rent $"
									+ rail.getRent(player, rail.getOwner())
									+ " to " + rail.getOwner().getName();
							state.put("owner", rail.getOwner().getColor());
							state.put("owner_money", rail.getOwner().getMoney());
						} else if (!player.equals(rail.getOwner())
								&& rail.getOwner() != null && rail.isMortage()) {
							buttons.put(ButtonsLabel.BUY, false);
							messages = "Object is mortage";
							state.put("owner", rail.getOwner().getColor());
							state.put("owner_money", rail.getOwner().getMoney());
						} else {
							buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
						}
						if (player.equals(rail.getOwner())) {
							messages = "Owner " + player.getName();
						}
						if (rail.getOwner() == null) {
							buttons.put(ButtonsLabel.AUCTION, true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					buttons.put(ButtonsLabel.PAY, false);

				} else if (chanceCell instanceof JailCard) {
					if (player.getPosition() == CellPositions.JAIL) {
						JailCard jailCard = (JailCard) CardFactory.chooseCard(player);
						jailCard.effectOnPlayer(player);
						if (player.isInJail()) {
							buttons.put(ButtonsLabel.PAY,
									jailCard.canPayRansom(player));
						} else {
							buttons.put(ButtonsLabel.PAY, false);
						}
					}
				} else {
					chanceCell.effectOnPlayer(player);
				}
			} else if (cell instanceof CommunityChestCard) {
				cell.effectOnPlayer(player);
				messages = ((CommunityChestCard) cell).getMsg();
			}
		}
		buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
		buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
		buttons.put(ButtonsLabel.BUILD, player.canBuild());
		buttons.put(ButtonsLabel.SELL, player.canSell());
		buttons.put(ButtonsLabel.DONE, true);
		if (player.isInJail()) {
			buttons.put(ButtonsLabel.ROLL, true);
		} else if (!player.isInJail()
				&& player.getPosition() == CellPositions.JAIL) {
			buttons.put(ButtonsLabel.ROLL, true);
		} else {
			buttons.put(ButtonsLabel.ROLL, player.doublePoints());
		}
		state.put("buttons", buttons);
		state.put("messages", messages);
		state.put("player", player.getColor());
		state.put("player_money", player.getMoney());
		return state;
	}
	// private static Map<String, Object> onChanceAction(Cell cell, Player
	// player){
	// Map<String, Object> buttons = new HashMap<String, Object>();
	// Map<String, Object> state = new HashMap<String, Object>();
	// String messages = null;
	// cell.effectOnPlayer(player);
	// messages = ((ChanceCard) cell).getInformation();
	// //move = true;
	// int start = player.getPosition();
	// int end = ((ChanceCard) cell).getMovePosition();
	// int dice1 = 0;
	// if (end > start) {
	// dice1 = end - start;
	// } else if (start > end) {
	// dice1 = (CELL_NUMBER - start) + end;
	// }
	// //state.put("move", move);
	// state.put("was", start);
	// state.put("dice1", dice1);
	// state.put("dice2", 0);
	// player.setPosition(end);
	// Cell chanceCell = CardFactory.chooseCard(player);
	// if (chanceCell instanceof RailCard) {
	// state = onRailAction(chanceCell, player);
	// } else if (chanceCell instanceof JailCard) {
	// state = onJailAction(cell, player);
	// } else {
	// chanceCell.effectOnPlayer(player);
	// }
	// return checkAction(player, buttons, state, messages);
	// }
	// private static Map<String, Object> onRailAction(Cell cell, Player player)
	// {
	// Map<String, Object> buttons = new HashMap<String, Object>();
	// Map<String, Object> state = new HashMap<String, Object>();
	// String messages = null;
	// RailCard rail = (RailCard) cell;
	// rail.effectOnPlayer(player);
	// if (!player.equals(rail.getOwner()) && rail.getOwner() != null
	// && !rail.isMortage()) {
	// // if (!player.equals(rail.getOwner()) &&
	// // !rail.getOwner().equals(null)) {
	// buttons.put(ButtonsLabel.BUY, false);
	// messages = "Pay rent $" + rail.getRent(player, rail.getOwner())
	// + " to " + rail.getOwner().getName();
	// state.put("owner", rail.getOwner().getColor());
	// state.put("owner_money", rail.getOwner().getMoney());
	// } else {
	// buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
	// }
	// if (player.equals(rail.getOwner())) {
	// messages = "You are owner!";
	// }
	// if (rail.getOwner() == null) {
	// buttons.put(ButtonsLabel.AUCTION, true);
	// }
	// buttons.put(ButtonsLabel.PAY, false);
	// return checkAction(player, buttons, state, messages);
	// }
	//
	// private static Map<String, Object> onCityAction(Cell cell, Player player)
	// {
	// log.info("ON CITY ACTION");
	// Map<String, Object> buttons = new HashMap<String, Object>();
	// Map<String, Object> state = new HashMap<String, Object>();
	// String messages = null;
	// try {
	// CityCard city = (CityCard) cell;
	// city.effectOnPlayer(player);
	// if (!player.equals(city.getOwner()) && city.getOwner() != null
	// && !city.isMortage()) {
	// buttons.put(ButtonsLabel.BUY, false);
	// messages = "Pay rent $" + city.getRent(player, city.getOwner())
	// + " to " + city.getOwner().getName();
	// state.put("owner", city.getOwner().getColor());
	// state.put("owner_money", city.getOwner().getMoney());
	// } else if (player.equals(city.getOwner())) {
	// messages = "Owner " + player.getName();
	// } else {
	// buttons.put(ButtonsLabel.BUY, city.canBuy(player));
	// }
	// if (city.getOwner() == null) {
	// buttons.put(ButtonsLabel.AUCTION, true);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// buttons.put(ButtonsLabel.PAY, false);
	// log.info("state in city: "+checkAction(player, buttons, state,
	// messages));
	// return checkAction(player, buttons, state, messages);
	// }
	//
	// private static Map<String, Object> onJailAction(Cell cell, Player player)
	// {
	// Map<String, Object> buttons = new HashMap<String, Object>();
	// Map<String, Object> state = new HashMap<String, Object>();
	// String messages = null;
	// cell.effectOnPlayer(player);
	// messages = ((JailCard) cell).getMsg();
	// buttons.put(ButtonsLabel.ROLL, true);
	// if (player.isInJail()) {
	// // buttons.put(ButtonsLabel.ROLL, true);
	// buttons.put(ButtonsLabel.PAY,
	// ((JailCard) cell).canPayRansom(player));
	// messages = "You may pay a ransom or roll dices";
	// } else {
	// // buttons.put(ButtonsLabel.ROLL, true);
	// buttons.put(ButtonsLabel.PAY, false);
	// }
	// return checkAction(player, buttons, state, messages);
	// }
}
