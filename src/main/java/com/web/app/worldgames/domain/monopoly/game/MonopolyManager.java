package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;

public class MonopolyManager {
	public Game monopolyGame;
	User creator;

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public MonopolyManager(Game monopolyGame) {
		super();
		this.monopolyGame = monopolyGame;
	}

	public Map<String, Object> onMessage(int idGame, String type) {
		Game game = new Game();
		Map<String, Object> message = new HashMap<String, Object>();
		Map<Object, Object> result = new HashMap<Object, Object>();
		Player currentPlayer = game.getCurrentPlayer();
		Player nextPlayer = null;
		if (!game.isStarted()) {
			if (game.isReadyToStart()) {
				if (type.toLowerCase().trim().equals("ready")) {
					// MonopolyService.createGame(creator);
					message.put("game_start", game.isStarted());
					String msg = "Game is started";
					for (Player players : game.playerList()) {
						WebSocketTransport.sendMassage(players.getId(), msg);
					}
					return message;
				}
				game.start();
			}
		} else {
			if (type.toLowerCase().trim().equals("roll")) {
				currentPlayer.rollDicesAndMove();
				result.put(currentPlayer.getDiceOne(),
						currentPlayer.getDiceTwo());
				message.put("roll_dice", result);
				return message;
			} else if (type.toLowerCase().trim().equals("done")) {
				nextPlayer = game.getNextPlayer();
				game.setCurrentPlayer(nextPlayer);
				String msg = "Next Player " + nextPlayer.getName()
						+ " roll dices";
				for (Player players : game.playerList()) {
					WebSocketTransport.sendMassage(players.getId(), msg);
				}
			}
		}
		return message;
	}
}
