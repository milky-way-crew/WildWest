package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class ChatRoomsController {

    private static final Logger log = Logger
	    .getLogger(ChatRoomsController.class);

    private static ChatRoomServiceManager manager = new ChatRoomServiceManager();

    @RequestMapping(value = "/chatRooms")
    public ModelAndView showPage(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	ChatParticipant chatParticipant = new ChatParticipant(user);
	if (!manager.isParticipantInAnyRoom(chatParticipant)) {
	    setChatParticipantToRequest(request, chatParticipant);
	    log.debug("Put ChatParticipant in session and in world particicipant list: "
		    + chatParticipant.getNickname());
	    manager.getChatRoomById(manager.getIdWorldRoom())
		    .addChatParticipant(chatParticipant);
	} else {
	    chatParticipant = manager.getChatParticipantById(user.getId());
	    setChatParticipantToRequest(request, chatParticipant);
	    log.debug("ChatParticipant get from session "
		    + chatParticipant.getNickname());
	}
	chatParticipant.setSessionID(request.getSession().getId());
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("chatRooms");
	return modelAndView;
    }

    public static ChatRoomServiceManager getManager() {
	return manager;
    }

    public static ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	ChatParticipant chatParticipant = (ChatParticipant) request
		.getSession().getAttribute("chatParticipant");
	log.trace("getChatParticipant from Request: "
		+ chatParticipant.getNickname());
	return chatParticipant;
    }
    
    public static void setChatParticipantToRequest(
	    HttpServletRequest request, ChatParticipant participant) {
	request.getSession().setAttribute("chatParticipant", participant);
	log.trace("getChatParticipant from Request: "
		+ participant.getNickname());
    }


}
