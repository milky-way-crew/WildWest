package com.web.app.worldgames.domain.monopoly.game;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.Player;

public interface GameManager {
	Map<String, ? extends Object> sendMessageToPlayer(
			Map<String, ? extends Object> messageObject, Player player);

	Map<String, ? extends Object> sendMessageFromPlayer(
			Map<String, ? extends Object> messageObject, Player player);
}
