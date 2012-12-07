package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;
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

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

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
			try {
				cell.effectOnPlayer(player);
				buttons.put(ButtonsLabel.BUY,
						player.canBuy((SellableCard) cell));
				messages = player.messages((SellableCard) cell);
				if (((SellableCard) cell).getOwner() != null) {
					state.put("owner", ((SellableCard) cell).getOwner()
							.getColor());
					state.put("owner_money", ((SellableCard) cell).getOwner()
							.getMoney());
				}
				if (((SellableCard) cell).getOwner() == null) {
					buttons.put(ButtonsLabel.AUCTION, true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// if (cell instanceof CityCard) {
			// try {
			// CityCard city = (CityCard) cell;
			// city.effectOnPlayer(player);
			// if (!player.equals(city.getOwner())
			// && city.getOwner() != null && !city.isMortage()) {
			// // if (!player.equals(city.getOwner()) &&
			// // !city.getOwner().equals(null)) {
			// buttons.put(ButtonsLabel.BUY, false);
			// messages = "Pay rent $"
			// + city.getRent(player, city.getOwner())
			// + " to " + city.getOwner().getName();
			// state.put("owner", city.getOwner().getColor());
			// state.put("owner_money", city.getOwner().getMoney());
			// } else if (player.equals(city.getOwner())) {
			// messages = "Owner " + player.getName();
			// } else if (!player.equals(city.getOwner())
			// && city.getOwner() != null && city.isMortage()) {
			// buttons.put(ButtonsLabel.BUY, false);
			// messages = "Object is mortage";
			// state.put("owner", city.getOwner().getColor());
			// state.put("owner_money", city.getOwner().getMoney());
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
			// } else if (cell instanceof RailCard) {
			// RailCard rail = (RailCard) cell;
			// try {
			// rail.effectOnPlayer(player);
			// // if (!player.equals(rail.getOwner())
			// // && rail.getOwner() != null && !rail.isMortage()) {
			// // // if (!player.equals(rail.getOwner()) &&
			// // // !rail.getOwner().equals(null)) {
			// // buttons.put(ButtonsLabel.BUY, false);
			// // messages = "Pay rent $"
			// // + rail.getRent(player, rail.getOwner())
			// // + " to " + rail.getOwner().getName();
			// // state.put("owner", rail.getOwner().getColor());
			// // state.put("owner_money", rail.getOwner().getMoney());
			// // } else if (!player.equals(rail.getOwner())
			// // && rail.getOwner() != null && rail.isMortage()) {
			// // buttons.put(ButtonsLabel.BUY, false);
			// // messages = "Object is mortage";
			// // state.put("owner", rail.getOwner().getColor());
			// // state.put("owner_money", rail.getOwner().getMoney());
			// // } else {
			// // buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
			// // }
			// // if (player.equals(rail.getOwner())) {
			// // messages = "Owner " + player.getName();
			// // }
			// if (rail.getOwner() == null) {
			// buttons.put(ButtonsLabel.AUCTION, true);
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// buttons.put(ButtonsLabel.PAY, false);
			// }
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
					JailCard jailCard = (JailCard) CardFactory
							.chooseCard(player);
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
						buttons.put(ButtonsLabel.BUY, player.canBuy(rail));
						messages = player.messages(rail);
						if (rail.getOwner() != null) {
							state.put("owner", (rail).getOwner().getColor());
							state.put("owner_money", (rail).getOwner()
									.getMoney());
						}
						// if (!player.equals(rail.getOwner())
						// && rail.getOwner() != null && !rail.isMortage()) {
						// buttons.put(ButtonsLabel.BUY, false);
						// messages = "Pay rent $"
						// + rail.getRent(player, rail.getOwner())
						// + " to " + rail.getOwner().getName();
						// state.put("owner", rail.getOwner().getColor());
						// state.put("owner_money", rail.getOwner().getMoney());
						// } else if (!player.equals(rail.getOwner())
						// && rail.getOwner() != null && rail.isMortage()) {
						// buttons.put(ButtonsLabel.BUY, false);
						// messages = "Object is mortage";
						// state.put("owner", rail.getOwner().getColor());
						// state.put("owner_money", rail.getOwner().getMoney());
						// } else {
						// buttons.put(ButtonsLabel.BUY, rail.canBuy(player));
						// }
						// if (player.equals(rail.getOwner())) {
						// messages = "Owner " + player.getName();
						// }
						if (rail.getOwner() == null) {
							buttons.put(ButtonsLabel.AUCTION, true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					buttons.put(ButtonsLabel.PAY, false);

				} else if (chanceCell instanceof JailCard) {
					if (player.getPosition() == CellPositions.JAIL) {
						JailCard jailCard = (JailCard) CardFactory
								.chooseCard(player);
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

	// public static void main(String[] args) {
	// StartGame.initCities();
	// // StartGame g = new StartGame();
	// // g.initCities();
	// Player player = new Player("1", 2, 1200,"red");
	// Player player2 = new Player("2", 2, 1200,"red");
	// CityCard c1 = (CityCard) CardFactory.chooseCard(player);
	// c1.buyCityOrRail(player);
	// System.out.println(c1.info());
	// c1.mortage(player);
	// System.out.println(CardFactory.chooseCard(player).info());
	// System.out.println(c1.info());
	// c1.effectOnPlayer(player2);
	// // System.out.println("-----------");
	// // System.out.println(CardFactory.chooseCard(player).info());
	// System.out.println("**************");
	// System.out.println(StartGame.boardCities);
	// System.out.println(StartGame.boardCities().get(2).info());
	// System.out.println(StartGame.boardCities().get(4).info());
	// System.out.println(StartGame.boardCities().get(7).info());
	// System.out.println(StartGame.boardCities().get(9).info());
	// System.out.println(StartGame.boardCities().get(10).info());
	// CityCard c2 = (CityCard) CardFactory.chooseCard(player2);
	// System.out.println("----");
	// System.out.println(c2.info());
	// }
}
