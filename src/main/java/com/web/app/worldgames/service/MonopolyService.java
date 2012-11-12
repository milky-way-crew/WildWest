package com.web.app.worldgames.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.Game;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;
import com.web.app.worldgames.service.interfaces.IMonopolyService;

@Service
public class MonopolyService implements IMonopolyService {
	public static Map<Integer, MonopolyManager> serverMap = new HashMap<Integer, MonopolyManager>();
	private static int i = 0;

	public void createGame(User host) {
		MonopolyManager manager = new MonopolyManager(new Game());
		manager.setCreator(host);
		serverMap.put(i++, manager);
	}

	public void removeGameById(int idGame) {
		serverMap.remove(idGame);
		// notify users about removing game
	}

	public void connect(User client, int idGame) {
		MonopolyManager manager = serverMap.get(idGame);
		// TODO: add that method
		// manager.addClient(client);
	}

	public Player getPlayerById(int idPlayer) {
		Game game = new Game();
		for (Player players : game.playerList) {
			if (players.getId() == idPlayer) {
				return players;
			}
		}
		return null;
	}

	public MonopolyManager getGameById(int idGame) {
		return null;
	}
}