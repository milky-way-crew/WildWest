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
	
	public Map<Integer, GuessGame> getAllGames() {
		return serverMap;
	}
	
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
	
	public boolean connectUserTo(int idServer, User client) {
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
	public void tryRemoveGame(int idGame) {
		GuessGame game = serverMap.get(idGame);
		if (game != null) {
			log.info(String.format("Trying to remove game with id:%d.", idGame));
			
			boolean anyActive = false;
			
			for (GuessPlayer player : game.getPlayers()) {
				anyActive |= player.isActive();
				if (anyActive == true) {
					break;
				}
			}
			
			if (anyActive == false) {
				log.info(String.format("Removing game with id:%d. Reason: 0 players in.", idGame));
				serverMap.remove(idGame);
			}
		} else {
			log.info(String.format("Game with id=%d already removed or never existed", idGame));
		}
	}
}
