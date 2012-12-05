package com.web.app.worldgames.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class RoomController {

    static final String CREATOR = "creator";
    static final String READY_STATUS = "ready";
    static final String NOT_READY_STATUS = "";
    private static final Logger log = Logger.getLogger(RoomController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/ajax_room", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	JSONParser parser = new JSONParser();
	JSONObject json = new JSONObject();

	if (type.toLowerCase().trim().equals("lists")) {
	    log.debug("Update lists from user: " + participant.getNickname());
	    json = updateChatRoomPage(participant);
	    return json;
	}
	if (type.toLowerCase().trim().equals("invite")) {
	    json = invite(Integer.parseInt(data), participant);
	    return json;
	}
	if (type.toLowerCase().trim().equals("filteron")) {
	    log.debug("Filter turn On by User: " + participant.getNickname());
	    participant.setChatFilter(true);
	}
	if (type.toLowerCase().trim().equals("filteroff")) {
	    log.debug("Filter turn Off by User: " + participant.getNickname());
	    participant.setChatFilter(false);
	}
	if (type.toLowerCase().trim().equals("create")) {
	    try {
		json = (JSONObject) parser.parse(data);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    log.debug("Create room " + json.get("name") + " by user: "
		    + participant.getNickname());
	    log.debug("Room type is: " + json.get("type"));
	    createRoom(participant, json.get("name").toString(),
		    json.get("type").toString());
	}
	if (type.toLowerCase().trim().equals("join")) {
	    log.debug("User: " + participant.getNickname() + " joined to room");
	    json.put("joinStatus",
		    joinToRoom(participant, Integer.parseInt(data)));
	    return json;
	}
	if (type.toLowerCase().trim().equals("exit")) {
	    log.debug("User: "
		    + participant.getNickname()
		    + " exit from room: "
		    + manager.getChatRoomById(participant.getId_room())
			    .getRoomName());
	    exitFromRoom(participant);
	}
	if (type.toLowerCase().trim().equals("ready")) {
	    log.debug("User: " + participant.getNickname() + " set Status to: "
		    + participant.getStatus());
	    setReadyStatus(participant);
	}
	return null;
    }

    private ChatParticipant getChatParticipantById(int id) {
	for (ChatRoom room : manager.getChatRooms()) {
	    for (ChatParticipant participant : room.getChatParticipants()) {
		if (participant.getParticipantId() == id) {
		    return participant;
		}
	    }
	}
	return null;
    }

    private JSONObject updateChatRoomPage(ChatParticipant participant) {
	JSONObject json = new JSONObject();
	if (participant.getId_room() == manager.getIdWorldRoom()) {
	    json = updateWorldRoomPage(participant);
	} else {
	    json = updateRoomPage(participant);
	}
	return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject updateWorldRoomPage(ChatParticipant participant) {
	JSONObject json = new JSONObject();
	json.put("roomList", updateRoomList(participant));
	if (participant.isInviteState()) {
	    json.put("inviteStatus", participant.isInviteState());
	    log.debug("User inviteState = " + participant.isInviteState());
	    ChatParticipant invitator = getChatParticipantById(participant
		    .getId_invitator());
	    json.put("invitator", invitator.getNickname());
	    log.debug("Invitator = " + invitator.getNickname());
	    json.put("room", manager.getChatRoomById(invitator.getId_room()));
	    log.debug("roomId = "
		    + manager.getChatRoomById(invitator.getId_room())
			    .getRoomId());
	    participant.setInviteState(false);
	}
	return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject updateRoomPage(ChatParticipant participant) {
	JSONObject json = new JSONObject();
	json.put("userList", updateUserList(participant));
	json.put("userRoom", manager.getChatRoomById(participant.getId_room()));
	json.put("creatorRedirect",
		manager.getChatRoomById(participant.getId_room())
			.getRoomCreator(participant).isRedirectState());
	json.put("isCreator", isCreator(participant));
	json.put("filter", participant.isChatFilter());
	json.put("myRedirect", participant.isRedirectState());
	json.put("startStatus", activateStartButton(participant));
	return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject invite(int idUser, ChatParticipant participant) {
	JSONObject json = new JSONObject();
	ChatParticipant invitedParticipant = getChatParticipantById(idUser);
	log.debug("User: " + participant.getNickname() + " invite user - "
		+ invitedParticipant.getNickname());
	if (invitedParticipant.getParticipantId() == participant
		.getParticipantId()) {
	    json.put("inviteError", 1);
	    return json;
	}
	if (invitedParticipant.getId_room() == participant.getId_room()) {
	    json.put("inviteError", 0);
	    return json;
	}
	invitedParticipant.setInviteState(true);
	invitedParticipant.setId_invitator(participant.getParticipantId());
	return json;
    }

    private boolean isCreator(ChatParticipant participant) {
	if (participant.getStatus().toLowerCase().trim().equals("creator")) {
	    return true;
	}
	return false;
    }

    private List<ChatRoom> updateRoomList(ChatParticipant participant) {
	manager.calculateRoomsSize();
	for (ChatRoom chatRoom : manager.getChatRooms()) {
	    if (chatRoom.getSize() == 0
		    && chatRoom.getRoomId() != manager.getIdWorldRoom()) {
		manager.deleteRoomById(chatRoom.getRoomId());
	    }
	}
	return manager.getChatRooms();
    }

    private List<ChatParticipant> updateUserList(ChatParticipant participant) {
	return manager.getChatRoomById(participant.getId_room())
		.getChatParticipants();
    }

    private void exitFromRoom(ChatParticipant participant) {
	if (chooseNewCreator(participant) != null) {
	    chooseNewCreator(participant).setStatus(CREATOR);
	}
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	manager.getChatRoomById(manager.getIdWorldRoom()).addChatParticipant(
		participant);
	participant.setId_room(manager.getIdWorldRoom());
    }

    private ChatParticipant chooseNewCreator(ChatParticipant participant) {
	for (ChatParticipant chatParticipant : manager.getChatRoomById(
		participant.getId_room()).getChatParticipants()) {
	    if (participant.getParticipantId() != chatParticipant
		    .getParticipantId()) {
		return chatParticipant;
	    }
	}
	return null;
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
	boolean flag = true;
	if (manager.getChatRoomById(participant.getId_room()).getSize() > 1) {
	    for (ChatParticipant player : manager.getChatRoomById(
		    participant.getId_room()).getChatParticipants()) {
		if (participant.getParticipantId() != player.getParticipantId()
			&& player.getStatus().toLowerCase().trim()
				.equals(NOT_READY_STATUS)) {
		    flag = false;
		}
	    }
	} else {
	    flag = false;
	}
	return flag;
    }

    private void createRoom(ChatParticipant participant, String roomName,
	    String type) {
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	int lastRoom = manager.getChatRooms().size() - 1;
	int newId = manager.getChatRoomById(lastRoom).getRoomId() + 1;
	manager.addChatRoom(roomName, newId, type);
	participant.setId_room(newId);
	participant.setStatus(CREATOR);
	manager.getChatRoomById(participant.getId_room()).addChatParticipant(
		participant);
    }

    private boolean joinToRoom(ChatParticipant participant, int id) {
	int roomSize = manager.getChatRoomById(id).getSize();
	int maxRoomSize = manager.getChatRoomById(id).getMaxSize();
	log.debug("roomSize = " + roomSize);
	log.debug("roomMaxSize = " + maxRoomSize);
	if (roomSize < maxRoomSize) {
	    manager.getChatRoomById(participant.getId_room())
		    .deleteChatParticipantById(participant.getParticipantId());
	    manager.getChatRoomById(id).addChatParticipant(participant);
	    participant.setId_room(id);
	    participant.setStatus(NOT_READY_STATUS);
	    return true;
	} else {
	    return false;
	}
    }
}
