package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
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
		    + chatParticipant.getNickname());
	    chatManager.getChatRoomById(chatParticipant.getId_room())
		    .addChatParticipant(chatParticipant);
	} else {
	    request.getSession().setAttribute("chatParticipant",
		    chatParticipant);
	}
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("chatRooms");
	return modelAndView;

    }

    public static ChatServiceManager getChatManager() {
	return chatManager;
    }

    protected ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	ChatParticipant chatParticipant = (ChatParticipant) request
		.getSession().getAttribute("chatParticipant");
	return chatParticipant;
    }
}
