package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;

@Controller
public class MonopolyController {

	@Autowired
	private IUserServiceManager userService;
	
	public static Map<Integer, MonoWebSocket> socketMap = new HashMap<Integer, MonoWebSocket>();

	@RequestMapping(value = "/mono")
	public String showPage(HttpSession session) {
		User user = getUserFromSession(session);

		if (user == null) {
			session.setAttribute("user", userService.findUserByLogin("test"));
		}

		return "monopoly/monopoly-game";
	}

	@RequestMapping(value = "/mono-ajax")
	public @ResponseBody Integer getUserId(HttpSession session) {
		return getUserFromSession(session).getId();
	}

	private User getUserFromSession(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user;
	}
}
