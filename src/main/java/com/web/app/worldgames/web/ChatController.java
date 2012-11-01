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
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.Room;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chat.ChatRoom;

@Controller
public class ChatController {
    private static final Logger log = Logger.getLogger(ChatController.class);
    private List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	ChatParticipant chatParticipant = new ChatParticipant(user);
	Room room = (Room) request.getSession().getAttribute("room");
	ChatRoom chatRoom = new ChatRoom(room);

	request.getSession().setAttribute("chatParticipant", chatParticipant);
	request.getSession().setAttribute("chatRoom", chatRoom);

	if (chatRoom.isUserInRoom(chatParticipant.getRoomId()))
	    chatRoom.addChatParticipant(chatParticipant);

	chatRooms.add(chatRoom);
	return new ModelAndView("test");
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody
    String onMessage(HttpServletRequest request,
	    @RequestParam("type") String type, @RequestParam("data") String data) {
	ChatParticipant chatParticipant = getChatParticipantFromRequest(request);

	if (type.toLowerCase().trim().equals("update")) {
	    log.debug("Update request from user: " + chatParticipant.getNick());
	    return updateUserMessages(chatParticipant);
	} else if (type.toLowerCase().trim().equals("message")) {
	    log.debug("Broadcast request from user: "
		    + chatParticipant.getNick());
	    broadcast(chatParticipant, data);
	}
	return "";
    }

    private void broadcast(ChatParticipant participant, String data) {
	for (ChatRoom chatRoom : chatRooms) {
	    for (ChatParticipant chatMember : chatRoom.getChatParticipants()) {
		if (chatMember.getParticipantId() != participant
			.getParticipantId()) {
		    chatMember.addMessage(chatRoom.getRoomName()+"/"+participant.getNick() + ":" + data);
		}
	    }
	}
    }

    private ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	return (ChatParticipant) request.getSession().getAttribute(
		"chatParticipant");
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
