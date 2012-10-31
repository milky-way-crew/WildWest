package com.web.app.worldgames.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.app.worldgames.service.interfaces.IUserServiceManager;

public class ChessWebSocketHandler extends WebSocketHandler {
	private final static Logger log = Logger
			.getLogger(ChatWebSocketHandler.class);
	
	@Autowired
	private IUserServiceManager userService;

	private final Set<ChessWebSocket> webSockets = new CopyOnWriteArraySet<ChessWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new ChessWebSocket();
	}

	private class ChessWebSocket implements WebSocket.OnTextMessage {
		private Connection connection;

		@Override
		public void onOpen(Connection connection) {
			this.connection = connection;
			webSockets.add(this);
		}

		@Override
		public void onMessage(String data) {
		}

		@Override
		public void onClose(int closeCode, String message) {
			webSockets.remove(this);
		}

	}
}