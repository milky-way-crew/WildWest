package com.web.app.worldgames.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;

public class ChatRoomServiceManager {

    private static final int ID_WORLD_ROOM = 0;
    private static final Logger log = Logger
	    .getLogger(ChatRoomServiceManager.class);
    private static List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

    public ChatRoomServiceManager() {
	chatRooms.add(new ChatRoom("World", ID_WORLD_ROOM, "chat"));
    }

    public List<ChatRoom> getChatRooms() {
	return chatRooms;
    }

    public void setChatRooms(List<ChatRoom> chatRooms) {
	ChatRoomServiceManager.chatRooms = chatRooms;
    }

    public void addChatRoom(String roomName, int id, String type) {
	chatRooms.add(new ChatRoom(roomName, id, type));
	log.debug("ChatRoom " + roomName + " added to room list");
    }

    public ChatRoom getChatRoomById(int id) {
	for (ChatRoom chatRoom : chatRooms) {
	    if (chatRoom.getRoomId() == id)
		return chatRoom;
	}
	log.debug("This room does not exist");
	return null;
    }

    public void deleteRoomById(int id) {
	for (int i = 0; i < chatRooms.size(); i++) {
	    if (id == chatRooms.get(i).getRoomId()) {
		log.debug("Room " + chatRooms.get(i).getRoomName() + " removed");
		chatRooms.remove(i);
		break;
	    }
	}
    }

    public boolean isParticipantInAnyRoom(ChatParticipant participant) {
	boolean flag = false;
	for (ChatRoom room : chatRooms) {
	    if (room.isParticipantInThisRoom(participant)) {
		flag = true;
	    }
	}
	return flag;
    }

    public void calculateRoomsSize() {
	for (ChatRoom room : chatRooms) {
	    room.setSize(room.getChatParticipants().size());
	    log.debug(room.getRoomName() + " Size=" + room.getSize());
	}
    }

    public int getIdWorldRoom() {
	return ID_WORLD_ROOM;
    }
}
