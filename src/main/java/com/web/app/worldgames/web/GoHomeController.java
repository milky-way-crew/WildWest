package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class GoHomeController {
    private static final Logger log = Logger.getLogger(GoHomeController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    @RequestMapping(value = "/goHome")
    public String goHome(HttpServletRequest request) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	log.debug("User " + participant.getNickname() + " go home");
	return "redirect:/chatRooms";
    }
}
