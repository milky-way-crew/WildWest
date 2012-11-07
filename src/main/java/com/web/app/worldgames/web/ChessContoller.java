package com.web.app.worldgames.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.Board;
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


	@RequestMapping(value = "/chess") 
	public String showChessServers(Model model) {
		model.addAttribute("test", "ChessContoller");
		model.addAttribute("chess", chessService.getAllGames());
		log.info("Showing all chess games to user");
		return "chess/chess-list";
	}


	@RequestMapping(value = "/chess/all")
	public @ResponseBody  Set<Entry<Integer,WebChessGame>> allServers() {
		return chessService.getAllGames();
	}

	@RequestMapping(value = "/board") 
	public @ResponseBody Board getBoard() {

		return chessService.getGameById(1).getGame().getBoard();
	} 

	@RequestMapping(value = "/chess/create")
	public String createServer(HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("test"));
		}

		log.info("Starting creating chess-game");
		User userHost = (User) session.getAttribute("user");
		int gameId = chessService.createGame(userHost);
		session.setAttribute("idChessGame", gameId);
		log.info("Finished creating chess-game with id: " + gameId);

		return "redirect:/chess-server";
	}

	@RequestMapping(value = "/chess/connect")
	public String onConnectToServer(@RequestParam int idServer, HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting doggi user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("doggi"));
		}
		
		User client = (User) session.getAttribute("user");
		WebChessGame chessGame = chessService.getGameById(idServer);
		
		if (chessGame == null || chessGame.getHost() == null) {
			return "redirect:/404";
		} else if (chessGame.getHost().getId() == client.getId()) {
			return "/chess-server";
		}
		// TODO:
		// connect to self
		// connect if other games are running
		// connect if
		
		if (!chessGame.isFull()) {
			log.info("Connecting client to server with id" + idServer);
			session.setAttribute("idChessGame", idServer);
			chessGame.setClient(new Player(client));
			return "redirect:/chess-server";
		} else {
			log.info("Server is full");
			return "redirect:/home";
		}
	}
	

	@RequestMapping(value = "/chess-server")
	public String startServer(HttpSession session, Model model) {
		model.addAttribute("sizeX", Board.BOARD_SIZE_X - 1);
		model.addAttribute("sizeY", Board.BOARD_SIZE_Y - 1);
		return "chess/chess-game";
	}

	@RequestMapping(value = "/chess/exit") 
	public String leaveGame(HttpSession session) {
		log.info("Exit requested");
		Integer id = (Integer) session.getAttribute("idChessGame");

		session.removeAttribute("idChessGame");
		chessService.removeGameById(id);

		return "redirect:/home";
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
