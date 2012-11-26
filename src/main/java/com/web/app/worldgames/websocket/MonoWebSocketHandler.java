package com.web.app.worldgames.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.app.worldgames.service.interfaces.IUserServiceManager;

public class MonoWebSocketHandler extends WebSocketHandler {
	private final static Logger log = Logger.getLogger(MonoWebSocketHandler.class);
	
	@Autowired
	private IUserServiceManager userService;

	private final Set<MonoWebSocket> webSockets = new CopyOnWriteArraySet<MonoWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new MonoWebSocket();
	}

	public class MonoWebSocket implements WebSocket.OnTextMessage {
		private Connection connection;

		@Override
		public void onOpen(Connection connection) {
			this.connection = connection;
			webSockets.add(this);
			try {
				connection.sendMessage("[Server] Connected succesfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onMessage(String json) {
			ObjectMapper jsonParser = new ObjectMapper();
			
			try {
				log.info("MESSAGE FROM CLIENT" + json);
				JsonNode messageTree = jsonParser.readTree(json);
				String type = messageTree.path("type").getTextValue();
				
				if (type.equals("bind-websocket")) {
					JsonNode node = messageTree.path("data");
					int idUser = node.path("user-id").getIntValue();
					int idGame = node.path("game-id").getIntValue();

					log.debug("[Bind-websocket] From user-id " + idUser);
					
					// getting game from service, setting websocket
				} else {
					
				}
			} catch (IOException e) {
				log.error("Error in receving message fro, client", e);
				e.printStackTrace();
			}
		}

		@Override
		public void onClose(int closeCode, String message) {
			webSockets.remove(this);
		}

		public Connection getConnection() {
			return connection;
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