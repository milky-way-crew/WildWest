package com.web.app.worldgames.domain.guess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GuessGame {
	private static final int TIMER_WAIT_SECONDS = 60; // 1-minute

	private static final Logger log = Logger.getLogger(GuessGame.class);

	private static final int LINE_SEGMENT = 0;
	private static final int CHAT_MESSAGE = 1;
	private static final int GAME_LOGIC = 2;

	// Constant for game logic state
	private static final int WAITING_TO_START = 0;
	private static final int GAME_START = 1;
	private static final int GAME_OVER = 2;
	private static final int GAME_RESTART = 3;

	private int currentState = WAITING_TO_START;
	private int turn = 0;
	private String currentAnswer = "apple";
	private Timer timer = new Timer();

	private static List<String> answers;
	private List<GuessPlayer> players = new ArrayList<GuessPlayer>();

	static {
		try {
			log.info(AnswerLoader.class.getClassLoader().getResource("answers"));
			answers = AnswerLoader.loadAnswers();			
		} catch (Exception e) {
			log.warn("Cannot load words from file for Guess game, using custom set", e);
			answers = new ArrayList<String>(Arrays.asList("dog", "cat", "human", "student"));
		}
	}
	

	public void addPlayer(GuessPlayer player) {
		if (this.getPlayerById(player.getId()) == null) {
			getPlayers().add(player);
		}
	}

	public void removePlayer(GuessPlayer player) {
		log.debug("All players: " + players);
		log.debug("Removing player: " + player);
		String message = "Hmmm, " + player.getNick()
				+ " disconnected. Total connection: " + (players.size() - 1);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("dataType", CHAT_MESSAGE);
		data.put("sender", "Server");
		data.put("message", message);
		getPlayers().remove(player);
		this.broadcast(data);
	}

	public void deactivatePlayer(GuessPlayer player) {
		log.debug("All players: " + players);
		log.debug("Deactivating player: " + player);
		String message = "Hmmm, " + player.getNick()
				+ "disconnected. Total connection: " + (players.size() - 1);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("dataType", CHAT_MESSAGE);
		data.put("sender", "Server");
		data.put("message", message);
		player.setActive(false);

		this.broadcast(data);
	}

	public List<GuessPlayer> getPlayers() {
		return players;
	}

	public GuessGame() {
	}

	public void startGame() {
		log.debug("*** Starting guess-game ***");
		log.debug("*** getting next-player ***");
		do {
			turn = getNextPlayer();
		} while (!players.get(turn).isActive());
		log.debug("*** new player is: id=" + players.get(turn).getId());

		log.debug("*** Randomizing answers ***");
		currentAnswer = randomizeAnswer();

		log.debug("*** Sending GAME_START to all players ***");
		HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
		gameLogicData.put("dataType", GAME_LOGIC);
		gameLogicData.put("gameState", GAME_START);
		gameLogicData.put("isPlayerTurn", false);
		this.broadcast(gameLogicData);

		log.debug("*** Sending message to player who currently must draw ***");
		final GuessPlayer playerWhoDraws = players.get(turn);
		log.debug(String.format("*** Choose player with nick:[%s] to turn ***",
				playerWhoDraws.getNick()));

		HashMap<String, Object> gameLogicForDraw = new HashMap<String, Object>();
		log.debug(String.format("*** Sending gameState Obj to [%s] ***",
				playerWhoDraws.getNick()));
		gameLogicForDraw.put("dataType", GAME_LOGIC);
		gameLogicForDraw.put("gameState", GAME_START);
		gameLogicForDraw.put("answer", currentAnswer);
		gameLogicForDraw.put("isPlayerTurn", true);
		playerWhoDraws.sendMessage(gameLogicForDraw);

		log.debug(String.format("*** Starting timer ***"));
		timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				log.debug(String
						.format("*** Timer executes his task after 60s ***"));
				log.debug(String.format("*** No one wins ***"));
				HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
				gameLogicData.put("dataType", GAME_LOGIC);
				gameLogicData.put("gameState", GAME_OVER);
				gameLogicData.put("winner", "No one");
				gameLogicData.put("answer", currentAnswer);
				broadcast(gameLogicData);
				currentState = WAITING_TO_START;
			}
		}, TimeUnit.SECONDS.toMillis(TIMER_WAIT_SECONDS));

		currentState = GAME_START;
	}

	private int getNextPlayer() {
		return (turn + 1) % players.size();
	}

	private String randomizeAnswer() {
		Random random = new Random();
		int answer = random.nextInt(answers.size());
		return answers.get(answer);
	}

	public void onConnect(int idWho) {
		GuessPlayer player = getPlayerById(idWho);
		log.debug("A connection established with player: " + player.getNick());

		String message = "Welcome " + player.getNick() + ". Total connection:"
				+ players.size();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("dataType", CHAT_MESSAGE);
		data.put("sender", "Server");
		data.put("message", message);

		// for stats
		data.put("player", player.getNick());
		data.put("wins", player.getWinCount());
		
		this.broadcast(data);

		// send the game state to all players.
		HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
		gameLogicData.put("dataType", GAME_LOGIC);
		gameLogicData.put("gameState", currentState);
		gameLogicData.put("answer", currentAnswer);
		gameLogicData.put("isPlayerTurn", players.get(turn).getId() == idWho);
		player.sendMessage(gameLogicData);
		
//		this.broadcast(gameLogicData);
		player.setActive(true);

		// start the game if there are 2 or more connections
		// and game-state == WAITING_TO_START
		// if (canBeStarted()) {
		// startGame();
		// }
	}

	private boolean canBeStarted() {
		int activeCount = 0;
		for (GuessPlayer player: players) {
			if (player.isActive()) {
				activeCount++;
			}
		}
		return currentState == WAITING_TO_START && players.size() >= 2 && activeCount > 1;
	}

	public void onMessage(int type, String message)
			throws JsonProcessingException, IOException {
		ObjectMapper jackson = new ObjectMapper();
		JsonNode messageTree = jackson.readTree(message);
		int idUser = messageTree.path("userId").asInt();

		if (type == LINE_SEGMENT) {
			broadcast(message);
		} else if (type == CHAT_MESSAGE) {
			log.debug("[CHAT-MESSAGE] from connection: " + idUser);
			HashMap<String, Object> map = new HashMap<String, Object>();
			String chatMessage = messageTree.path("message").getTextValue();

			map.put("dataType", CHAT_MESSAGE);
			map.put("sender", getPlayerById(idUser).getNick());
			map.put("message", chatMessage);

			broadcast(map);
		} else if (type == GAME_LOGIC
				&& messageTree.path("gameState").asInt() == GAME_RESTART) {
			log.info(String.format("Player id=[%d] want to restart game",
					idUser));
			if (canBeStarted()) {
				log.info(String.format("Starting new game", idUser));
				startGame();
			}
		} else {
			log.info(String.format("Uknown requested type of [%d]", type));
		}

		if (type == CHAT_MESSAGE) {
			if (currentState == GAME_START
					&& isTrueAnswer(messageTree.path("message").asText())
					&& idUser != players.get(turn).getId()) {
				log.debug(String.format("*** WE HAVE A WINNER ***"));
				log.debug(String.format("*** Canceling timer ***"));

				timer.cancel();

				GuessPlayer winner = getPlayerById(idUser);

				HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
				gameLogicData.put("dataType", GAME_LOGIC);
				gameLogicData.put("gameState", GAME_OVER);
				gameLogicData.put("winner", winner.getNick());
				gameLogicData.put("wins", winner.incWinCount());
				gameLogicData.put("answer", currentAnswer);
				broadcast(gameLogicData);

				currentState = WAITING_TO_START;
			}
		}

	}

	private boolean isTrueAnswer(String string) {
		String[] strings = string.split(" ");
		for (String maybeTrueAnswer : strings) {
			if (currentAnswer.startsWith(maybeTrueAnswer.trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private void broadcast(Map<String, ? extends Object> message) {
		for (GuessPlayer player : players) {
			if (player.isActive()) {
				player.sendMessage(message);
			}
		}
	}

	private void broadcast(String message) {
		for (GuessPlayer player : players) {
			if (player.isActive()) {
				player.getSocket().sendMessage(message);
			}
		}
	}

	public GuessPlayer getPlayerById(int id) {
		for (GuessPlayer player : players) {
			if (player.getId() == id) {
				return player;
			}
		}
		return null;
	}
}
