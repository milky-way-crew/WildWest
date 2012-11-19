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

import com.web.app.worldgames.domain.chat.ChatParticipant;

@Controller
public class ChatController extends ChatRoomsController {
    private static final Logger log = Logger.getLogger(ChatController.class);

    @RequestMapping(value = "/ajax_chat", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = getChatParticipantFromRequest(request);
	JSONObject json = new JSONObject();
	
	if (type.toLowerCase().trim().equals("message")) {
	    log.debug("Broadcast request from user: "
		    + participant.getNickname());
	    broadcast(participant, data);
	    json.put("sender", answerOnMessage(participant, ""));
	    json.put("message", data);
	    return json;
	}
	if (type.toLowerCase().trim().equals("update")) {
	    log.debug("Update request from user: " + participant.getNickname());
	    json.put("update", updateUserMessages(participant));
	    return json;
	}
	return json;
    }

    private String answerOnMessage(ChatParticipant participant, String data) {
	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	Date date = new Date();
	StringBuilder sb = new StringBuilder();
	sb.append("[");
	sb.append(dateFormat.format(date));
	sb.append("] ");
	sb.append(getChatManager().getChatRoomById(participant.getId_room())
		.getRoomName());
	sb.append(" / ");
	sb.append(participant.getNickname());
	sb.append(" : ");
	sb.append(data);
	return sb.toString();
    }

    private void broadcast(ChatParticipant participant, String data) {
	if (data != "") {
	    for (ChatParticipant chatParticipant : getChatManager()
		    .getChatRoomById(participant.getId_room())
		    .getChatParticipants()) {
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
