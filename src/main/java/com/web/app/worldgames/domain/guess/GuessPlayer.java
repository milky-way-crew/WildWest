package com.web.app.worldgames.domain.guess;

import java.util.Map;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.websocket.GuessWebSocketHandler.GuessWebSocket;

public class GuessPlayer {
	private int id;
	private String nick;
	private GuessWebSocket socket;
	private boolean active = false;
	private int winCount = 0;
	
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

	@Override
	public String toString() {
		return "GuessPlayer [id=" + id + ", nick=" + nick + ", socket="
				+ socket + "]";
	}

	public void setActive(boolean b) {
		this.active = b;
	}
	
	public boolean isActive() {
		return active;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	
	public int incWinCount() {
		return ++winCount;
	}
}
