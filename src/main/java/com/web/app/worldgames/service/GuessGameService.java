package com.web.app.worldgames.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.guess.GuessGame;
import com.web.app.worldgames.domain.guess.GuessPlayer;

public enum GuessGameService {
	INSTANCE;
	
	private static final Logger log = Logger.getLogger(GuessGameService.class);
	
	private Map<Integer, GuessGame> serverMap = new HashMap<Integer, GuessGame>();
	private static int index = 0;
	
	public GuessGame getGameById(int id) {
		return serverMap.get(id);
	}
	
	public int createGame(User host) {
		int idServer = ++index;
		GuessGame game = new GuessGame();
		game.addPlayer(new GuessPlayer(host));
		serverMap.put(idServer, game);
		
		log.info(String.format("Created guess-game id:%d creator:%s", idServer, host.toString()));
		return idServer;
	}
	
	public boolean connect(int idServer, User client) {
		GuessGame game = serverMap.get(idServer);
		
		if (game != null) {
			game.addPlayer(new GuessPlayer(client));
			log.info(String.format("Connected to game id:%d client:%s", idServer, client.toString()));
			return true;
		} else {
			log.info(String.format("Server with id:%d doesn't exists", idServer));
			return false;
		}
	}
}
