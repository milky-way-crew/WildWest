package com.web.app.worldgames.service.interfaces;

import java.util.Map.Entry;
import java.util.Set;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.ChessGameManager;

public interface IChessGameService {
	int createGame(User host);
	ChessGameManager getGameById(int id);
	boolean removeGameById(int id);
	Set<Entry<Integer, ChessGameManager>> getAllGames();
}
