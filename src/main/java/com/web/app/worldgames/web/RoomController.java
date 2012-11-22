package com.web.app.worldgames.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;
import com.web.app.worldgames.service.ChatServiceManager;

@Controller
public class RoomController {

    static final String CREATOR = "creator";
    static final String READY_STATUS = "ready";
    static final String NOT_READY_STATUS = "";
    private static final Logger log = Logger.getLogger(RoomController.class);
    private static ChatServiceManager manager = ChatRoomsController
	    .getManager();

    @RequestMapping(value = "/ajax_room", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	JSONObject json = new JSONObject();

	if (type.toLowerCase().trim().equals("lists")) {
	    log.debug("Update lists from user: " + participant.getNickname());
	    if (participant.getId_room() == manager.getIdWorldRoom()) {
		json.put("roomList", updateRoomList(participant));
	    } else {
		json.put("userList", updateUserList(participant));
		json.put("userRoom", manager.getChatRoomById(participant.getId_room()));
	    }
	    return json;
	}
	if (type.toLowerCase().trim().equals("create")) {
	    log.debug("Create room " + data + " by user: "
		    + participant.getNickname());
	    createRoom(participant, data);
	    json.put("newRoom",
		    manager.getChatRoomById(participant.getId_room()));
	    //json.put("roomCreator", participant);
	    return json;
	}
	if (type.toLowerCase().trim().equals("join")) {
	    log.debug("User: " + participant.getNickname() + " joined to room");
	    joinToRoom(participant, Integer.parseInt(data));
	    json.put("roomParticipant", participant);
	    return json;
	}

	return json;
    }

    private List<ChatRoom> updateRoomList(ChatParticipant participant) {
	ChatRoomsController.getManager().calculateRoomsSize();
	for (ChatRoom chatRoom : ChatRoomsController.getManager()
		.getChatRooms()) {
	    if (chatRoom.getSize() == 0
		    && chatRoom.getRoomId() != ChatRoomsController.getManager()
			    .getIdWorldRoom()) {
		ChatRoomsController.getManager().deleteRoomById(
			chatRoom.getRoomId());
	    }
	}
	return ChatRoomsController.getManager().getChatRooms();
    }

    private List<ChatParticipant> updateUserList(ChatParticipant participant) {
	return ChatRoomsController.getManager()
		.getChatRoomById(participant.getId_room())
		.getChatParticipants();
    }

    private void exitFromRoom(ChatParticipant participant) {
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	manager.getChatRoomById(manager.getIdWorldRoom()).addChatParticipant(
		participant);
	participant.setId_room(manager.getIdWorldRoom());
    }

    private void setReadyStatus(ChatParticipant participant) {
	if (participant.getStatus().toLowerCase().trim()
		.equals(NOT_READY_STATUS)) {
	    participant.setStatus(READY_STATUS);
	} else {
	    participant.setStatus(NOT_READY_STATUS);
	}
    }

    private boolean activateStartButton(ChatParticipant participant) {
	if (participant.getStatus().toLowerCase().trim().equals(CREATOR)) {
	    boolean flag = true;
	    for (ChatParticipant player : manager.getChatRoomById(
		    participant.getId_room()).getChatParticipants()) {
		if (participant.getParticipantId() != player.getParticipantId()
			&& player.getStatus().toLowerCase().trim()
				.equals(NOT_READY_STATUS)) {
		    flag = false;
		}
	    }
	    return flag;
	}
	return false;
    }

    private void createRoom(ChatParticipant participant, String roomName) {
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	int lastRoom = manager.getChatRooms().size() - 1;
	int newId = manager.getChatRoomById(lastRoom).getRoomId() + 1;
	manager.addChatRoom(roomName, newId);
	participant.setId_room(newId);
	participant.setStatus(CREATOR);
	manager.getChatRoomById(participant.getId_room()).addChatParticipant(
		participant);
    }

    private void joinToRoom(ChatParticipant participant, int id) {
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	manager.getChatRoomById(id).addChatParticipant(participant);
	participant.setId_room(id);
	participant.setStatus(NOT_READY_STATUS);
    }
}
