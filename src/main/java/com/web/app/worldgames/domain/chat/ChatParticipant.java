package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChatParticipant {
    private int id_participant;
    private String nickname;
    private int id_room;
    private LinkedList<String> deliver = new LinkedList<String>();

    public ChatParticipant(User user) {
	super();
	this.setParticipantId(user.getId());
	this.setNickname(user.getNickname());
	this.id_room = 0;
    }

    public ChatParticipant() {
    }

    public int getParticipantId() {
	return id_participant;
    }

    public void setParticipantId(int id) {
	this.id_participant = id;
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public void addMessage(String message) {
	deliver.add(message);
    }

    public List<String> getMessages() {
	ArrayList<String> messages = new ArrayList<String>(deliver);
	deliver.clear();
	return messages;
    }

    public int getId_room() {
	return id_room;
    }

    public void setId_room(int id_room) {
	this.id_room = id_room;
    }
}
