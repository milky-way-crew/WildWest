package com.web.app.worldgames.service.interfaces;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public interface IMonopolyService {
	void createGame(User host);
	void removeGameById(int idGame);
	void connect(User client, int idGame);
	Player getPlayerById(int idPlayer);
	MonopolyManager getGameById(int idGame);
}
