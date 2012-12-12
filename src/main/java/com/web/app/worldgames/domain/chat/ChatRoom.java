package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ChatRoom {
    private static final Logger log = Logger.getLogger(ChatRoom.class);
    static final String MONOPOLY = "monopoly";
    static final String CHESS = "chess";
    static final String DRAW = "guess";
    static final String GIBBET = "gibbet";

    private List<ChatParticipant> chatParticipants = new ArrayList<ChatParticipant>();
    private int roomId;
    private int gameId;
    private String roomName;
    private int size;
    private String type;
    private int maxSize;
    private boolean roomState;

    public ChatRoom(String roomName, int id, String type) {
	this.roomId = id;
	this.roomName = roomName;
	this.type = type;
	log.debug("Room " + roomName + " added");
    }

    public void addChatParticipant(ChatParticipant participant) {
	chatParticipants.add(participant);
	log.debug("User " + participant.getNickname() + " added to roomChat");
    }

    public ChatParticipant getChatParticipantById(int id) {
	for (ChatParticipant participant : chatParticipants) {
	    if (id == participant.getParticipantId()) {
		log.debug("User " + participant.getNickname()
			+ " founded in roomChat");
		return participant;
	    }
	}
	return null;
    }

    public void deleteChatParticipantById(int id) {
	for (int i = 0; i < chatParticipants.size(); i++) {
	    if (id == chatParticipants.get(i).getParticipantId()) {
		log.debug("User " + chatParticipants.get(i).getNickname()
			+ " deleted from room " + this.roomName);
		chatParticipants.remove(i);
		break;
	    }
	}
    }

    public boolean isParticipantInThisRoom(ChatParticipant chatParticipant) {
	for (ChatParticipant participant : chatParticipants) {
	    if (participant.getParticipantId() == chatParticipant
		    .getParticipantId())
		return true;
	}
	return false;
    }

    public ChatParticipant getRoomCreator(ChatParticipant participant) {
	for (ChatParticipant candidat : chatParticipants) {
	    if (candidat.getStatus().toLowerCase().trim().equals("creator")) {
		return candidat;
	    }
	}
	return participant;
    }

    public List<ChatParticipant> getChatParticipants() {
	return chatParticipants;
    }

    public void setChatParticipants(List<ChatParticipant> chatParticipants) {
	this.chatParticipants = chatParticipants;
    }

    public int getRoomId() {
	return roomId;
    }

    public void setRoomId(int roomId) {
	this.roomId = roomId;
    }

    public String getRoomName() {
	return roomName;
    }

    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }

    public int getSize() {
	this.size = chatParticipants.size();
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getGameId() {
	return gameId;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public int getMaxSize() {
	if (this.getType().toLowerCase().trim().equals(MONOPOLY)) {
	    this.maxSize = 4;
	}
	if (this.getType().toLowerCase().trim().equals(CHESS)) {
	    this.maxSize = 2;
	}
	if (this.getType().toLowerCase().trim().equals(GIBBET)) {
	    this.maxSize = 2;
	}
	if (this.getType().toLowerCase().trim().equals(DRAW)) {
	    this.maxSize = 50;
	}
	return maxSize;
    }

    public void setMaxSize(int maxSize) {
	this.maxSize = maxSize;
    }

    public boolean isRoomState() {
	return roomState;
    }

    public void setRoomState(boolean roomState) {
	this.roomState = roomState;
    }
}
