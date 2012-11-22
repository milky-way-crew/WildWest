package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IChessGameService;

public class ChessGameManager {

	private static final Logger log = Logger.getLogger(ChessGameManager.class);
	private Timer timer;

	private ChessGame game;

	public void setGame(ChessGame game) {
		this.game = game;
	}

	public ChessGame getGame() {
		return game;
	}

	public ChessGameManager(ChessGame chessGame) {
		this.game = chessGame;
	}

	public void setHost(ChessPlayer user) {
		if (game != null && user != null) {
			log.info("Setting host: " + user);
			game.setWhite(user);
			log.info("Setting game board");
			game.setBoard(Board.createInitializedBoard());
		} else {
			throw new NullPointerException("User object is " + user
					+ " Game object is: " + game);
		}
	}

	public ChessPlayer getHost() {
		return game.getWhite();
	}

	public ChessPlayer getClient() {
		return game.getBlack();
	}

	public void setClient(ChessPlayer client) {
		log.info("Setting client" + client);
		game.setBlack(client);
		if (game.canBeStarted()) {
			game.startGame();
		}
	}

	public Map<String, ? extends Object> onRequestFromUser(
			Map<String, ? extends Object> params, User user) {

		Map<String, Object> responseJson = new HashMap<String, Object>();

		if (params.containsKey("is-started")) {
			responseJson.put("started", game.isStarted());
		}

		if (params.containsKey("whoami")) {
			onWhoamiRecieved(responseJson, user);
		}

		if (params.containsKey("init")) {
			onInitRecieved(responseJson);
		}

		if (params.containsKey("shuffle") && !game.isStarted()) {
			onShuffleReceived(responseJson, user);
		}

		if (params.containsKey("ready")) {
			onReadyReceived(responseJson, user);
		}

		if (params.containsKey("chat")) {
			onChatReceived(responseJson, user, params.get("chat"));
		}

		// TESTTTT
		// TODO FIXME
		if (params.containsKey("changes")) {
			onChanges(responseJson, user);
		}

		if (game.isStarted() && !game.isEnded()) {
			if (params.containsKey("draw_choice")) {
				onDrawChoice(params, user);
			}
			if (params.containsKey("move")) {
				onMoveRecieved(params, responseJson);
			}
			if (params.containsKey("disco")) {
				onDisconnect(params, user);
			}
		}
		return responseJson;
	}

	public void onDisconnect(Map<String, ? extends Object> params, final User user) {
		ChessPlayer chessPlayer = game.getPlayerById(user.getId());
		chessPlayer.setActive(false);
		// Notify opponent about disconnect
//		game.getOpponentOf(chessPlayer).getMail().add("Your opponent disconnected");
		log.info("Player id=%d disconnected setting active=" + chessPlayer.isActive());
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Autowired
			private IChessGameService chessService;
			
			@Override
			public void run() {
				if (!game.getPlayerById(user.getId()).isActive()) {
					log.info("Plyer disconnected, trying to remove game");
					chessService.tryRemoveGame(game);
					game.getOpponentOf(game.getPlayerById(user.getId())).notifyAbout(new GameAction() {
						@Override
						public void process(ChessGame game, Map<String, Object> json) {
							json.put("result", BattleResultsEnum.ABSOLUTE_WIN);
						}
					});
				} else {
					log.debug("player is active now, so do nothing.");
					this.cancel();
				}
			}
		}, TimeUnit.SECONDS.toMillis(10));
		
	}

	private void onChatReceived(Map<String, Object> responseJson, User user,
			Object message) {
		log.info("Received chat message from id=" + user.getId());
		ChessPlayer opponent = game.getOpponentOf(game.getPlayerById(user.getId()));
		opponent.getMail().add(game.getPlayerById(user.getId()).getNick() + " : " + message);
	}

	private void onReadyReceived(Map<String, Object> responseJson, User user) {
		ChessPlayer player = game.getPlayerById(user.getId());
		player.setReady(true);
		if (game.canBeStarted()) {
			game.startGame();
		}
	}

	private void onShuffleReceived(Map<String, Object> responseJson, User user) {
		log.debug("[Shuffle request] From user: " + user.getId() + ":"
				+ user.getNickname());
		log.debug("Game isStarted: " + game.isStarted());

		if (!game.isStarted()) {
			log.debug("So, game is started, radomizing figures");
			BoardRandomizer randomizer = new BoardRandomizer();
			Board board = game.getBoard();
			if (log.isDebugEnabled()) {
				log.debug("Board before randomizing");
				Utils.printBoard(board);
			}

			ChessPlayer player = game.getPlayerById(user.getId());
			if (player != null) {
				randomizer.randomizeArea(board, player.getType());
			} else {
				log.error("User with id:" + user.getId()
						+ " isn't player of game");
			}

			// randomizer.randomizeArea(board, game.getWhite().getId() ==
			// user.getId() ? PlayerType.WHITE
			// : PlayerType.BLACK);

			if (log.isDebugEnabled()) {
				log.debug("Board after randomizing");
				Utils.printBoard(board);
			}
			responseJson.put("shuffle", board);
		} else {
			responseJson.put("shuffle", null);
		}

	}

	private void onDrawChoice(Map<String, ? extends Object> params, User user) {
		if (game.getLastMoveResult() == BattleResultsEnum.DRAW) {
			String choice = (String) params.get("draw_choice");
			// * //
			log.info("USER ID[" + user.getId() + "] CHOISE IS: " + choice); //

			ChessPlayer player = game.getPlayerById(user.getId());
			FigureTypesEnum figureChoice = FigureTypesEnum.valueOf(choice);

			if (player.getDrawChoice() == null) {
				player.setDrawChoice(figureChoice);
			}

			BattleResultsEnum drawResult;

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

				game.resetPlayerChoices();

				final BattleResultsEnum result = drawResult;

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

	private void onChanges(Map<String, Object> responseJson, User user) {
		ChessPlayer player = game.getPlayerById(user.getId());
		putMail(responseJson, player);

		if (game.isStarted()) {
			boolean isCurrentPlayerMove = false;
			responseJson.put("move", isCurrentPlayerMove);
			GameAction action = player.popAction();

			if (action != null) {
				log.info("*** Sending instructions to player via GameAction object. To"
						+ "*** Player id = [" + player.getId() + "]");
				action.process(game, responseJson);
			}

			responseJson
					.put("move",
							game.getCurrentPlayer().getId() == user.getId()
									&& game.getLastMoveResult() != BattleResultsEnum.DRAW);
		}
	}

	private void putMail(Map<String, Object> responseJson, ChessPlayer player) {
		if (player.getMail().size() > 0) {
			log.info("Sending chat message to id=" + player.getId());
			responseJson.put("mail", new ArrayList<String>(player.getMail()));
			player.getMail().clear();
		}
	}

	private void onWhoamiRecieved(Map<String, Object> responseJson, User user) {
		log.info("REQUEST TYPE: Who am i");
		ChessPlayer chessPlayer = game.getPlayerById(user.getId());
		chessPlayer.setActive(true);
		responseJson.put("whoami", chessPlayer.getType());
		ChessPlayer player = game.getOpponentOf(game.getPlayerById(user.getId()));
		if (player != null) {
			player.getMail().add("Server: " + user.getNickname() + " connected.");
		}
	}

	private void onInitRecieved(Map<String, Object> responseJson) {
		log.info("REQUEST TYPE: init");
		com.web.app.worldgames.domain.chess.Utils.printBoard(game.getBoard());
		responseJson.put("init", game.getBoard().getBoard());
	}

	private void onMoveRecieved(Map<String, ? extends Object> params,
			Map<String, Object> responseJson) {
		log.info("REQUEST TYPE: Player move");
		String coords = (String) params.get("move");

		game.setCurrentPlayer(game.getNextPlayer());

		final Move move = Move.valueOf(coords);
		final FigureTypesEnum invokerFigure = game.getBoard()
				.getFigure(move.getStart()).getType();
		final FigureTypesEnum defenderFigure = game.getBoard()
				.getFigure(move.getEnd()).getType();

		final BattleResultsEnum result = game.moveTo(move);

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

		if (result == BattleResultsEnum.DRAW) {
			log.info("ITS DRAW INVOKED BY USER: "
					+ game.getNextPlayer().getId());
		}
	}

	public boolean isFull() {
		return game.getWhite() != null && game.getBlack() != null;
	}

	public boolean isStarted() {
		return game.isStarted();
	}
}
