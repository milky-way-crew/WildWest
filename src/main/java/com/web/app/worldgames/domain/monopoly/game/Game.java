package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Game {
	private int id;
	private boolean started = false;
	private boolean end = false;
	private Player currentPlayer = null;
	private Map<SellableCard, Map<String, Object>> gameBoard = new HashMap<SellableCard, Map<String, Object>>();
	public List<Player> playerList = new ArrayList<Player>();
	public List<Player> loserList = new ArrayList<Player>();
	private static final Logger log = Logger.getLogger(Game.class);

	public Game() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Player> getAllPlayers() {
		return playerList;
	}

	public List<Player> getAllLosers() {
		return loserList;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
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

	public Map<SellableCard, Map<String, Object>> refreshBoard(
			Map<SellableCard, Map<String, Object>> gameBoard, SellableCard card) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("position", card.getPosition());
		temp.put("owner", card.getOwner());
		temp.put("mortage", card.isMortage());
		if(card instanceof CityCard){
			temp.put("houses", ((CityCard) card).getNumbersOfHouses());
			temp.put("hotel", ((CityCard) card).isHotel());
		}
		gameBoard.put(card, temp);
		return gameBoard;
	}

	public void addPlayers(User user) {
		int listPlSize = playerList.size();
		String color = null;
		log.info("[PLAYER  LIST] " + playerList);
		for (PlayerColors playerColors : PlayerColors.values()) {
			if (playerColors.ordinal() == listPlSize) {
				color = playerColors.getColor();
			}
		}
		playerList.add(new Player(user, CellPositions.START,
				CardPrices.START_MONEY, color));
		log.info("[PLAYER  LIST AFTER --ADD--] " + playerList);
	}

	public boolean isReadyToStart() {
		for (Player players : getAllPlayers()) {
			if (players.isReadyToStart() && playerList.size() > 1) {
				return true;
			}
		}
		return false;
	}

	public void addLoser(Player player) {
		loserList.add(player);
	}

	public void start() {
		StartGame.initCities();
		StartGame.initRails();
		this.setStarted(true);
		if (currentPlayer == null) {
			this.setCurrentPlayer(this.getAllPlayers().get(0));
		}
	}

	public Player getNextPlayer() {
		int turn = 0;
		if (this.isStarted()) {
			turn = this.getAllPlayers().indexOf(this.getCurrentPlayer());
			turn = (turn + 1) % getAllPlayers().size();
			return this.getAllPlayers().get(turn);
		}
		return null;
	}
}