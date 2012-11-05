package com.web.app.worldgames.service.interfaces;

import java.util.Map.Entry;
import java.util.Set;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.WebChessGame;

public interface IChessGameService {
	int createGame(User host);
	WebChessGame getGameById(int id);
	boolean removeGameById(int id);
	Set<Entry<Integer, WebChessGame>> getAllGames();
}
