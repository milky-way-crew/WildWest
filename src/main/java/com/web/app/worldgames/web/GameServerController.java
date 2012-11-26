package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;

@Controller
public class GameServerController {

    static final String MONOPOLY = "monopoly";
    static final String CHESS = "chess";
    static final String DRAW = "drawandguess";
    static final String GIBBET = "gibbet";

    private static final Logger log = Logger
	    .getLogger(GameServerController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    private int createGameAndGetID(String gameType) {
	int id = 0;
	if (gameType.toLowerCase().trim().equals(MONOPOLY)) {
	    id = 1;
	}
	if (gameType.toLowerCase().trim().equals(CHESS)) {
	    id = 2;
	}
	if (gameType.toLowerCase().trim().equals(DRAW)) {
	    id = 3;
	}
	if (gameType.toLowerCase().trim().equals(GIBBET)) {
	    id = 4;
	}
	return id;
    }

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
	    return "to error 404";
	}
	participantHost.setRedirectState(true);
	setRedirectToAllInRoomByID(participantHost.getId_room(), true);
	String gameType = manager.getChatRoomById(participantHost.getId_room())
		.getType();
	int gameId = createGameAndGetID(gameType);
	manager.getChatRoomById(participantHost.getId_room()).setGameId(gameId);
	session.setAttribute(gameType, gameId);
	log.debug("Create game server for game: " + gameType + " by user: "
		+ participantHost.getNickname());
	return "redirect:/" + gameType.toLowerCase();
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connectToServer(HttpSession session,
	    HttpServletRequest request) {
	ChatParticipant participantClient = ChatRoomsController
		.getChatParticipantFromRequest(request);
	log.debug("Connect err");
	int gameId = manager.getChatRoomById(participantClient.getId_room())
		.getGameId();
	String gameType = manager.getChatRoomById(
		participantClient.getId_room()).getType();
	log.debug("User: " + participantClient.getNickname()
		+ " connect to game server");
	/* connecting to game */
	return "redirect:/" + gameType.toLowerCase();
    }
}
