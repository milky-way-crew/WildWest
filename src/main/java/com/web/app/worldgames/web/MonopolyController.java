package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.MonopolyService;
import com.web.app.worldgames.service.interfaces.IMonopolyService;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;
import org.apache.log4j.Logger;


@Controller
public class MonopolyController {
	private final static Logger log = Logger.getLogger(MonopolyController.class);
	@Autowired
	private IUserServiceManager userService;
	private IMonopolyService monopolyService = MonopolyService.getInstance();
	
	public static Map<Integer, MonoWebSocket> socketMap = new HashMap<Integer, MonoWebSocket>();

	@RequestMapping(value = "/mono")
	public String showPage(HttpSession session) {
		User user = getUserFromSession(session);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// FOR TEST 
		if (user == null) {
			log.info("user is null -> creating new game");
			User host = userService.findUserByLogin("test");
			session.setAttribute("user", host);
			monopolyService.createGame(host);
		}
		session.setAttribute("idMonopolyGame", 0);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		return "monopoly/monopoly-game";
	}

	@RequestMapping(value = "/mono-ajax")
	public @ResponseBody Map<String,Object> makeHandShake(HttpSession session) {
		Integer idGame = (Integer) session.getAttribute("idMonopolyGame");
		int idUser = getUserFromSession(session).getId();

		HashMap<String,Object> response = new HashMap<String, Object>();
		response.put("idUser", idUser);
		response.put("idGame", idGame);

		log.info("[HandShake request] from user: " + idUser);
		return response;
	}

	private User getUserFromSession(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user;
	}
}
