package com.web.app.worldgames.domain.chess;

import java.util.Map;

public interface GameAction {
	void process(ChessGame game, Map<String, Object> json);
}
