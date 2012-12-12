package com.web.app.worldgames.domain.chat;

import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChatParticipant {
    private int id_participant;
    private int id_room;
    private int id_invitator;
    private User user;
    private String nickname;
    private String status;
    private String sessionID;
    private boolean chatFilter;
    private boolean redirectState;
    private boolean inviteState;
    private LinkedList<String> deliver = new LinkedList<String>();

    public ChatParticipant(User user) {
	super();
	this.setUser(user);
	this.setParticipantId(user.getId());
	this.setNickname(user.getNickname());
	this.id_room = 0;
	this.status = "";
	this.chatFilter = false;
	this.redirectState = false;
	this.inviteState = false;
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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isInviteState() {
	return inviteState;
    }

    public void setInviteState(boolean invite) {
	this.inviteState = invite;
    }

    public int getId_invitator() {
	return id_invitator;
    }

    public void setId_invitator(int id_invitator) {
	this.id_invitator = id_invitator;
    }

    public String getSessionID() {
	return sessionID;
    }

    public void setSessionID(String sessionID) {
	this.sessionID = sessionID;
    }
}
