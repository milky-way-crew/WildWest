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
		this.monopolyGame = monopolyGame;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
		monopolyGame.addPlayers(creator);
		// monopolyGame.setCurrentPlayer(new Player(creator,
		// CellPositions.START, CardPrices.START_MONEY,
		// "RED"));
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {

		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);

		Map<String, Object> response = new HashMap<String, Object>();
		//WebSocketTransport socketTransport = WebSocketTransport.getInstance();
		if ($(type).equals(READY)) {
			Player currentPlayer = getPlayerById(idPlayer);
			currentPlayer.setReadyToStart(true);
			response.put("type", READY);
		}
		
		if ($(type).equals(START)) {
			if (monopolyGame.isReadyToStart()) {
				monopolyGame.start();
				broadcast(response);
			}
			response.put("type", START);
			response.put("start", monopolyGame.isStarted());
		}

		if ($(type).equals(ROLL)) {
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			currentPlayer.rollDicesAndMove();
			response.put("type", ROLL);
			response.put("dice1", currentPlayer.getDiceOne());
			response.put("dice2", currentPlayer.getDiceTwo());
			response.put("player", currentPlayer.getColor());
			response.putAll(GameAction.action(
					CardFactory.chooseCard(currentPlayer), currentPlayer));
			broadcast(response);
		}
		if ($(type).equals(ButtonsLabel.BUY)) {
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			card.buyCityOrRail(currentPlayer);
			response.put("type", ButtonsLabel.BUY);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
			broadcast(response);
		}
		if ($(type).equals(ButtonsLabel.REFUSE)) {
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			card.refuse(currentPlayer);
			response.put("type", ButtonsLabel.REFUSE);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
			broadcast(response);
		}
		if ($(type).equals(ButtonsLabel.PAY)) {
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			Cell cell = CardFactory.chooseCard(currentPlayer);
			if (cell instanceof SellableCard) {
				SellableCard card = (SellableCard) cell;
				card.payRentToOwner(currentPlayer, card.getOwner(),
						card.getRent(currentPlayer, card.getOwner()));
			} else if (cell instanceof JailCard) {
				JailCard card = (JailCard) cell;
				card.payRansom(currentPlayer);
			}
			response.put("type", ButtonsLabel.PAY);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
			broadcast(response);
		}
		if ($(type).equals(ButtonsLabel.MORTAGE)) {
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			// card
			response.put("type", ButtonsLabel.MORTAGE);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
			broadcast(response);
		}
		if ($(type).equals(NEXT)) {
			monopolyGame.setCurrentPlayer(monopolyGame.getNextPlayer());
			Player currentPlayer = monopolyGame.getCurrentPlayer();
			response.put("type", NEXT);
			response.put("next", currentPlayer);
			broadcast(response);
		}
		// socketTransport.sendMessage(idPlayer, response);

		
		//socketTransport.sendMessage(idPlayer, response);
		// not sure if we need this
		return response;
	}

	public void broadcast(Map<String, ? extends Object> message) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		for (Player player : monopolyGame.getAllPlayers()) {
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
		for (Player players : monopolyGame.getAllPlayers()) {
			if (players.getId() == idPlayer) {
				return players;
			}
		}
		return null;
	}

	public void addClient(User client) {
		monopolyGame.addPlayers(client);
	}
}
