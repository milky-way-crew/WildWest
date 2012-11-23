package com.web.app.worldgames.dao.interfaces;

import java.util.Set;
import java.util.Map.Entry;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.ChessGameManager;
import com.web.app.worldgames.gibbet.GibbetGameManager;

public interface IGibbetService {
	int createGame(User host);
	GibbetGameManager getGameById(int id);
	boolean removeGameById(int id);
	Set<Entry<Integer, GibbetGameManager>> getAllGames();
}
