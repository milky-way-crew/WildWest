package com.web.app.worldgames.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.Player;
import com.web.app.worldgames.domain.chess.WebChessGame;
import com.web.app.worldgames.service.ChessGameService;

@Controller
public class ChessContoller {
	private static final Logger log = Logger.getLogger(ChessContoller.class);

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ChessGameService chessService;

	@RequestMapping(value = "/chess/all")
	public @ResponseBody  Set<Entry<Integer,WebChessGame>> allServers() {
		return chessService.getAllGames();
	}
	@RequestMapping(value = "/chess/create")
	public String createServer(HttpSession session) {
		log.info("1");
		
		
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("test"));
		}

		log.info("Starting creating chess-game");
		User userHost = (User) session.getAttribute("user");
		int gameId = chessService.createGame(userHost);
		session.setAttribute("idChessGame", gameId);
		log.info("Finished creating chess-game with id: " + gameId);

		return "redirect:/server";
	}

	@RequestMapping(value = "/chess/connect")
	public String onConnectToServer(@RequestParam int idServer, HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting doggi user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("doggi"));
		}
		
		User client = (User) session.getAttribute("user");
		WebChessGame chessGame = chessService.getGameById(idServer);
		
		if (!chessGame.isFull()) {
			log.info("Connecting client to server with id" + idServer);
			session.setAttribute("idChessGame", idServer);
			chessGame.setClient(new Player(client));
			return "redirect:/server";
		} else {
			log.info("Server is full");
			return "redirect:/home";
		}
		
	}
	

	@RequestMapping(value = "/server")
	public String startServer(HttpSession session) {
		return "chess";

	}

	@RequestMapping(value = "/chess/update")
	public @ResponseBody
	Map<String, ? extends Object> updateHandler(HttpSession session,
			@RequestParam Map<String, ? extends Object> params) {

		// Getting user object and game id from session
		User user = (User) session.getAttribute("user");
		Integer id = (Integer) session.getAttribute("idChessGame");
		
		WebChessGame chessGame = chessService.getGameById(id);
		
		if (chessGame != null) {
			return chessGame.onRequestFromUser(params, user);
		} else {
			return null;
		}
		
	}
}
