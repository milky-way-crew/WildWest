package com.web.app.worldgames.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.Game;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

@Service
public class MonopolyService {

	public static Map<Integer, MonopolyManager> monolopyGame = new HashMap<Integer, MonopolyManager>();
	private static  int i=0;

	public static void createGame(User user) {
		MonopolyManager manager = new MonopolyManager(new Game());
		manager.setCreator(user);
		monolopyGame.put(i++, manager);
	}

	public static void removeGame(Game game) {

	}

	public static void connect() {

	}

	public Player getPlayerById(int id) {
		Game game = new Game();
		for (Player players : game.playerList) {
			if (players.getId() == id) {
				return players;
			}
		}
		return null;
	}

	public Game getGameById(int id) {
		return null;
	}
=======
package com.web.app.worldgames.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.game.Game;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;
import com.web.app.worldgames.service.interfaces.IMonopolyService;

@Service
public class MonopolyService implements IMonopolyService {
	private static final MonopolyService _INSTANCE = new MonopolyService();
	public static Map<Integer, MonopolyManager> serverMap = new HashMap<Integer, MonopolyManager>();
	private static int i = 0;

	public int createGame(User host) {
		MonopolyManager manager = new MonopolyManager(new Game());
		manager.setCreator(host);
		serverMap.put(++i, manager);
		return i;
	}

	public void removeGameById(int idGame) {
		serverMap.remove(idGame);
		// TODO: notify users about removing game
	}

	public void connect(User client, int idGame) {
		MonopolyManager manager = serverMap.get(idGame);
		// TODO: uncomment & add that method
		manager.addClient(client);
	}

	public MonopolyManager getGameById(int idGame) {
		return serverMap.get(idGame);
	}
	
	private MonopolyService() {
	}
	
	public static MonopolyService getInstance() {
		return _INSTANCE;
	}
	
	public Map<Integer, MonopolyManager> getAllGames() {
		return serverMap;
	}
}
