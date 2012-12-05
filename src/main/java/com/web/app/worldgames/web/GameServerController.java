package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class GameServerController {

    private static final Logger log = Logger
	    .getLogger(GameServerController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    private void setRedirectToAllInRoomByID(int id, boolean state) {
	for (ChatParticipant participant : manager.getChatRoomById(id)
		.getChatParticipants()) {
	    participant.setRedirectState(state);
	}
    }

    @RequestMapping(value = "/create")
    public String createServer(HttpSession session, HttpServletRequest request) {
	ChatParticipant participantHost = ChatRoomsController
		.getChatParticipantFromRequest(request);
	if (participantHost == null) {
	    log.debug("User not found in Session");
	    return "redirect:/404";
	}
	participantHost.setRedirectState(true);
	setRedirectToAllInRoomByID(participantHost.getId_room(), true);
	String gameType = manager.getChatRoomById(participantHost.getId_room())
		.getType();
	log.debug("Create game server for game: " + gameType + " by user: "
		+ participantHost.getNickname());
	return "redirect:/" + gameType.toLowerCase() + "/create";
    }

    @RequestMapping(value = "/connect")
    public String connectToServer(HttpSession session,
	    HttpServletRequest request) {
	ChatParticipant participantClient = ChatRoomsController
		.getChatParticipantFromRequest(request);
	if (participantClient == null) {
	    log.debug("User not found in Session");
	    return "redirect:/404";
	}
	String gameType = manager.getChatRoomById(
		participantClient.getId_room()).getType();
	return "redirect:/" + gameType.toLowerCase() + "/connect";
    }
}
