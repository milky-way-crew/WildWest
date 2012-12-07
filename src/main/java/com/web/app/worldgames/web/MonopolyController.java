package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;
import com.web.app.worldgames.service.ChatRoomServiceManager;
import com.web.app.worldgames.service.MonopolyService;
import com.web.app.worldgames.service.interfaces.IMonopolyService;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;

@Controller
public class MonopolyController {
	private static final String ID_MONO_GAME = "idMonoGame";
	private final static Logger log = Logger
			.getLogger(MonopolyController.class);
	@Autowired
	private IUserServiceManager userService;
	private IMonopolyService monopolyService = MonopolyService.getInstance();
	 private static ChatRoomServiceManager manager = ChatRoomsController
		    .getManager();

	public static Map<Integer, MonoWebSocket> socketMap = new HashMap<Integer, MonoWebSocket>();

	@RequestMapping(value = "/mono-server")
	public String showPage(HttpSession session, Model model) {

		model.addAttribute("user", getUserFromSession(session));
		
		return "monopoly/monopoly-board";
//		return "monopoly/monopoly-game";
	}

	@RequestMapping(value = "/monopoly-create")
	public String createServer(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userService.findUserByLogin("test"));
		}

		log.info("Starting creating monopoly-game");
		User userHost = (User) session.getAttribute("user");
		int gameId = monopolyService.createGame(userHost);
		ChatParticipant host = ChatRoomsController
			.getChatParticipantFromRequest(request);
		manager.getChatRoomById(host.getId_room()).setGameId(gameId);
		session.setAttribute(ID_MONO_GAME, gameId);
		log.info("Finished creating chess-game with id: " + gameId);

		return "redirect:/mono-server";
	}

	@RequestMapping(value = "/monopoly-connect")
	public String onConnectToServer(@RequestParam int idServer,
			HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting doggi user to session ***************");
			session.setAttribute("user", userService.findUserByLogin("doggi"));
		}

		User client = (User) session.getAttribute("user");
		MonopolyManager monopolyGame = monopolyService.getGameById(idServer);

		if (monopolyGame == null || monopolyGame.getCreator() == null) {
			return "redirect:/404";
		} else if (monopolyGame.getCreator().getId() == client.getId()) {
			return "redirect:/mono-server";
		}
		
		log.info("Connecting client to server with id" + idServer);
		session.setAttribute(ID_MONO_GAME, idServer);
		monopolyGame.addClient(client);
		return "redirect:/mono-server";
	}

	@RequestMapping(value = "/mono-ajax")
	public @ResponseBody
	Map<String, Object> makeHandShake(HttpSession session) {
		Integer idGame = (Integer) session.getAttribute(ID_MONO_GAME);
		int idUser = getUserFromSession(session).getId();

		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("idUser", idUser);
		response.put("idGame", idGame);

		log.info("[HandShake request] from user: " + idUser);
		return response;
	}

	private User getUserFromSession(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user;
	}

	@RequestMapping(value = "/mono-view")
	public @ResponseBody MonopolyManager test(@RequestParam int id) {
		return monopolyService.getGameById(id);
	}
	
	@RequestMapping(value = "/mono-clear")
	public void clear() {
//		monopolyService.
	}
}
