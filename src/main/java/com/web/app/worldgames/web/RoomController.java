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


@Controller
public class RoomController extends ChatRoomsController {
    private static final Logger log = Logger.getLogger(RoomController.class);

    @RequestMapping(value = "/ajax_room", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = getChatParticipantFromRequest(request);
	JSONObject json = new JSONObject();

	if (type.toLowerCase().trim().equals("lists")) {
	    log.debug("Update lists from user: " + participant.getNickname());
	    if (participant.getId_room() == getChatManager().getIdWorldRoom()) {
		json.put("roomList", updateRoomList(participant));
	    } else {
		json.put("userList", updateUserList(participant));
	    }
	    return json;
	}
	/*if (type.toLowerCase().trim().equals("create")) {
	    json.clear();
	    log.debug("Create room " + data + " by user: "
		    + participant.getNickname());
	    if (data.equals("")) {
		return json;
	    } else {
		createRoom(participant, data);
		json.put(
			"newRoom",
			getChatManager().getChatRoomById(
				participant.getId_room()));
		json.put("roomCreator", participant);
		return json;
	    }
	}
	if (type.toLowerCase().trim().equals("join")) {
	    log.debug("User: " + participant.getNickname() + " joined to room");
	    joinToRoom(participant, Integer.parseInt(data));
	    json.put("roomParticipant", participant);
	    return json;
	}
	*/return json;
    }

    private List<ChatRoom> updateRoomList(ChatParticipant participant) {
	getChatManager().calculateRoomsSize();
	for (ChatRoom chatRoom : getChatManager().getChatRooms()) {
	    if (chatRoom.getSize() == 0
		    && chatRoom.getRoomId() != getChatManager()
			    .getIdWorldRoom()) {
		getChatManager().deleteRoomById(chatRoom.getRoomId());
	    }
	}
	return getChatManager().getChatRooms();
    }

    private List<ChatParticipant> updateUserList(ChatParticipant participant) {
	return getChatManager().getChatRoomById(participant.getId_room())
		.getChatParticipants();
    }

/*    private void createRoom(ChatParticipant participant, String roomName) {
	getChatManager().getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	int lastRoom = getChatManager().getChatRooms().size() - 1;
	int newId = getChatManager().getChatRoomById(lastRoom).getRoomId() + 1;
	getChatManager().addChatRoom(roomName, newId);
	participant.setId_room(newId);
	participant.setStatus("creator");
	getChatManager().getChatRoomById(participant.getId_room())
		.addChatParticipant(participant);
    }

    private void joinToRoom(ChatParticipant participant, int id) {
	getChatManager().getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	getChatManager().getChatRoomById(id).addChatParticipant(participant);
	participant.setId_room(id);
	participant.setStatus("");
    }*/
}
