package com.web.app.worldgames.domain.monopoly;

import java.util.HashMap;
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
		boardCities.put(2, new CityCard(Cities.GDYNIA, null));
		boardCities.put(4, new CityCard(Cities.TAIPEI, null));
		boardCities.put(7, new CityCard(Cities.TOKYO, null));
		boardCities.put(9, new CityCard(Cities.BARCELONA, null));
		boardCities.put(10, new CityCard(Cities.ATHENS, null));
		boardCities.put(12, new CityCard(Cities.ISTAMBUL, null));
		boardCities.put(14, new CityCard(Cities.KYIV, null));
		boardCities.put(15, new CityCard(Cities.TORONTO, null));
		boardCities.put(17, new CityCard(Cities.ROME, null));
		boardCities.put(19, new CityCard(Cities.SHANGHAI, null));
		boardCities.put(20, new CityCard(Cities.VANCOVER, null));
		boardCities.put(22, new CityCard(Cities.SYDNEY, null));
		boardCities.put(24, new CityCard(Cities.NEW_YORK, null));
		boardCities.put(25, new CityCard(Cities.LONDON, null));
		boardCities.put(27, new CityCard(Cities.BEIJING, null));
		boardCities.put(28, new CityCard(Cities.HONG_KONG, null));
		boardCities.put(30, new CityCard(Cities.JERUSALEM, null));
		boardCities.put(32, new CityCard(Cities.PARIS, null));
		boardCities.put(33, new CityCard(Cities.BELGRADE, null));
		boardCities.put(35, new CityCard(Cities.CAPE_TOWN, null));
		boardCities.put(38, new CityCard(Cities.RIGA, null));
		boardCities.put(40, new CityCard(Cities.MONTREAL, null));

	}

	public static Map<Integer, SellableCard> boardCities() {
		return boardCities;
	}

	public static Map<Integer, SellableCard> boardRails() {
		return boardRails;
	}
}
