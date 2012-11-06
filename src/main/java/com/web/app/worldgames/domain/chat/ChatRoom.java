package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.web.ChatController;

public class ChatRoom {
    private static final Logger log = Logger.getLogger(ChatController.class);

    private List<ChatParticipant> chatParticipants = new ArrayList<ChatParticipant>();
    private long roomId;
    private String roomName;

    public ChatRoom(String roomName, long id) {
	this.roomId = id;
	this.roomName = roomName;
    }

    public void addChatParticipant(ChatParticipant participant) {
	chatParticipants.add(participant);
	log.debug("User " + participant.getNickname() + " added to roomChat");
    }

    public ChatParticipant getChatParticipantById(long id) {
	for (ChatParticipant participant : chatParticipants) {
	    if (id == participant.getParticipantId()) {
		log.debug("User " + participant.getNickname()
			+ " founded in roomChat");
		return participant;
	    }
	}
	return null;
    }

    public boolean deleteChatParticipantById(long id) {
	for (int i = 0; i < chatParticipants.size(); i++) {
	    if (id == chatParticipants.get(i).getParticipantId()) {
		chatParticipants.remove(i);
		log.debug("User " + chatParticipants.get(i).getNickname()
			+ " removed from roomChat");
		return true;
	    }
	}
	return false;
    }

    public boolean isParticipantInRoom(long id) {
	for (ChatParticipant participant : chatParticipants) {
	    if (participant.getParticipantId() == id)
		return true;
	}
	return false;
    }

    public List<ChatParticipant> getChatParticipants() {
	return chatParticipants;
    }

    public void setChatParticipants(List<ChatParticipant> chatParticipants) {
	this.chatParticipants = chatParticipants;
    }

    public long getRoomId() {
	return roomId;
    }

    public void setRoomId(long roomId) {
	this.roomId = roomId;
    }

    public String getRoomName() {
	return roomName;
    }

    public void setRoomName(String roomName) {
	this.roomName = roomName;
    }
}
