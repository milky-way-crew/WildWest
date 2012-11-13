package com.web.app.worldgames.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.chat.ChatRoom;

public class ChatServiceManager {
    private static final Logger log = Logger
	    .getLogger(ChatServiceManager.class);
    private static List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

    public ChatServiceManager() {
	chatRooms.add(new ChatRoom("World", 0));
    }

    public List<ChatRoom> getChatRooms() {
	return chatRooms;
    }

    public void setChatRooms(List<ChatRoom> chatRooms) {
	ChatServiceManager.chatRooms = chatRooms;
    }

    public void addChatRoom(String roomName, int id) {
	chatRooms.add(new ChatRoom(roomName, id));
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
		chatRooms.remove(i);
		log.debug("Room " + chatRooms.get(i).getRoomName() + " removed");
	    }
	}
    }

}
