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
}