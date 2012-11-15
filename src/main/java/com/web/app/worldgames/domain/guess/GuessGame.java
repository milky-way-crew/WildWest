package com.web.app.worldgames.domain.guess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GuessGame {
	private static final int TIMER_WAIT_SECONDS = 10;

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
	
	private List<GuessPlayer> players = new ArrayList<GuessPlayer>();

	public void addPlayer(GuessPlayer player) {
		getPlayers().add(player);
	}

	public void removePlayer(GuessPlayer player) {
		getPlayers().remove(player);
	}

	public List<GuessPlayer> getPlayers() {
		return players;
	}

	public GuessGame() {
	}

	public void startGame() {
		log.debug("*** Starting guess-game ***");

		turn = (turn + 1) % players.size();
		
		log.debug("*** Randomizing answers ***");
		currentAnswer = randomizeAnswer();

		log.debug("*** Sending GAME_START to all players ***");
		HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
		gameLogicData.put("dataType", GAME_LOGIC);
		gameLogicData.put("gameState", GAME_START);
		gameLogicData.put("isPlayerTurn", false);
		this.broadcast(gameLogicData);

		final GuessPlayer playerWhoDraws = players.get(turn);
		log.debug(String.format("*** Choose player with nick:[%s] to turn ***", playerWhoDraws.getNick()));
		HashMap<String, Object> gameLogicForDraw = new HashMap<String, Object>();
		log.debug(String.format("*** Sending gameState Obj to [%s] ***", playerWhoDraws.getNick()));
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
				log.debug(String.format("*** Timer executes his task***"));
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

	private String randomizeAnswer() {
		// TODO FIXME FIXME
		return currentAnswer;
	}

	public void onConnect(int idWho) {
		GuessPlayer player = getPlayerById(idWho);
		log.debug("A connection established with player: " + player.getNick());

		String message = "Welcome " + player.getNick()
				+ ". Total connection:" + players.size();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("dataType", CHAT_MESSAGE);
		data.put("sender", "Server");
		data.put("message", message);

		this.broadcast(data);

		// send the game state to all players.
		HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
		gameLogicData.put("dataType", GAME_LOGIC);
		gameLogicData.put("gameState", WAITING_TO_START);
		this.broadcast(gameLogicData);

		// start the game if there are 2 or more connections
		if (canBeStarted()) {
			startGame();
		}
	}

	private boolean canBeStarted() {
		return currentState == WAITING_TO_START && players.size() >= 2;
	}

	public void onMessage(int type, String message)
			throws JsonProcessingException, IOException {
		ObjectMapper jackson = new ObjectMapper();
		JsonNode messageTree = jackson.readTree(message);
		int idUser = messageTree.path("userId").asInt();
		int idGame = messageTree.path("gameId").asInt();

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
		} else {
			log.info(String.format("Uknown requested type of [%d]", type));
		}
		
		if (type == CHAT_MESSAGE)
		{
			if (currentState == GAME_START && messageTree.path("message").asText().startsWith(currentAnswer))
			{
				log.debug(String.format("*** WE HAVE A WINNER ***"));
				log.debug(String.format("*** Canceling timer ***"));
				
				timer.cancel();
				
				HashMap<String, Object> gameLogicData = new HashMap<String, Object>();
				gameLogicData.put("dataType", GAME_LOGIC);
				gameLogicData.put("gameState", GAME_OVER);
				gameLogicData.put("winner", getPlayerById(idUser).getNick());
				gameLogicData.put("answer", currentAnswer);
				broadcast(gameLogicData);
				
				currentState = WAITING_TO_START;
				
				log.debug(String.format("*** Trying to start new game ***"));
				if (canBeStarted()) {
					startGame();
				} else {
					log.debug(String.format("*** Cannot start new game***"));
				}
			}
		}
		
		
		if (type == GAME_LOGIC && messageTree.path("gameState").asInt() == GAME_RESTART)
		{
			startGame();
		}
	}

	private void broadcast(Map<String, ? extends Object> message) {
		for (GuessPlayer player : players) {
			player.sendMessage(message);
		}
	}

	private void broadcast(String message) {
		for (GuessPlayer player : players) {
			player.getSocket().sendMessage(message);
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
