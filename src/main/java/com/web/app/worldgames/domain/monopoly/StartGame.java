package com.web.app.worldgames.domain.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.app.worldgames.domain.Room;
import com.web.app.worldgames.domain.User;

public class StartGame {
	static Map<Integer, SellableCard> board = new HashMap<Integer, SellableCard>();
	private List<Player> playerList = new ArrayList<Player>();

	public void start(Room room) {
		for (User user : room.getRoomMembers()) {
			playerList.add(new Player(user, CellPositions.STAR,
					CardPrices.START_MONEY, false));
		}
	}

	public List<Player> playersList() {
		return playerList;
	}

	public void init() {
		board.put(2, new CityCard(Cities.VENESIA));
		board.put(4, new CityCard(Cities.BARI));
		board.put(6, new PortCard(CardPrices.PORT1, 30, 50, 70, 80));
		board.put(7, new CityCard(Cities.CORSICA));
		board.put(9, new CityCard(Cities.PONTOS));
		board.put(10, new CityCard(Cities.SARDINIA));
		board.put(12, new CityCard(Cities.GAUL));
		board.put(14, new CityCard(Cities.ILIRIC));
		board.put(15, new CityCard(Cities.MACEDONIA));
		board.put(16, new PortCard(CardPrices.PORT2, 30, 50, 70, 80));
		board.put(17, new CityCard(Cities.ACHAIA));
		board.put(18, new CityCard(Cities.BITHYNIA));
		board.put(20, new CityCard(Cities.ASSYRIA));
		board.put(22, new CityCard(Cities.BAETIC));
		board.put(24, new CityCard(Cities.GALATIA));
		board.put(25, new CityCard(Cities.DACIA));
		board.put(26, new PortCard(CardPrices.PORT3, 30, 50, 70, 80));
		board.put(27, new CityCard(Cities.CAPADOCIA));
		board.put(28, new CityCard(Cities.KOMMAGENA));
		board.put(30, new CityCard(Cities.LYCAONIA));
		board.put(32, new CityCard(Cities.EPIRUS));
		board.put(33, new CityCard(Cities.OSROENA));
		board.put(35, new CityCard(Cities.PAMPULIA));
		board.put(36, new PortCard(CardPrices.PORT3, 30, 50, 70, 80));
		board.put(38, new CityCard(Cities.PANNONIA));
		board.put(40, new CityCard(Cities.SICILY));

	}
	public static Map<Integer, SellableCard> board(){
		return board;
	}
}
