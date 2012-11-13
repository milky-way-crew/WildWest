package com.web.app.worldgames.domain.chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class ChatRoom {
    private static final Logger log = Logger.getLogger(ChatRoom.class);

    private static List<ChatParticipant> chatParticipants = new ArrayList<ChatParticipant>();
    private int roomId;
    private String roomName;

    public ChatRoom(String roomName, int id) {
	this.roomId = id;
	this.roomName = roomName;
	log.debug("Room "+roomName+" added");
    }

    public int sizeOfRoom(){
	return chatParticipants.size();
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
		chatParticipants.remove(i);
		log.debug("User " + chatParticipants.get(i).getNickname()
			+ " removed from roomChat");
	    }
	}
    }

    public boolean isParticipantInRoom(ChatParticipant chatParticipant) {
	for (ChatParticipant participant : chatParticipants) {
	    if (participant.getParticipantId() == chatParticipant.getParticipantId())
		return true;
	}
	return false;
    }

    public List<ChatParticipant> getChatParticipants() {
	return chatParticipants;
    }

    public static void setChatParticipants(List<ChatParticipant> chatParticipants) {
	ChatRoom.chatParticipants = chatParticipants;
    }

    public long getRoomId() {
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
}
