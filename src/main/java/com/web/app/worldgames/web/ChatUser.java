package com.web.app.worldgames.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChatUser {
	private long id;
	private String nick;
	private User user;
	private LinkedList<String> deliver = new LinkedList<String>(); 
	
	
	public ChatUser(User user) {
		super();
		this.setId(user.getId());
		this.setNick(user.getNickname());
		this.setUser(user);
	}

	public ChatUser() {
	}

	public void addMessage(String message) {
		deliver.add(message);
	}
	
	public List<String> getMessages() {
		ArrayList<String> messages = new ArrayList<String>(deliver);
		deliver.clear();
		return messages;
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
}
