package com.web.app.worldgames.domain.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.web.app.worldgames.domain.Room;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class StartGame {
	public static Map<Integer, SellableCard> boardCities = new HashMap<Integer, SellableCard>();
	public static Map<Integer, SellableCard> boardRails = new HashMap<Integer, SellableCard>();
	static private List<Player> playerList = new ArrayList<Player>();
	static private List<Player> permanentlyPlayerList = new CopyOnWriteArrayList<Player>();

//	public static void start(Room room) {
//		for (User user : room.getRoomMembers()) {
//			playerList.add(new Player(user, CellPositions.START,
//					CardPrices.START_MONEY, false));
//		}
//	}

	public List<Player> playersList() {
		return playerList;
	}

	public static void startTemp() {
		permanentlyPlayerList.add(new Player("Player 1", CellPositions.START,
				CardPrices.START_MONEY, false));
		permanentlyPlayerList.add(new Player("Player 2", CellPositions.START,
				CardPrices.START_MONEY, false));
		permanentlyPlayerList.add(new Player("Player 3", CellPositions.START,
				CardPrices.START_MONEY, false));
		permanentlyPlayerList.add(new Player("Player 4", CellPositions.START,
				CardPrices.START_MONEY, false));
	}

	public static void deleteLoserPlayer(List<Player> players, Player player) {
		players.remove(player);
	}

	public List<Player> playersPermanentlyList() {
		return permanentlyPlayerList;
	}

	public static void initRails() {
		boardRails.put(6, new RailCard(Rails.RAIL1));
		boardRails.put(16, new RailCard(Rails.RAIL2));
		boardRails.put(26, new RailCard(Rails.RAIL3));
		boardRails.put(36, new RailCard(Rails.RAIL4));
	}

	public static void initCities() {
		boardCities.put(2, new CityCard(Cities.GDYNIA));
		boardCities.put(4, new CityCard(Cities.TAIPEI));
		boardCities.put(7, new CityCard(Cities.TOKYO));
		boardCities.put(9, new CityCard(Cities.BARCELONA));
		boardCities.put(10, new CityCard(Cities.ATHENS));
		boardCities.put(12, new CityCard(Cities.ISTAMBUL));
		boardCities.put(14, new CityCard(Cities.KYIV));
		boardCities.put(15, new CityCard(Cities.TORONTO));
		boardCities.put(17, new CityCard(Cities.ROME));
		boardCities.put(19, new CityCard(Cities.SHANGHAI));
		boardCities.put(20, new CityCard(Cities.VANCOVER));
		boardCities.put(22, new CityCard(Cities.SYDNEY));
		boardCities.put(24, new CityCard(Cities.NEW_YORK));
		boardCities.put(25, new CityCard(Cities.LONDON));
		boardCities.put(27, new CityCard(Cities.BEIJING));
		boardCities.put(28, new CityCard(Cities.HONG_KONG));
		boardCities.put(30, new CityCard(Cities.JERUSALEM));
		boardCities.put(32, new CityCard(Cities.PARIS));
		boardCities.put(33, new CityCard(Cities.BELGRADE));
		boardCities.put(35, new CityCard(Cities.CAPE_TOWN));
		boardCities.put(38, new CityCard(Cities.RIGA));
		boardCities.put(40, new CityCard(Cities.MONTREAL));

	}

	public static Map<Integer, SellableCard> boardCities() {
		return boardCities;
	}

	public static Map<Integer, SellableCard> boardRails() {
		return boardRails;
	}

	public static void exit() {
		System.exit(0);
	}
}
