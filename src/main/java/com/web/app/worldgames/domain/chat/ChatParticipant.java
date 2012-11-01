package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChatParticipant {
    private long participantId;
    private long roomId;
    private String nick;
    private User user;
    private LinkedList<String> deliver = new LinkedList<String>();

    public ChatParticipant(User user) {
	super();
	this.setParticipantId(user.getId());
	this.setNick(user.getNickname());
	this.setUser(user);
    }

    public ChatParticipant() {
    }

    public long getParticipantId() {
	return participantId;
    }

    public void setParticipantId(long id) {
	this.participantId = id;
    }

    public String getNick() {
	return nick;
    }

    public void setNick(String nick) {
	this.nick = nick;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public void addMessage(String message) {
	deliver.add(message);
    }

    public List<String> getMessages() {
	ArrayList<String> messages = new ArrayList<String>(deliver);
	deliver.clear();
	return messages;
    }

    public long getRoomId() {
	return roomId;
    }

    public void setRoomId(long roomId) {
	this.roomId = roomId;
    }
}
