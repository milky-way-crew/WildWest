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

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.chess.Board;
import com.web.app.worldgames.domain.chess.FigureTypesEnum;
import com.web.app.worldgames.domain.chess.GameAction;
import com.web.app.worldgames.domain.chess.Move;
import com.web.app.worldgames.domain.chess.Player;
import com.web.app.worldgames.domain.chess.ResultEnum;
import com.web.app.worldgames.domain.chess.ChessGame;
import com.web.app.worldgames.domain.chess.WebPlayer;

@Controller
public class ChessContoller {
	private static final long GAME_TEST_ID = 1L;

	@Autowired
	private IUserDao userDao;

	private static final Logger log = Logger.getLogger(ChessContoller.class);

	private static ChessGame game = new ChessGame();

	private static Map<Long, ChessGame> serverMap = new HashMap<Long, ChessGame>();

	static {
		serverMap.put(GAME_TEST_ID, game);
	}

	@RequestMapping(value = "/1")
	public String createServer(HttpSession session) {
		log.info("1");
		// Test user
		if (game.getWhite() == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("test"));
			User user = (User) session.getAttribute("user");
			session.setAttribute("idChessGame", GAME_TEST_ID);

			game.setWhite(new WebPlayer(user));
			log.info("WHITE HAS BEEN PLAYER CONNECTED");
			Board board = Board.createInitializedBoard();
			log.info("INITIALIZED :" + board);
			game.setBoard(board);
		} else if (game.getBlack() == null) {
			log.info("**************** Setting doggi user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("doggi"));
			User user = (User) session.getAttribute("user");
			session.setAttribute("idChessGame", GAME_TEST_ID);

			game.setBlack(new WebPlayer(user));
			log.info("BLACK PLAYER SETTED");
			log.fatal("GAME CAN BE STARTED");
			game.startGame();
		} else {
			log.info("NO SPACE");
		}

		return "redirect:/server";
	}

	@RequestMapping(value = "/server")
	public String startServer(HttpSession session) {
		return "chess";

	}

	@RequestMapping(value = "/chess/update")
	public @ResponseBody
	Map<String, ? extends Object> updateHandler(HttpSession session,
			@RequestParam Map<String, ? extends Object> params) {
		Map<String, Object> responseJson = new HashMap<String, Object>();

		// Getting game object from user session
		Long id = (Long) session.getAttribute("idChessGame");
		final ChessGame game = serverMap.get(id);

		// if (game != null && game.isStarted()) {
		// Getting user object from session
		User user = (User) session.getAttribute("user");

		// log.info("INCOMING JSON" + params);
		// log.info("CHESS GAME ID: " + id);
		// log.info("User :" + user);
		

		if (params.containsKey("draw_choice")) {
			onDrawChoice(params, game, user);
		}

		if (params.containsKey("changes")) {
			onChanges(responseJson, game, user);
		}

		if (params.containsKey("whoami")) {
			onWhoamiRecieved(responseJson, game, user);
		}

		if (params.containsKey("init")) {
			onInitRecieved(responseJson, game);
		}

		if (params.containsKey("move") && game.isStarted()) {
			onMoveRecieved(params, responseJson, game);
		}
		return responseJson;

	}

	private void onDrawChoice(Map<String, ? extends Object> params,
			final ChessGame game, User user) {
		if (game.getLastMoveResult() == ResultEnum.DRAW) {
			String choice = (String) params.get("draw_choice");
			// * //
			log.info("USER ID[" + user.getId() + "] CHOISE IS: " + choice); //

			Player player = game.getPlayerById(user.getId());
			FigureTypesEnum figureChoice = FigureTypesEnum.valueOf(choice);

			if (player.getDrawChoice() == null) {
				player.setDrawChoice(figureChoice);
			}

			ResultEnum drawResult;

			if (game.canResolveDraw()) {
				log.info("Game can resolve the draw situation");
				log.info("Game has 2player choices");

				final Move move = game.getLastMove();
				final FigureTypesEnum invokerFigure = game.getNextPlayer()
						.getDrawChoice();
				final FigureTypesEnum defenderFigure = game.getCurrentPlayer()
						.getDrawChoice();

				drawResult = game.resolveDraw();
				game.setLastMoveResult(drawResult);

				// game.resetPlayerChoices();
				game.getWhite().setDrawChoice(null);
				game.getBlack().setDrawChoice(null);

				final ResultEnum result = drawResult;

				GameAction gameAction = new GameAction() {
					@Override
					public void process(ChessGame game, Map<String, Object> json) {
						json.put("enemyMove", move);
						json.put("result", result);
						json.put("invoker", invokerFigure);
						json.put("defender", defenderFigure);
					}
				};

				game.getCurrentPlayer().notifyAbout(gameAction);
				game.getNextPlayer().notifyAbout(gameAction);

				log.info("DRAW INITER WAS: " + game.getNextPlayer().getId());
				log.info("DRAW WAS RESOVLED AS: " + drawResult);
			}
		} else {
			log.error("Player sends draw choice but there is not draw situation");
		}
	}

	private void onChanges(Map<String, Object> responseJson,
			final ChessGame game, User user) {
		boolean isCurrentPlayerMove = false;
		responseJson.put("move", isCurrentPlayerMove);
		Player player = game.getPlayerById(user.getId());
		GameAction action = player.popAction();

		if (action != null) {
			log.info("*** Sending instructions to player via GameAction object. To"
					+ "*** Player id = [" + player.getId() + "]");
			action.process(game, responseJson);
		}

		responseJson.put(
				"move",
				game.getCurrentPlayer().getId() == user.getId()
						&& game.getLastMoveResult() != ResultEnum.DRAW);
	}

	private void onWhoamiRecieved(Map<String, Object> responseJson,
			ChessGame game, User user) {
		log.info("REQUEST TYPE: Who am i");
		responseJson.put("whoami",
				game.getWhite().getId() == user.getId() ? "WHITE" : "BLACK");
	}

	private void onInitRecieved(Map<String, Object> responseJson, ChessGame game) {
		log.info("REQUEST TYPE: init");
		com.web.app.worldgames.domain.chess.Utils.printBoard(game.getBoard());
		responseJson.put("init", game.getBoard().getBoard());
	}

	private void onMoveRecieved(Map<String, ? extends Object> params,
			Map<String, Object> responseJson, ChessGame game) {
		log.info("REQUEST TYPE: Player move");
		String coords = (String) params.get("move");

		game.setCurrentPlayer(game.getNextPlayer());

		final Move move = Move.valueOf(coords);
		final FigureTypesEnum invokerFigure = game.getBoard()
				.getFigure(move.getStart()).getType();
		final FigureTypesEnum defenderFigure = game.getBoard()
				.getFigure(move.getEnd()).getType();

		final ResultEnum result = game.moveTo(move);

		game.setLastMove(move);
		game.setLastMoveResult(result);

		game.getCurrentPlayer().notifyAbout(new GameAction() {
			@Override
			public void process(ChessGame game, Map<String, Object> json) {
				json.put("enemyMove", move);
				json.put("result", result);
				json.put("defender", defenderFigure);
				json.put("invoker", invokerFigure);
			}
		});

		responseJson.put("defender", defenderFigure);
		responseJson.put("invoker", invokerFigure);
		responseJson.put("result", result);

		if (result == ResultEnum.DRAW) {
			log.info("ITS DRAW INVOKED BY USER: "
					+ game.getNextPlayer().getId());
		}
	}
}
