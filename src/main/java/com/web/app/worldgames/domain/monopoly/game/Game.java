package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class Game {
	private int id;
	private boolean started = false;
	private boolean end = false;
	private Player currentPlayer = null;

	public List<Player> playerList = new ArrayList<Player>();
	public List<Player> loserList = new ArrayList<Player>();
	// -------for test
	public List<User> userList = new ArrayList<User>();
	// public Map<Integer, SellableCard> boardCities = new HashMap<Integer,
	// SellableCard>();
	// public Map<Integer, SellableCard> boardRails = new HashMap<Integer,
	// SellableCard>();
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

	// ------------for test

	// public void setPlayers() {
	// for (int i = 0; i < getUserList().size(); i++) {
	// User user = getUserList().get(i);
	// addPlayers(user);
	// }
	// }

	public void setPlayer() {
		playerList.add((new Player("Player 1", CellPositions.START,
				CardPrices.START_MONEY, PlayerColors.PLAYER1)));
		playerList.add((new Player("Player 2", CellPositions.START,
				CardPrices.START_MONEY, PlayerColors.PLAYER2)));
		playerList.add((new Player("Player 3", CellPositions.START,
				CardPrices.START_MONEY, PlayerColors.PLAYER3)));
		playerList.add((new Player("Player 4", CellPositions.START,
				CardPrices.START_MONEY, PlayerColors.PLAYER4)));
	}

	public void addUser() {
		userList.add(new User(123, "Us1", "1234", "Player1123", "User@mail.ru",
				1, "picture1", "role"));
		userList.add(new User(2, "Us2", "124564", "Player2", "User2@mail.ru",
				2, "picture2", "role2"));
		userList.add(new User(3, "Us3", "12345", "Player3", "User3@mail.ru", 3,
				"picture3", "role3"));
	}

	public List<User> getUserList() {
		return userList;
	}

	// -----------

	// public static void main(String[] args) {
	// Game g = new Game();
	// g.addUser();
	// g.setPlayers();
	// for(Player p:g.getAllPlayers()){
	// System.out.println(p.getId());
	// System.out.println(p.getName());
	// System.out.println(p.getMoney());
	// System.out.println(p.getColor());
	// }
	// }

}