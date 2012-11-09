package com.web.app.worldgames.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatServiceManager;

@Controller
public class ChatController {
    private static final Logger log = Logger.getLogger(ChatController.class);

    private static ChatServiceManager manager = new ChatServiceManager();

    /*
     * @RequestMapping(method = RequestMethod.GET) public ModelAndView
     * showPage(HttpServletRequest request) { User user = (User)
     * request.getSession().getAttribute("user"); ChatParticipant
     * chatParticipant = new ChatParticipant(user);
     * request.getSession().setAttribute("chatParticipant", chatParticipant);
     * 
     * chatRooms.add(new ChatRoom("", 0));
     * chatRooms.get(0).addChatParticipant(chatParticipant); return new
     * ModelAndView("chatRooms"); }
     */

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody
    String onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant participant = getChatParticipantFromRequest(request);

	/*if (type.toLowerCase().trim().equals("update")) {
	    log.debug("Update request from user: " + participant.getNickname());
	    return updateUserMessages(participant);
	} else*/ if (type.toLowerCase().trim().equals("message")) {
	    log.debug("Broadcast request from user: "
		    + participant.getNickname());
	    broadcast(participant, data);
	}

	return manager.getChatRoomById(participant.getId_room()).getRoomName()
		+ " / " + participant.getNickname();
    }

    private ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	ChatParticipant chatParticipant = (ChatParticipant) request
		.getSession().getAttribute("chatParticipant");
	manager.getChatRoomById(chatParticipant.getId_room())
		.addChatParticipant(chatParticipant);
	return chatParticipant;
    }

    private void broadcast(ChatParticipant participant, String data) {
	for (ChatParticipant chatParticipant : manager.getChatRoomById(
		participant.getParticipantId()).getChatParticipants()) {
	    if (chatParticipant.getParticipantId() != participant
		    .getParticipantId()) {
		chatParticipant.addMessage(manager.getChatRoomById(
			participant.getParticipantId()).getRoomName()
			+ " / " + participant.getNickname() + ":" + data);
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
