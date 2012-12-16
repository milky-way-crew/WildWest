package com.web.app.worldgames.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;
import com.web.app.worldgames.service.ChatRoomServiceManager;
import com.web.app.worldgames.service.MonopolyService;

@Controller
public class GoHomeController {
    private static final String MONOPOLY = "monopoly";
    private static final String GIBBET = "gibbet";
    private static final Logger log = Logger.getLogger(GoHomeController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();
    private static Map<Integer, MonopolyManager> serverMap = MonopolyService
	    .getServerMap();

    @RequestMapping(value = "/goHome")
    public String goHome(HttpServletRequest request) {
	ChatParticipant participant = ChatRoomsController
		.getChatParticipantFromRequest(request);
	int idServer = manager.getChatRoomById(participant.getId_room())
		.getGameId();
	
	if (manager.getChatRoomById(participant.getId_room()).getType()
		.equals(MONOPOLY)) {
	    serverMap.get(idServer).leaveGame(
		    serverMap.get(idServer).getPlayerById(
			    participant.getParticipantId()));
	}
	
	if (manager.getChatRoomById(participant.getId_room()).getType().equals(GIBBET)){
	    //delete server room
	}
	
	manager.getChatRoomById(participant.getId_room())
		.deleteChatParticipantById(participant.getParticipantId());
	log.debug("User " + participant.getNickname() + " go home");
	return "redirect:/chatRooms";
    }
}
