package com.web.app.worldgames.gibbet;

import org.apache.log4j.Logger;






public class GibbetGame {
	private static final Logger log = Logger.getLogger(GibbetGame.class);
	
	
	private GibbetPlayer[] players;
	private boolean isStarted = false;
	private GibbetPlayer white, black;
	private GibbetPlayer currentPlayer;
	private boolean isEnded;
	
	public GibbetGame(GibbetPlayer player1, GibbetPlayer player2) {
		players = new GibbetPlayer[] { player1, player2 };
	}
	
	public GibbetGame() {
		// TODO Auto-generated constructor stub
	}

	public boolean canBeStarted() {
		return white != null && black != null
				&& white.isReady() && black.isReady();
	}
	
	public void startGame() {
		log.debug("Trying to start game");
		if (this.canBeStarted()) {
			log.debug("Game can be started");
			this.setStarted(true);
			if (getCurrentPlayer() == null) {
				this.players = new GibbetPlayer[] { white, black };
				setCurrentPlayer(white);
			}
			log.debug("Setting current player to WHITE");
		}
	}

	public GibbetPlayer getNextPlayer() {
		if (this.isStarted()) {
			return currentPlayer.getId() == white.getId() ? black : white;
		}
		return null;
	}
	
	
	
	
	public GibbetPlayer getWhite() {
		return white;
	}

	public void setWhite(GibbetPlayer white) {
		white.setType(GibbetPlayerTypesEnum.WHITE);
		this.white = white;
	}

	public GibbetPlayer getBlack() {
		return black;
	}

	public void setBlack(GibbetPlayer black) {
		black.setType(GibbetPlayerTypesEnum.BLACK);
		this.black = black;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public GibbetPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(GibbetPlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
