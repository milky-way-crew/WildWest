package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Game {
	private int id;
	boolean started = false;
	boolean end = false;
	Player currentPlayer = null;

	public List<Player> playerList = new ArrayList<Player>();
	public  Map<Integer, SellableCard> boardCities = new HashMap<Integer, SellableCard>();
	public  Map<Integer, SellableCard> boardRails = new HashMap<Integer, SellableCard>();

	// public void setPlayer(Room room) {
	// for (User user : room.getRoomMembers()) {
	// playerList.add(new Player(user, CellPositions.START,
	// CardPrices.START_MONEY, false));
	// }
	// }

	// for test
	public void setPlayer() {
		playerList.add((new Player("Player 1", CellPositions.START,
				CardPrices.START_MONEY, false, PlayerColors.PLAYER_1)));
		playerList.add((new Player("Player 2", CellPositions.START,
				CardPrices.START_MONEY, false, PlayerColors.PLAYER_2)));
		playerList.add((new Player("Player 3", CellPositions.START,
				CardPrices.START_MONEY, false, PlayerColors.PLAYER_3)));
		playerList.add((new Player("Player 4", CellPositions.START,
				CardPrices.START_MONEY, false, PlayerColors.PLAYER_4)));
	}

	public Game( ) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<Player> playerList() {
		return playerList;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isReadyToStart() {
		// this.setPlayer(room);
		this.setPlayer();
		if (!playerList.isEmpty() && playerList.size() > 1) {
			return true;
		} else
			return false;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean start() {
		if (this.isReadyToStart()) {
			StartGame.initCities();
			StartGame.initRails();
			this.setStarted(true);
			if (currentPlayer == null) {
				this.setCurrentPlayer(this.playerList().get(0));
			}
			return true;
		}
		return false;
	}

	public Player getNextPlayer() {
		if (this.isStarted()) {
			int indexCurrentPlayer = this.playerList().indexOf(currentPlayer);
			if (indexCurrentPlayer == playerList().size() - 1) {
				return this.playerList().get(0);
			}
			return this.playerList().get(indexCurrentPlayer++);
		}
		return null;
	}

}