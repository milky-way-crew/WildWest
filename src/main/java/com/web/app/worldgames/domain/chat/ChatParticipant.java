package com.web.app.worldgames.domain.chat;

import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChatParticipant {
    private int id_participant;
    private String nickname;
    private int id_room;
    private String status;
    private String textColor;
    private boolean chatFilter;
    private boolean redirectState;
    public LinkedList<String> deliver = new LinkedList<String>();

    public ChatParticipant(User user) {
	super();
	this.setParticipantId(user.getId());
	this.setNickname(user.getNickname());
	this.id_room = 0;
	this.textColor = "black";
	this.status = "";
	this.chatFilter = false;
	this.redirectState = false;
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
	return deliver;
    }

    public int getId_room() {
	return id_room;
    }

    public void setId_room(int id_room) {
	this.id_room = id_room;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getTextColor() {
	return textColor;
    }

    public void setTextColor(String textColor) {
	this.textColor = textColor;
    }

    public boolean isChatFilter() {
	return chatFilter;
    }

    public void setChatFilter(boolean chatFilter) {
	this.chatFilter = chatFilter;
    }

    public boolean isRedirectState() {
	return redirectState;
    }

    public void setRedirectState(boolean redirectState) {
	this.redirectState = redirectState;
    }
}
