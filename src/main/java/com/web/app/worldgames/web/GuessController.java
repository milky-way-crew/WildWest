package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.service.ChatRoomServiceManager;
import com.web.app.worldgames.service.GuessGameService;
import com.web.app.worldgames.service.interfaces.IStatisticsServiceManager;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;

@Controller
public class GuessController {
    private static final Logger log = Logger.getLogger(GuessController.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();
    private final String drawGuess = "drow&guess";

    @Autowired
    private IUserServiceManager userService;
    
    @Autowired
    private IStatisticsServiceManager serviceManager;

    @RequestMapping(value = "/guess-game")
    public String showPage() {
	return "guess-game";
    }

    @RequestMapping(value = "/guess-create")
    public String createServer(HttpSession session, HttpServletRequest request) {

    	log.info("Starting creating draw-game");
	User user = getUserFromSession(session);
	int idGame = GuessGameService.INSTANCE.createGame(user);
	ChatParticipant host = ChatRoomsController
		.getChatParticipantFromRequest(request);
	manager.getChatRoomById(host.getId_room()).setGameId(idGame);
	session.setAttribute("idGuessGame", idGame);
	
	serviceManager.incrementUserAllGames(user.getId(), drawGuess);
	log.info("Finished creating chess-game with id: " + idGame);
	return "redirect:/guess-game";
    }

    @RequestMapping(value = "/guess-connect")
    public String connect(@RequestParam int idServer, HttpSession session) {

	User client = getUserFromSession(session);
	boolean connectStatus = GuessGameService.INSTANCE.connectUserTo(idServer,
		client);

	if (connectStatus) {
	    session.setAttribute("idGuessGame", idServer);
	    serviceManager.incrementUserAllGames(client.getId(), drawGuess);
	    return "redirect:/guess-game";
	} else {
	    return "redirect:/404";
	}
    }

    @RequestMapping(value = "/guess-bind")
    public @ResponseBody
    Map<String, ? extends Object> bindWebSocket(HttpSession session) {
	Integer idGame = (Integer) session.getAttribute("idGuessGame");

	HashMap<String, Object> response = new HashMap<String, Object>();
	response.put("dataType", "web-socket-bind");
	response.put("userId", getUserFromSession(session).getId());
	response.put("gameId", idGame);

	return response;
    }

    private User getUserFromSession(HttpSession session) {
	return (User) session.getAttribute("user");
    }
}
