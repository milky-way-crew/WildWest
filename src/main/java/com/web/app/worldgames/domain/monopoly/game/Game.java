package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
	private Map<Integer, Map<String, Object>> gameBoard = new HashMap<Integer, Map<String, Object>>();
	private Map<String, Map<String, Object>> players = new HashMap<String, Map<String, Object>>();

	public List<Player> playerList = new ArrayList<Player>();
	public List<Player> loserList = new ArrayList<Player>();
	private static final Logger log = Logger.getLogger(Game.class);
	private List<SellableCard> activeBoard = new ArrayList<SellableCard>();
	private int auctionPrice;
	private long time;
	public List<SellableCard> getActiveBoard() {
		return activeBoard;
	}

	public void addToActivBoard(SellableCard card) {
		activeBoard.add(card);
	}

	public void removeFromActivBoard(SellableCard card) {
		activeBoard.remove(card);
	}

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

	public Map<Integer, Map<String, Object>> getGameBoard() {
		return gameBoard;
	}

	// public void setGameBoard(Map<Integer, Map<String, Object>> gameBoard) {
	// this.gameBoard = gameBoard;
	// }

	public int getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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

	public Map<Integer, Map<String, Object>> refreshBoard() {
		Map<String, Object> temp = new HashMap<String, Object>();
		for (SellableCard card : activeBoard) {
			temp.put("position", card.getPosition());
			temp.put("owner", card.getOwner().getColor());
			temp.put("mortage", card.isMortage());
			if (card instanceof CityCard) {
				temp.put("houses", ((CityCard) card).getNumbersOfHouses());
				temp.put("hotel", ((CityCard) card).isHotel());
			}
			gameBoard.put(card.getPosition(), temp);
		}
		return gameBoard;
	}

	public Map<String, Map<String, Object>> refreshPlayers() {
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Player player : playerList) {
			temp.put("color", player.getColor());
			temp.put("money", player.getMoney());
			temp.put("position", player.getPosition());
			players.put(player.getColor(), temp);
		}
		return players;
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
	public long checkAuctionEndTime(int auctionPrice, long time){
	long start =time;
	long end=0;
	log.info("IN CHECT METHOD:::"+ System.currentTimeMillis());
		try{
			TimeUnit.SECONDS.sleep(30); 
			end=System.currentTimeMillis();
			log.info("IN CHECT METHOD:::"+ System.currentTimeMillis());
			log.info("IN CHECT METHOD::: start="+start);
		}catch(Exception e){
			
		}
		return end-start;
	}
}