package com.web.app.worldgames.domain.monopoly.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.web.app.worldgames.domain.User;
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
	public List<User> userList = new ArrayList<User>();
	public Map<Integer, SellableCard> boardCities = new HashMap<Integer, SellableCard>();
	public Map<Integer, SellableCard> boardRails = new HashMap<Integer, SellableCard>();

	// public void setPlayer(Room room) {
	// for (User user : room.getRoomMembers()) {
	// playerList.add(new Player(user, CellPositions.START,
	// CardPrices.START_MONEY, false));
	// }
	// }

	public void addUser() {
		userList.add(new User(1, "Us1", "1234", "Player1", "User@mail.ru", 1,
				"picture1", "role"));
		userList.add(new User(2, "Us2", "124564", "Player2", "User2@mail.ru",
				2, "picture2", "role2"));
		userList.add(new User(3, "Us3", "12345", "Player3", "User3@mail.ru", 3,
				"picture3", "role3"));
	}

	public List<User> getUserList() {
		return userList;
	}

	public void addPlayers(User user) {
		for (PlayerColors playerColors : PlayerColors.values()) {
			int listPlSize = playerList.size();
			String color = null;
			if (playerColors.ordinal() == listPlSize) {
				color = playerColors.getColor();
				playerList.add(new Player(user, CellPositions.START,
						CardPrices.START_MONEY, color));
			}
		}
	}

	public void setPlayers() {
		for (int i = 0; i < getUserList().size(); i++) {
			this.addPlayers(this.getUserList().get(i));
		}
	}

	// for test
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

	public Game() {

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

	public List<Player> getAllPlayers() {
		return playerList;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isReadyToStart() {
		for (Player players : getAllPlayers()) {
			if (players.isReadyToStart() && playerList.size() > 1) {
				return true;
			}
		}
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

	public void start() {
		//if (this.isReadyToStart()) {
			StartGame.initCities();
			StartGame.initRails();
			this.setStarted(true);
			if (currentPlayer == null) {
				this.setCurrentPlayer(this.getAllPlayers().get(0));
			}
			//return true;
		//}
		//return false;
	}

	public Player getNextPlayer() {
		if (this.isStarted()) {
			int indexCurrentPlayer = this.getAllPlayers()
					.indexOf(currentPlayer);
			if (indexCurrentPlayer == getAllPlayers().size() - 1) {
				return this.getAllPlayers().get(0);
			}
			return this.getAllPlayers().get(indexCurrentPlayer++);
		}
		return null;
	}

}