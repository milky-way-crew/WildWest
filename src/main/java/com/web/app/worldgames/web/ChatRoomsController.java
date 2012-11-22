package com.web.app.worldgames.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private static ChatServiceManager manager = new ChatServiceManager();

    @RequestMapping(value = "/chatRooms", method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	ChatParticipant chatParticipant = new ChatParticipant(user);
	if (!manager.isParticipantInAnyRoom(chatParticipant)) {
	    generateTextColorForParticipant(chatParticipant);
	    request.getSession().setAttribute("chatParticipant",
		    chatParticipant);
	    log.debug("Put ChatParticipant in session and in world particicipant list: "
		    + chatParticipant.getNickname());
	    manager.getChatRoomById(manager.getIdWorldRoom())
		    .addChatParticipant(chatParticipant);
	} else {
	    chatParticipant = getChatParticipantFromRequest(request);
	    log.debug("ChatParticipant get from session "
		    + chatParticipant.getNickname());
	}
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("chatRooms");
	return modelAndView;

    }

    public static ChatServiceManager getManager() {
	return manager;
    }

    private void generateTextColorForParticipant(ChatParticipant participant) {
	List<String> colorList = new ArrayList<String>(Arrays.asList("red",
		"green", "blue", "yellow", "violet", "brown", "black", "azure"));
	Random rand = new Random();
	participant
		.setTextColor(colorList.get(rand.nextInt(colorList.size() - 1)));
    }

    public static ChatParticipant getChatParticipantFromRequest(
	    HttpServletRequest request) {
	ChatParticipant chatParticipant = (ChatParticipant) request
		.getSession().getAttribute("chatParticipant");
	log.debug("getChatParticipant from Request: "
		+ chatParticipant.getNickname());
	return chatParticipant;
    }

}
