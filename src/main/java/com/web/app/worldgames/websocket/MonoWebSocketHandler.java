package com.web.app.worldgames.websocket;

import java.io.IOException;
import java.text.MessageFormat;
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

import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;
import com.web.app.worldgames.domain.monopoly.game.WebSocketTransport;
import com.web.app.worldgames.service.MonopolyService;

public class MonoWebSocketHandler extends WebSocketHandler {
	private final static Logger log = Logger
			.getLogger(MonoWebSocketHandler.class);
	private final Set<MonoWebSocket> webSockets = new CopyOnWriteArraySet<MonoWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new MonoWebSocket();
	}

	public class MonoWebSocket implements WebSocket.OnTextMessage {
//		private static final String DATA_NODE = "data";
		private static final String ID_NODE = "id";
		private static final String TYPE_NODE = "type";
		private static final String BIND_WEBSOCKET = "bind-websocket";
		private Connection connection;

		@Override
		public void onOpen(Connection connection) {
			log.debug("[Web-socket] opening connection");
			// FIXME: Safe or not?
			this.connection = connection;
			this.connection.setMaxIdleTime((int) TimeUnit.MINUTES.toMillis(60));
			webSockets.add(this);
		}

		@Override
		public void onMessage(String json) {
			ObjectMapper jsonParser = new ObjectMapper();

			try {
				log.debug("[FULL MESSAGE]" + json);
				JsonNode messageTree = jsonParser.readTree(json);

				String typeOfMessage = messageTree.path(TYPE_NODE)
						.getTextValue();

				// getting id of user and so on
				JsonNode idBlock = messageTree.path(ID_NODE);
				int idUser = idBlock.path("user-id").getIntValue();
				int idGame = idBlock.path("game-id").getIntValue();
//				JsonNode dataBlock = messageTree.path(DATA_NODE);

				if (typeOfMessage.equals(BIND_WEBSOCKET)) {
					// way #2
					// getting game from service, setting web-socket
					// directly to player object
					log.debug($("[Bind-websocket] From user-id: ${0} ", idUser));
					WebSocketTransport transport = WebSocketTransport.getInstance();
					transport.bind(idUser, this);
				} else {
					log.debug($("[message] type:{0} from id:{1}", typeOfMessage, idUser));
					
					MonopolyService monopolyService = MonopolyService.getInstance();
					MonopolyManager manager = monopolyService.getGameById(idGame);
					
					manager.onMessage(idUser, typeOfMessage, json);
				}
			} catch (IOException e) {
				log.error("Error while receiving message from client", e);
				e.printStackTrace();
			}
		}

		@Override
		public void onClose(int closeCode, String message) {
			log.debug("Closing web-socket connection. Close code: " + closeCode);
			log.debug("Close message" + message);
			webSockets.remove(this);
		}

		public Connection getConnection() {
			return connection;
		}

		/**
		 * Sends message-map encoded as string through web-socket directly to
		 * user browser
		 * 
		 * @param messageMap
		 *            map - that represents JSON-object
		 */
		public void sendMessage(Map<String, ? extends Object> messageMap) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String respose = mapper.writeValueAsString(messageMap);
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

	/**
	 * MessageFormat clone, don't be scary
	 * 
	 * @param pattern
	 * @param args
	 * @return
	 */
	private String $(String pattern, Object... args) {
		return MessageFormat.format(pattern, args);
	}
}
