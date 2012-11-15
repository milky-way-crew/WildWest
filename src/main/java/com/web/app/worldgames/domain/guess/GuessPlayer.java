package com.web.app.worldgames.domain.guess;

import java.util.Map;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.websocket.GuessWebSocketHandler.GuessWebSocket;

public class GuessPlayer {
	private int id;
	private String nick;
	private GuessWebSocket socket;
	
	public GuessPlayer() {
	}

	public GuessPlayer(User user) {
		this.setId(user.getId());
		this.setNick(user.getNickname());
	}
	
	public void sendMessage(Map<String, ? extends Object> message) {
		getSocket().sendMessage(message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public GuessWebSocket getSocket() {
		return socket;
	}

	public void setSocket(GuessWebSocket socket) {
		this.socket = socket;
	}
}
