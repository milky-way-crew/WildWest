package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;

public class WebSocketTransport {
	private static WebSocketTransport INSTANCE = null;
	private Map<Integer, MonoWebSocket> socketMap = new HashMap<Integer, MonoWebSocket>();

	public void sendMessage(int idUser,
			Map<String, ? extends Object> message) {
		MonoWebSocket socket = socketMap.get(idUser);
		socket.sendMessage(message);
	}

	public MonoWebSocket put(Integer idUser, MonoWebSocket socket) {
		return socketMap.put(idUser, socket);
	}

	public MonoWebSocket remove(Integer idUser) {
		return socketMap.remove(idUser);
	}
	
	public static WebSocketTransport getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new WebSocketTransport();
		} 
		return INSTANCE;
	}

	private WebSocketTransport() {
	}
}