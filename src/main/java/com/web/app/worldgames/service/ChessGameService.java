package com.web.app.worldgames.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.ChessGame;
import com.web.app.worldgames.domain.chess.Player;
import com.web.app.worldgames.domain.chess.WebChessGame;
import com.web.app.worldgames.service.interfaces.IChessGameService;

@Service
public class ChessGameService implements IChessGameService {
	private static final Map<Integer, WebChessGame> serverMap = Collections.synchronizedMap(new HashMap<Integer, WebChessGame>());
	private static int counter = 0;
	
	@Override
	public synchronized int createGame(User host) {
		WebChessGame chessGame = new WebChessGame(new ChessGame());
		chessGame.setHost(new Player(host));
		serverMap.put(++counter, chessGame);
		return counter;
	}

	@Override
	public synchronized WebChessGame getGameById(int id) {
		if (serverMap.containsKey(id)) {
			return serverMap.get(id);
		} else {
			return null;
		}
	}

	@Override
	public synchronized boolean removeGameById(int id) {
		if (serverMap.containsKey(id)) {
			serverMap.remove(id);
			// counter--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public synchronized Set<Entry<Integer,WebChessGame>> getAllGames() {
		return serverMap.entrySet();
	}

}
