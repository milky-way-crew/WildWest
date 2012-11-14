package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	private static final String ROLL = "roll";
	private static final String START = "start";
	private static final String READY = "ready";
	private static final String NEXT = "next";
	private Game monopolyGame;
	private User creator;

	public MonopolyManager(Game monopolyGame) {
		this.setMonopolyGame(monopolyGame);
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
		getMonopolyGame().addPlayers(creator);
		// monopolyGame.setCurrentPlayer(new Player(creator,
		// CellPositions.START, CardPrices.START_MONEY,
		// "RED"));
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {

		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);

		Map<String, Object> response = new HashMap<String, Object>();
		// WebSocketTransport socketTransport =
		// WebSocketTransport.getInstance();
		if ($(type).equals(READY)) {
			log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
			Player currentPlayer = getPlayerById(idPlayer);
			currentPlayer.setReadyToStart(true);
			log.info("[PLAYER IS READY TO START: ]"
					+ currentPlayer.isReadyToStart());
			response.put("type", READY);
		}

		if ($(type).equals(START)) {
			if (getMonopolyGame().isReadyToStart()) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				getMonopolyGame().start();
				log.info("[GAME IS STARTED] " + getMonopolyGame().isStarted());
				broadcast(response);
			}
			response.put("type", START);
			response.put("start", getMonopolyGame().isStarted());
		}
		if (getMonopolyGame().isStarted()) {
			if ($(type).equals(ROLL)) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				currentPlayer.rollDicesAndMove();
				log.info("[Player: ]" + currentPlayer.getColor());
				log.info("[Dice1: ]" + currentPlayer.getDiceOne());
				log.info("[Dice2: ]" + currentPlayer.getDiceTwo());
				log.info("[Cell: ]" + CardFactory.chooseCard(currentPlayer));
				response.put("type", ROLL);
				response.put("dice1", currentPlayer.getDiceOne());
				response.put("dice2", currentPlayer.getDiceTwo());
				response.put("player", currentPlayer.getColor());
				try {
					response.put("buttons", GameAction.action(CardFactory.chooseCard(currentPlayer), currentPlayer));					
				// response.putAll(GameAction.action(
				// 		CardFactory.chooseCard(currentPlayer), currentPlayer));
				} catch (Exception e) {
					log.error("Exception in [ROLL] section", e);
				}
				broadcast(response);
			}
			if ($(type).equals(ButtonsLabel.BUY)) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				SellableCard card = (SellableCard) CardFactory
						.chooseCard(currentPlayer);
				log.info("[Cell: ]" + card);
				card.buyCityOrRail(currentPlayer);
				log.info("[Player: ]" + currentPlayer.getColor()
						+ "buy this cell");
				log.info("[Player: ]" + currentPlayer.getColor() + " money: "
						+ currentPlayer.getMoney());
				response.put("type", ButtonsLabel.BUY);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
				broadcast(response);
			}
			if ($(type).equals(ButtonsLabel.REFUSE)) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				SellableCard card = (SellableCard) CardFactory
						.chooseCard(currentPlayer);
				card.refuse(currentPlayer);
				log.info("[Player: ]" + currentPlayer.getColor()
						+ " refuse");
				response.put("type", ButtonsLabel.REFUSE);
				response.put("player", currentPlayer.getColor());
				//response.put("player_money", currentPlayer.getMoney());
				broadcast(response);
			}
			if ($(type).equals(ButtonsLabel.PAY)) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				Cell cell = CardFactory.chooseCard(currentPlayer);
				if (cell instanceof SellableCard) {
					SellableCard card = (SellableCard) cell;
					card.payRentToOwner(currentPlayer, card.getOwner(),
							card.getRent(currentPlayer, card.getOwner()));
				} else if (cell instanceof JailCard) {
					JailCard card = (JailCard) cell;
					card.payRansom(currentPlayer);
				}
				log.info("[Player: ]" + currentPlayer.getColor()
						+ " pay rent");
				response.put("type", ButtonsLabel.PAY);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
				broadcast(response);
			}
			if ($(type).equals(ButtonsLabel.MORTAGE)) {
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				SellableCard card = (SellableCard) CardFactory
						.chooseCard(currentPlayer);
				card.mortage(currentPlayer);
				response.put("type", ButtonsLabel.MORTAGE);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
				broadcast(response);
			}
			if ($(type).equals(NEXT)) {
				log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
				getMonopolyGame().setCurrentPlayer(getMonopolyGame().getNextPlayer());
				Player currentPlayer = getMonopolyGame().getCurrentPlayer();
				log.info("[Player: ]" + currentPlayer.getColor()
						+ " can roll dice");
				response.put("type", NEXT);
				response.put("next", currentPlayer);
				broadcast(response);
			}
		}
		// socketTransport.sendMessage(idPlayer, response);

		// socketTransport.sendMessage(idPlayer, response);
		// not sure if we need this
		return response;
	}

	public void broadcast(Map<String, ? extends Object> message) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		for (Player player : getMonopolyGame().getAllPlayers()) {
			transport.sendMessage(player.getId(), message);
		}
	}

	/**
	 * Pretify string
	 * 
	 * @param s
	 * @return
	 */
	private String $(String s) {
		return s.toLowerCase().trim();
	}

	public Player getPlayerById(int idPlayer) {
		for (Player players : getMonopolyGame().getAllPlayers()) {
			if (players.getId() == idPlayer) {
				return players;
			}
		}
		return null;
	}

	public void addClient(User client) {
		getMonopolyGame().addPlayers(client);
	}

	public Game getMonopolyGame() {
		return monopolyGame;
	}

	public void setMonopolyGame(Game monopolyGame) {
		this.monopolyGame = monopolyGame;
	}
}
