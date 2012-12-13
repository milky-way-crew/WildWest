package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.ChanceCard;
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
		try {
			if (cell instanceof SellableCard) {
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

			} else {
				if (cell instanceof StartCard) {
					cell.effectOnPlayer(player);
				} else if (cell instanceof TaxCard) {
					cell.effectOnPlayer(player);
					messages = "Payed tax " + CardPrices.TAX;
				} else if (cell instanceof JailCard) {
					cell.effectOnPlayer(player);
					messages = ((JailCard) cell).getMsg();
					if (player.isInJail()) {
						buttons.put(ButtonsLabel.PAY,
								((JailCard) cell).canPayRansom(player));
						messages = "You may pay a ransom or roll dices";
					} else {
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
			buttons.putAll(ButtonsAction.buttonsAction(player));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
}
