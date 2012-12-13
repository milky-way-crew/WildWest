package com.web.app.worldgames.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import com.web.app.worldgames.domain.chat.ChatParticipant;
import com.web.app.worldgames.domain.chess.Board;
import com.web.app.worldgames.domain.chess.ChessGameManager;
import com.web.app.worldgames.domain.chess.ChessPlayer;
import com.web.app.worldgames.service.ChatRoomServiceManager;
import com.web.app.worldgames.service.ChessGameService;
import com.web.app.worldgames.service.interfaces.IStatisticsServiceManager;

@Controller
public class ChessContoller {
    private static final Logger log = Logger.getLogger(ChessContoller.class);
    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();
    private final String chess = "chess";

    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private IStatisticsServiceManager serviceManager;
    
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
    public @ResponseBody
    Set<Entry<Integer, ChessGameManager>> allServers() {
	return chessService.getAllGames();
    }

	@RequestMapping(value = "/chess-create")
	public String createServer(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("test"));
		}
		
		log.info("Starting creating chess-game");
		User userHost = (User) session.getAttribute("user");
		int gameId = chessService.createGame(userHost);
		ChatParticipant host = ChatRoomsController.getChatParticipantFromRequest(request);
		manager.getChatRoomById(host.getId_room()).setGameId(gameId);
		session.setAttribute("idChessGame", gameId);
		serviceManager.incrementUserAllGames(userHost.getId(), chess);
		log.info("Finished creating chess-game with id: " + gameId);

		return "redirect:/chess-server";
	}

	@RequestMapping(value = "/chess-connect")
	public String onConnectToServer(@RequestParam int idServer, HttpSession session) {
		
		User client = (User) session.getAttribute("user");
		ChessGameManager chessGame = chessService.getGameById(idServer);

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
			chessGame.setClient(new ChessPlayer(client));
			serviceManager.incrementUserAllGames(client.getId(), chess);
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
	User user = (User) session.getAttribute("user");
	if (user != null) {
	    Integer id = (Integer) session.getAttribute("idChessGame");
	    log.info(String.format(
		    "Exit requested from user-id=%d chess-id=%d", user.getId(),
		    id));
	    ChessGameManager gameManager = chessService.getGameById(id);
	    
	    if (gameManager != null) {
		gameManager.onDisconnect(null, user);
		// session.removeAttribute("idChessGame");
		// chessService.removeGameById(id);
	    }
	}

	return "redirect:/home";
    }

    @RequestMapping(value = "/chess/update")
    public @ResponseBody
    Map<String, ? extends Object> updateHandler(HttpSession session,
	    @RequestParam Map<String, ? extends Object> params) {

	// Getting user object and game id from session
	User user = (User) session.getAttribute("user");
	Integer id = (Integer) session.getAttribute("idChessGame");

	ChessGameManager chessGame = chessService.getGameById(id);

	if (chessGame != null) {
	    return chessGame.onRequestFromUser(params, user);
	} else {
	    return null;
	}
    }
}
