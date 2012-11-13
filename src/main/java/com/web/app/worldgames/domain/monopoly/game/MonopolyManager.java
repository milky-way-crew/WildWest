package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;

public class MonopolyManager {
	private static final String ROLL = "roll";
	private Game monopolyGame;
	private User creator;

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
		monopolyGame.setCurrentPlayer(new Player(creator.getNickname(),
				CellPositions.START, CardPrices.START_MONEY, false,
				PlayerColors.PLAYER_1));
	}

	public MonopolyManager(Game monopolyGame) {
		this.monopolyGame = monopolyGame;
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {
		// Game game = new Game();
		// Map<String, Object> message = new HashMap<String, Object>();
		// Map<Object, Object> result = new HashMap<Object, Object>();
		// Player currentPlayer = game.getCurrentPlayer();
		// Player nextPlayer = null;
		// if (!game.isStarted()) {
		// if (game.isReadyToStart()) {
		// if (type.toLowerCase().trim().equals("ready")) {
		// // MonopolyService.createGame(creator);
		// message.put("game_start", game.isStarted());
		// String msg = "Game is started";
		// for (Player players : game.playerList()) {
		// // WebSocketTransport.sendMessage(players.getId(), msg);
		// }
		// return message;
		// }
		// game.start();
		// }
		// } else {
		// if (type.toLowerCase().trim().equals("roll")) {
		// currentPlayer.rollDicesAndMove();
		// result.put(currentPlayer.getDiceOne(),
		// currentPlayer.getDiceTwo());
		// message.put("roll_dice", result);
		// return message;
		// } else if (type.toLowerCase().trim().equals("done")) {
		// nextPlayer = game.getNextPlayer();
		// game.setCurrentPlayer(nextPlayer);
		// String msg = "Next Player " + nextPlayer.getName()
		// + " roll dices";
		// for (Player players : game.playerList()) {
		// // WebSocketTransport.sendMessage(players.getId(), msg);
		// }
		// }
		// }
		// return message;

		Map<String, Object> response = new HashMap<String, Object>();
		WebSocketTransport socketTransport = WebSocketTransport.getInstance();
		
		
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

	
	private void broadcast(Map<String, ? extends Object> message) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		// for (Player player : monopolyGame.getAllPlayers()) {
		// transport.sendMessage(player.getId(), message);
		// }
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

}
