package com.web.app.worldgames.gibbet;

import java.util.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.web.app.worldgames.dao.interfaces.IGibbetService;
import com.web.app.worldgames.domain.User;

@Service
public class GibbetGameService implements IGibbetService{
	private static final Logger log = Logger.getLogger(GibbetGameService.class);

	private static final Map<Integer, GibbetGameManager> serverMap = Collections.synchronizedMap(new HashMap<Integer, GibbetGameManager>());
	private static int counter = 0;
	
	@Override
	public synchronized int createGame(User host) {
		GibbetGameManager gibbetGame = new GibbetGameManager(new GibbetGame());
		gibbetGame.setHost(new GibbetPlayer(host));
		serverMap.put(++counter, gibbetGame);
		return counter;
	}

	@Override
	public synchronized GibbetGameManager getGameById(int id) {
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
	public synchronized Set<Entry<Integer,GibbetGameManager>> getAllGames() {
		return serverMap.entrySet();
	}
}
