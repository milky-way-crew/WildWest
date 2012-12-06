package com.web.app.worldgames.domain.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class StartGame {
	public static Map<Integer, SellableCard> boardCities = new HashMap<Integer, SellableCard>();
	public static Map<Integer, SellableCard> boardRails = new HashMap<Integer, SellableCard>();
	
	public static void initRails() {
		boardRails.put(6, new RailCard(Rails.RAIL1, null));
		boardRails.put(16, new RailCard(Rails.RAIL2, null));
		boardRails.put(26, new RailCard(Rails.RAIL3, null));
		boardRails.put(36, new RailCard(Rails.RAIL4, null));
	}

	public static void initCities() {
		boardCities.put(2, new CityCard(Cities.TENT, null, false));
		boardCities.put(4, new CityCard(Cities.CIRCUS, null, false));
		boardCities.put(7, new CityCard(Cities.POND, null, false));
		boardCities.put(9, new CityCard(Cities.FOUNTAIN, null, false));
		boardCities.put(10, new CityCard(Cities.UNIVERSITY, null, false));
		boardCities.put(12, new CityCard(Cities.WINDMILL, null, false));
		boardCities.put(14, new CityCard(Cities.FISHERY, null, false));
		boardCities.put(15, new CityCard(Cities.LITTLE_FARM, null, false));
		boardCities.put(17, new CityCard(Cities.CATHEDRAL, null, false));
		boardCities.put(19, new CityCard(Cities.MONASTERY, null, false));
		boardCities.put(20, new CityCard(Cities.GRAVEYARD, null, false));
		boardCities.put(22, new CityCard(Cities.FORT, null, false));
		boardCities.put(24, new CityCard(Cities.CITY, null, false));
		boardCities.put(25, new CityCard(Cities.FORTRESS, null, false));
		boardCities.put(27, new CityCard(Cities.OBELISK, null, false));
		boardCities.put(28, new CityCard(Cities.ARCH, null, false));
		boardCities.put(30, new CityCard(Cities.MAZE, null, false));
		boardCities.put(32, new CityCard(Cities.MINE, null, false));
		boardCities.put(33, new CityCard(Cities.HUNTER, null, false));
		boardCities.put(35, new CityCard(Cities.BLACKSMITH, null, false));
		boardCities.put(38, new CityCard(Cities.SQUARE_TOWER, null, false));
		boardCities.put(40, new CityCard(Cities.TOWER_ROUND, null, false));

	}

	public static  Map<Integer, SellableCard> boardCities() {
		return boardCities;
	}

	public static Map<Integer, SellableCard> boardRails() {
		return boardRails;
	}
}
