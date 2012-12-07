package com.web.app.worldgames.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class ChatController {

    static final String DATE_FORMAT = "HH:mm";
    private static final Logger log = Logger.getLogger(ChatController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/ajax_chat", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	JSONObject json = new JSONObject();

	if (type.toLowerCase().trim().equals("update")) {
	    log.debug("Update messages from user: " + participant.getNickname());
	    json.put("update", updateUserMessages(participant));
	    return json;
	} else {
	    log.debug("Broadcast request from user: "
		    + participant.getNickname() + " and message: " + data);
	    broadcast(participant, data);
	    json.put("message", answerOnMessage(participant, data));
	    return json;
	}
    }

    private String answerOnMessage(ChatParticipant participant, String data) {
	DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	Date date = new Date();
	StringBuilder sb = new StringBuilder();
	sb.append("<p id=");
	sb.append(participant.getParticipantId());
	sb.append(" name='");
	sb.append(participant.getNickname());
	sb.append("' >[");
	sb.append(dateFormat.format(date));
	sb.append("] ");
	sb.append(manager.getChatRoomById(participant.getId_room())
		.getRoomName());
	sb.append(" / ");
	sb.append("<a href='#' rel='tooltip' title='Click here to invite this User'>");
	sb.append(participant.getNickname());
	sb.append("</a>");
	sb.append(" : ");
	sb.append(data);
	sb.append("</p>");
	return sb.toString();
    }

    private void broadcast(ChatParticipant participant, String data) {
	if (participant.getId_room() == manager.getIdWorldRoom()) {
	    broadcastFromWorldRoom(participant, data);
	} else {
	    broadcastInRoom(participant, data);
	}
    }

    private void broadcastFromWorldRoom(ChatParticipant participant, String data) {
	for (ChatRoom room : manager.getChatRooms()) {
	    for (ChatParticipant chatParticipant : room.getChatParticipants()) {
		if ((chatParticipant.getParticipantId() != participant
			.getParticipantId())
			&& (chatParticipant.isChatFilter() == false)) {
		    chatParticipant.addMessage(answerOnMessage(participant,
			    data));
		}
	    }
	}
    }

    private void broadcastInRoom(ChatParticipant participant, String data) {
	for (ChatParticipant chatParticipant : manager.getChatRoomById(
		participant.getId_room()).getChatParticipants()) {
	    if (chatParticipant.getParticipantId() != participant
		    .getParticipantId()) {
		chatParticipant.addMessage(answerOnMessage(participant, data));
	    }
	}
    }

    private String updateUserMessages(ChatParticipant participant) {
	List<String> messages = new ArrayList<String>(participant.getMessages());
	participant.getMessages().clear();
	log.debug("Updating message: " + messages.toString());
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
