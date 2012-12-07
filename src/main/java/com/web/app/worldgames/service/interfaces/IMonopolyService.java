package com.web.app.worldgames.service.interfaces;

import java.util.Map;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public interface IMonopolyService {
	int createGame(User host);
	void removeGameById(int idGame);
	void connect(User client, int idGame);
	MonopolyManager getGameById(int idGame);
	Map<Integer, MonopolyManager> getAllGames();
}
