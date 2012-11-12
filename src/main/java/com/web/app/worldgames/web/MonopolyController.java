package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IMonopolyService;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;

@Controller
public class MonopolyController {

	@Autowired
	private IUserServiceManager userService;
	
	@Autowired
	private IMonopolyService monopolyService;
	
	public static Map<Integer, MonoWebSocket> socketMap = new HashMap<Integer, MonoWebSocket>();

	@RequestMapping(value = "/mono")
	public String showPage(HttpSession session) {
		User user = getUserFromSession(session);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// FOR TEST 
		if (user == null) {
			session.setAttribute("user", userService.findUserByLogin("test"));
		}
		session.setAttribute("idMonopolyGame", 1);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		return "monopoly/monopoly-game";
	}

	@RequestMapping(value = "/mono-ajax")
	public @ResponseBody Map<String,Object> getUserId(HttpSession session) {
		Integer idGame = (Integer) session.getAttribute("idMonopolyGame");
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("idUser", getUserFromSession(session).getId());
		map.put("idGame", idGame);
		return map;
	}

	private User getUserFromSession(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user;
	}
}
