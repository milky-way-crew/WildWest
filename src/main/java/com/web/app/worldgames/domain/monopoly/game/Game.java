package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.service.interfaces.IStatisticsServiceManager;

public class Game {
	private int id;
	private boolean started = false;
	private boolean end = false;
	private Player currentPlayer = null;
	public List<Player> playerList = new ArrayList<Player>();
	public List<Player> loserList = new ArrayList<Player>();
	private List<SellableCard> activeBoard = new ArrayList<SellableCard>();

	private static final Logger log = Logger.getLogger(Game.class);
	@Autowired
	private  IStatisticsServiceManager userService;
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

	public List<SellableCard> getActiveBoard() {
		return activeBoard;
	}

	public void addToActivBoard(SellableCard card) {
		activeBoard.add(card);
	}

	public void removeFromActivBoard(SellableCard card) {
		activeBoard.remove(card);
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

	/**
	 * Method add new user to player list in game
	 * 
	 * @param user
	 */
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

	/**
	 * The execute method add player in loser list if he leaves game
	 * 
	 * @param player
	 */
	public void addLoser(Player player) {
		loserList.add(player);
	}

	/**
	 * 
	 * @return true if all players ready to start game
	 */
	public boolean isReadyToStart() {
		for (Player players : getAllPlayers()) {
			if (players.isReadyToStart() && playerList.size() > 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The execute method starting game
	 */
	public void start() {
		try{
			
		StartGame.initCities();
		StartGame.initRails();
		this.setStarted(true);
		if (currentPlayer == null) {
			this.setCurrentPlayer(this.getAllPlayers().get(0));
			for(Player player:this.getAllPlayers()){
				log.info(":::::::::::::::  ID:::"+player.getId());
				//userService.incrementUserAllGames(player.getId(), "monopoly");
				}
		}
		}catch(Exception e){
			e.printStackTrace();
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

	public Player getPreventPlayer() {
		int turn = 0;
		Player preventPlayer = null;
		if (this.isStarted()) {
			turn = this.getAllPlayers().indexOf(this.getCurrentPlayer());
			if (turn == 0) {
				preventPlayer = this.getAllPlayers().get(
						this.getAllPlayers().size() - 1);
			} else {
				turn = turn - 1;
				preventPlayer = this.getAllPlayers().get(turn);
			}
		}
		log.info("PREVENT PLAYER" + preventPlayer);
		return preventPlayer;
	}

	public Map<Integer, Map<String, Object>> refreshBoard() {
		Map<Integer, Map<String, Object>> gameBoard = new HashMap<Integer, Map<String, Object>>();
		for (SellableCard card : activeBoard) {
			if (card instanceof CityCard) {
				gameBoard.put(card.getPosition(),
						((CityCard) card).currentCityState());
			} else if (card instanceof RailCard) {
				gameBoard.put(card.getPosition(),
						((RailCard) card).currentRailState());
			}
		}
		return gameBoard;
	}

	public Map<String, Map<String, Object>> refreshPlayers() {
		Map<String, Map<String, Object>> players = new HashMap<String, Map<String, Object>>();
		for (Player player : playerList) {
			players.put(player.getColor(), player.currentPlayerState());
		}
		return players;
	}
}