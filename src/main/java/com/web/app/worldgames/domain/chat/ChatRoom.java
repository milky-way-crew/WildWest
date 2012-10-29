package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.Room;
import com.web.app.worldgames.web.ChatController;

public class ChatRoom {
    private static final Logger log = Logger.getLogger(ChatController.class);

    private List<ChatParticipant> chatParticipants = new ArrayList<ChatParticipant>();
    private long roomId;
    private String roomName;

    public ChatRoom(Room room) {
	this.roomId = room.getRoomId();
	this.roomName = room.getRoomName();
    }

    public void addChatParticipant(ChatParticipant participant) {
	log.debug("User " + participant.getNick() + " added to roomChat");
	chatParticipants.add(participant);
    }

    public ChatParticipant getChatParticipant(long id) {
	for (ChatParticipant participant : chatParticipants) {
	    if (id == participant.getParticipantId()) {
		log.debug("User " + participant.getNick()
			+ " founded in roomChat");
		return participant;
	    }
	}
	return null;
    }

    public boolean deleteChatParticipant(long id) {
	for (int i = 0; i < chatParticipants.size(); i++) {
	    if (id == chatParticipants.get(i).getParticipantId()) {
		chatParticipants.remove(i);
		log.debug("User " + chatParticipants.get(i).getNick()
			+ " removed from roomChat");
		return true;
	    }
	}
	return false;
    }

    public boolean isUserInRoom(long userRoomId) {
	if (this.roomId == userRoomId)
	    return true;
	else
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
