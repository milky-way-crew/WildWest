package com.web.app.worldgames.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.app.worldgames.web.SetupDatabaseManager;
import com.web.app.worldgames.websocket.GuessWebSocketHandler;
import com.web.app.worldgames.websocket.MonoWebSocketHandler;

public class ContextLoaderHandler extends ContextLoaderListener {
	private static final int PORT = 8888;
	private static final int PORT_DRAW_GAME = 9999;
	
	private final static Logger log = Logger.getLogger(ContextLoaderHandler.class);
	private Server jetty;
	private Server jettyGuessGame;
	

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.info("RESETING DATABASE");
		refreshDatabase(event);
		log.info("STARTING JETTY SERVER");
		startJettyEmbededServer(PORT, new MonoWebSocketHandler());
		startGuessGameServer(PORT_DRAW_GAME, new GuessWebSocketHandler());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		log.info("STOPPING JETTY SERVER");
		stopJettyEmbededServer();
	}

	private void refreshDatabase(ServletContextEvent event) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		SetupDatabaseManager bean = context.getBean(SetupDatabaseManager.class);
		bean.refreshTables();
	}

	private void stopJettyEmbededServer() {
		if (jetty != null) {
			try {
				jetty.stop();
				jettyGuessGame.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startJettyEmbededServer(int port, WebSocketHandler handler) {
		try {
			jetty = new Server(port);
			handler.setHandler(new DefaultHandler());
			jetty.setHandler(handler);
			jetty.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void startGuessGameServer(int port, WebSocketHandler handler) {
		try {
			jettyGuessGame = new Server(port);
			handler.setHandler(new DefaultHandler());
			jettyGuessGame.setHandler(handler);
			jettyGuessGame.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}