package com.web.app.worldgames.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

import com.web.app.worldgames.websocket.MonoWebSocketHandler.MonoWebSocket;

public class GuessWebSocketHandler extends WebSocketHandler {
	private static final Logger log = Logger.getLogger(GuessWebSocketHandler.class);
	private final Set<GuessWebSocket> webSockets = new CopyOnWriteArraySet<GuessWebSocket>();
	
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		return new GuessWebSocket();
	}
	
	public class GuessWebSocket implements WebSocket.OnTextMessage {
		private Connection connection;

		@Override
		public void onClose(int arg0, String arg1) {
			log.info("Connection closed code: " + arg0);
			webSockets.remove(this);
		}

		@Override
		public void onOpen(Connection connection) {
			log.debug("Opening connection");
			this.connection = connection;
			webSockets.add(this);
		}

		@Override
		public void onMessage(String message) {
			log.debug("Receeved message : " + message);
		}
	}
}
