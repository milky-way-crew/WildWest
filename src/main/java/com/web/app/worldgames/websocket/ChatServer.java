package com.web.app.worldgames.websocket;

import org.eclipse.jetty.server.Server;

public class ChatServer {
	private int port = 8888;
	private Server server;
	
	public ChatServer(int port) {
		super();
		this.port = port;
	}

}
