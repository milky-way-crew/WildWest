package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatServiceManager;

@Controller
public class LogoutUserController {

    private static ChatServiceManager manager = ChatRoomsController
	    .getManager();

    @RequestMapping(value = { "/logout" })
    public String logout(HttpServletRequest request) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	HttpSession session = request.getSession();
	session.removeAttribute("chatParticipant");
	session.removeAttribute("user");
	return "redirect:login";
    }
}
