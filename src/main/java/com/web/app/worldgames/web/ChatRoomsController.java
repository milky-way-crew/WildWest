package com.web.app.worldgames.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;
import com.web.app.worldgames.service.ChatServiceManager;

@Controller
public class ChatRoomsController {

    private static final Logger log = Logger
	    .getLogger(ChatRoomsController.class);

    private static ChatServiceManager chatManager = new ChatServiceManager();

    @RequestMapping(value = "/chatRooms", method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	ChatParticipant chatParticipant = new ChatParticipant(user);
	if (!chatManager.getChatRoomById(chatParticipant.getId_room())
		.isParticipantInRoom(chatParticipant)) {
	    request.getSession().setAttribute("chatParticipant",
		    chatParticipant);
	    log.debug("Put ChatParticipant in Session "
		    + chatParticipant.getNickname()
		    + chatParticipant.getParticipantId()
		    + chatParticipant.getId_room());
	    chatManager.getChatRoomById(chatParticipant.getId_room())
		    .addChatParticipant(chatParticipant);
	}
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("chatRooms");
	return modelAndView;
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = getChatParticipantFromRequest(request);
	JSONObject json = new JSONObject();

	if (type.toLowerCase().trim().equals("message")) {
	    json.clear();
	    log.debug("Broadcast request from user: "
		    + participant.getNickname());
	    broadcast(participant, data);
	    json.put("message", answerOnMessage(participant, data));
	    return json;
	}
	if (type.toLowerCase().trim().equals("update")) {
	    json.clear();
	    log.debug("Update request from user: " + participant.getNickname());
	    json.put("update", updateUserMessages(participant));
	    if (participant.getId_room() == 0)
		json.put("roomList", updateRoomList(participant));
	    else
		json.put("userList", updateUserList(participant));
	    return json;
	}
	/*
	 * if (type.toLowerCase().trim().equals("create")) {
	 * log.debug("Create room by user: " + participant.getNickname());
	 * createRoom(participant, data); return data; } if
	 * (type.toLowerCase().trim().equals("join")) {
	 * log.debug("Create room by user: " + participant.getNickname());
	 * joinToRoom(participant, Integer.parseInt(data)); return data; }
	 */
	return null;
    }

    private List<ChatRoom> updateRoomList(ChatParticipant participant) {
	chatManager.calculateRoomsSize();
	List<ChatRoom> listRoom = chatManager.getChatRooms();
	return listRoom;
    }

    private List<ChatParticipant> updateUserList(ChatParticipant participant) {
	return chatManager.getChatRoomById(participant.getId_room())
		.getChatParticipants();
    }

    private void createRoom(ChatParticipant participant, String roomName) {
	chatManager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	int newId = chatManager.getChatRooms().size() + 1;
	chatManager.addChatRoom(roomName, newId);
	participant.setId_room(newId);
	chatManager.getChatRoomById(participant.getId_room())
		.addChatParticipant(participant);
    }

    private void joinToRoom(ChatParticipant participant, int id) {
	chatManager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	chatManager.getChatRoomById(id).addChatParticipant(participant);
	participant.setId_room(id);
    }

    private String answerOnMessage(ChatParticipant participant, String data) {
	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	Date date = new Date();
	StringBuilder sb = new StringBuilder();
	sb.append("[");
	sb.append(dateFormat.format(date));
	sb.append("] ");
	sb.append(chatManager.getChatRoomById(participant.getId_room())
		.getRoomName());
	sb.append(" / ");
	sb.append(participant.getNickname());
	sb.append(" : ");
	sb.append(data);
	return sb.toString();
    }

    private ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	ChatParticipant chatParticipant = (ChatParticipant) request
		.getSession().getAttribute("chatParticipant");
	return chatParticipant;
    }

    private void broadcast(ChatParticipant participant, String data) {
	if (chatManager.getChatRoomById(participant.getId_room())
		.getChatParticipants().size() > 1) {
	    for (ChatParticipant chatParticipant : chatManager.getChatRoomById(
		    participant.getId_room()).getChatParticipants()) {
		if (chatParticipant.getParticipantId() != participant
			.getParticipantId()) {
		    chatParticipant.addMessage(answerOnMessage(participant,
			    data));
		}
	    }
	}
    }

    private String updateUserMessages(ChatParticipant participant) {
	List<String> messages = participant.getMessages();
	return join(messages, "\n");
    }

    private String join(List<String> messages, String delimiter) {
	if (messages.size() < 1) {
	    return "";
	}
	StringBuilder sb = new StringBuilder();
	for (String message : messages) {
	    sb.append(message);
	    sb.append(delimiter);
	}
	return sb.toString();
    }
}
