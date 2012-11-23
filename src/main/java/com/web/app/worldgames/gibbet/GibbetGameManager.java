package com.web.app.worldgames.gibbet;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.chess.ChessGameManager;

public class GibbetGameManager {
	private static final Logger log = Logger.getLogger(ChessGameManager.class);

	private GibbetGame game;

	public void setGame(GibbetGame game) {
		this.game = game;
	}

	public GibbetGame getGame() {
		return game;
	}


	public GibbetGameManager(GibbetGame gibbetGame) {
		this.game = gibbetGame;
	}

	public void setHost(GibbetPlayer user) {
		if (game != null && user != null) {
			log.info("Setting host: " + user);
			game.setWhite(user);
		} else {
			throw new NullPointerException("User object is " + user
					+ " Game object is: " + game);
		}
	}

	public GibbetPlayer getHost() {
		return game.getWhite();
	}

	public GibbetPlayer getClient() {
		return game.getBlack();
	}

	public void setClient(GibbetPlayer client) {
		log.info("Setting client" + client);
		game.setBlack(client);
		if (game.canBeStarted()) {
			game.startGame();
		}
	}
	
	public boolean isFull() {
		return game.getWhite() != null && game.getBlack() != null;
	}

	public boolean isStarted() {
		return game.isStarted();
	}
	
}
