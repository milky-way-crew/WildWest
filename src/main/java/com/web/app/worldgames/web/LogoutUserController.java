package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class LogoutUserController {

    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    @RequestMapping(value = { "/logout" })
    public String logout(HttpServletRequest request) {
    try {
		ChatParticipant participant = ChatRoomsController.getChatParticipantFromRequest(request);
		manager.getChatRoomById(participant.getId_room())
			.deleteChatParticipantById(participant.getParticipantId());
    } catch (Exception e) {
    	// it's ok, user don't entered chat
    }
	HttpSession session = request.getSession();
	session.removeAttribute("chatParticipant");
	session.removeAttribute("user");
	return "redirect:login";
    }
}
