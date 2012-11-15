package com.web.app.worldgames.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

import com.web.app.worldgames.domain.guess.GuessGame;
import com.web.app.worldgames.domain.guess.GuessPlayer;
import com.web.app.worldgames.service.GuessGameService;

public class GuessWebSocketHandler extends WebSocketHandler {
	private final static Logger log = Logger.getLogger(GuessWebSocketHandler.class);

	private final Set<GuessWebSocket> webSockets = new CopyOnWriteArraySet<GuessWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new GuessWebSocket();
	}

	public class GuessWebSocket implements WebSocket.OnTextMessage {
		private Connection connection;
		private Integer idUser;
		private Integer idGame;
		
		
		@Override
		public void onOpen(final Connection connection) {
			log.info("Websocket connection opened");
			this.connection = connection;
			this.connection.setMaxIdleTime((int) TimeUnit.MINUTES.toMillis(10));
			webSockets.add(this);
		}

		@Override
		public void onMessage(String json) {
			log.info("MESSAGE FROM CLIENT" + json);
			ObjectMapper jackson = new ObjectMapper();
			
			try {
				JsonNode messageTree = jackson.readTree(json);
				
				int type = messageTree.path("dataType").getIntValue();
				if (idUser == null) {
					idUser = messageTree.path("userId").asInt();
				}
				
				if (idGame == null) {
					idGame = messageTree.path("gameId").asInt();
				}
				
				if (idGame != null && idUser != null) {
					log.debug(String.format("Getting game with id:{%d} from GuessService", idGame));
					GuessGame game = GuessGameService.INSTANCE.getGameById(idGame);
					tryBindSocket(idUser, game);
					log.debug("Redirecting received message to onMessage() method of GuessGame"); 
					game.onMessage(type, json);
				} else {
					log.info("Not enaugh info about user, missing idUser/idGame");
				}
				
			} catch (IOException e) {
				log.error("Error in receving message fro, client", e);
				e.printStackTrace();
			}
		}

		private void tryBindSocket(int idUser, GuessGame game) {
			log.debug(String.format("Trying to update socket-connection with id-user:%d", idUser));
			
			GuessPlayer userFrom = game.getPlayerById(idUser);
			GuessWebSocket socket = userFrom.getSocket();
			if (socket == null || !socket.equals(this)) {
				log.info("Binding ws to player with id: " + userFrom.getId());
				userFrom.setSocket(this);
				game.onConnect(idUser);
			}
		}

		@Override
		public void onClose(int closeCode, String message) {
			log.debug(String.format("User with id:%d disconnected", idUser));
			GuessGame game = GuessGameService.INSTANCE.getGameById(idGame);
			game.removePlayer(game.getPlayerById(idUser));
			GuessGameService.INSTANCE.tryRemoveGame(idGame);
			
			log.info(String.format("Removing web-socket idUser:%d", idUser));
			
			webSockets.remove(this);
		}

		public Connection getConnection() {
			return connection;
		}
		
		public void broadcast(String message) {
			for (GuessWebSocket socket : webSockets) {
				try {
					socket.connection.sendMessage(message);
				} catch (IOException e) {
					log.error("Error while sending message to socket", e);
				}
			}
		}
		public void sendMessage(String json) {
			try {
				connection.sendMessage(json);
			} catch (IOException e) {
				log.error("Error occurend while sending to client", e);
			}
		}
		
		public void sendMessage(Map<String, ? extends Object> message) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String respose = mapper.writeValueAsString(message);
				log.debug("Sending message: " + respose);
				connection.sendMessage(respose);
			} catch (JsonGenerationException e) {
				log.error("Error while generating JSON: ", e);
				e.printStackTrace();
			} catch (JsonMappingException e) {
				log.error("Error mapping JSON: ", e);
				e.printStackTrace();
			} catch (IOException e) {
				log.error("Cannot send data through web-socket: ", e);
				e.printStackTrace();
			}
		}
	}
}