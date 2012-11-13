package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	private static final String ROLL = "roll";
	private static final String START = "start";
	private static final String READY = "ready";
	private Game monopolyGame;
	private User creator;

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

	public MonopolyManager(Game monopolyGame) {
		this.monopolyGame = monopolyGame;
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {
		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);

		Map<String, Object> response = new HashMap<String, Object>();
		WebSocketTransport socketTransport = WebSocketTransport.getInstance();
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
		}
		
		socketTransport.sendMessage(idPlayer, response);
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
