package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.GuessGameService;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;

@Controller
public class GuessController {
	private static final Logger log = Logger.getLogger(GuessController.class);
	
	@Autowired
	private IUserServiceManager userService;

	@RequestMapping(value = "/guess-game")
	public String showPage() {
		return "guess-game";
	}
	
	@RequestMapping (value = "/guess-create")
	public String createServer(HttpSession session) {
		if (getUserFromSession(session) == null) {
			log.info("*** setting doggi user to session");
			session.setAttribute("user", userService.findUserByLogin("doggi"));
		}
		
		User user = getUserFromSession(session);
		int idGame = GuessGameService.INSTANCE.createGame(user);
		session.setAttribute("idGuessGame", idGame);
		
		return "redirect:/guess-game";
	}

	@RequestMapping(value = "/guess-connect")
	public String connect(@RequestParam int id, HttpSession session) {
		if (getUserFromSession(session) == null) {
			log.info("*** setting test user to session");
			session.setAttribute("user", userService.findUserByLogin("test"));
		}
		User client = getUserFromSession(session);
		boolean connectStatus = GuessGameService.INSTANCE.connectUserTo(id, client);
		
		if (connectStatus) {
			session.setAttribute("idGuessGame", id);
			return "redirect:/guess-game";
		} else {
			return "redirect:/404";
		}
	}
	
	@RequestMapping(value = "/guess-bind")
	public @ResponseBody Map<String, ? extends Object> bindWebSocket(HttpSession session) {
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
