package com.web.app.worldgames.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.ChessGame;
import com.web.app.worldgames.domain.chess.ChessPlayer;
import com.web.app.worldgames.domain.chess.ChessGameManager;
import com.web.app.worldgames.service.interfaces.IChessGameService;

@Service
public class ChessGameService implements IChessGameService {
	private static final Logger log = Logger.getLogger(ChessGameService.class);

	private static final Map<Integer, ChessGameManager> serverMap = Collections.synchronizedMap(new HashMap<Integer, ChessGameManager>());
	// private static final <Integer> removedId = new ArrayList<Integer>();
	private static int counter = 0;
	
	@Override
	public synchronized int createGame(User host) {
		ChessGameManager chessGame = new ChessGameManager(new ChessGame());
		chessGame.setHost(new ChessPlayer(host));
		serverMap.put(++counter, chessGame);
		return counter;
	}

	@Override
	public synchronized ChessGameManager getGameById(int id) {
		if (serverMap.containsKey(id)) {
			return serverMap.get(id);
		} else {
			return null;
		}
	}

	@Override
	public synchronized boolean removeGameById(int id) {
		if (serverMap.containsKey(id)) {
			log.info("removing game with id: " + id);
			serverMap.remove(id);
			// counter--;
			return true;
		} else {
			log.info("Cannot found game with such id=" + id);
			return false;
		}
	}

	@Override
	public synchronized Set<Entry<Integer,ChessGameManager>> getAllGames() {
		return serverMap.entrySet();
	}

}
