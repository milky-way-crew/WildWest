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
	private static Map<Integer, MonopolyManager> serverMap = new HashMap<Integer, MonopolyManager>();
	private static int i = 0;

	public int createGame(User host) {
		MonopolyManager manager = new MonopolyManager(new Game());
		manager.setCreator(host);
		getServerMap().put(++i, manager);
		return i;
	}

	public void removeGameById(int idGame) {
		getServerMap().remove(idGame);
		// TODO: notify users about removing game
	}

	public void connect(User client, int idGame) {
		MonopolyManager manager = getServerMap().get(idGame);
		// TODO: uncomment & add that method
		manager.addClient(client);
	}

	public MonopolyManager getGameById(int idGame) {
		return getServerMap().get(idGame);
	}
	
	private MonopolyService() {
	}
	
	public static MonopolyService getInstance() {
		return _INSTANCE;
	}
	
	public Map<Integer, MonopolyManager> getAllGames() {
		return getServerMap();
	}

	public static Map<Integer, MonopolyManager> getServerMap() {
		return serverMap;
	}

}
