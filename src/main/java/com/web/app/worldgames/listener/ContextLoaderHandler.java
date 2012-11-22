package com.web.app.worldgames.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.app.worldgames.web.SetupDatabaseManager;
import com.web.app.worldgames.websocket.GuessWebSocketHandler;
import com.web.app.worldgames.websocket.MonoWebSocketHandler;

public class ContextLoaderHandler extends ContextLoaderListener {
	private static final int PORT_MONOPOLY = 8888;
	private static final int PORT_DRAW_GAME = 8889;
	
	private final static Logger log = Logger.getLogger(ContextLoaderHandler.class);
	private Server jettyMonoGame;
	private Server jettyGuessGame;
	

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.info("RESETING DATABASE");
		refreshDatabase(event);
		log.info("STARTING JETTY SERVERS");
//		startJettyEmbededServer(PORT, new MonoWebSocketHandler());
		startMonoGameServer();
		startGuessGameServer();
	}

	private void startMonoGameServer() {
		try {
			log.info("Starting WS-server for monopoly game");
			jettyMonoGame = new Server(PORT_MONOPOLY);
			MonoWebSocketHandler handler = new MonoWebSocketHandler();
			handler.setHandler(new DefaultHandler());
			jettyMonoGame.setHandler(handler);
			jettyMonoGame.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void startGuessGameServer() {
		try {
			log.info("Starting WS-server for guess game");
			jettyGuessGame = new Server(PORT_DRAW_GAME);
			GuessWebSocketHandler guessWebSocketHandler = new GuessWebSocketHandler();
			guessWebSocketHandler.setHandler(new DefaultHandler());
			jettyGuessGame.setHandler(guessWebSocketHandler);
			jettyGuessGame.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		log.info("STOPPING JETTY SERVER");
		stopMonoJettyServer();
		stopGuessJettyServer();
	}

	private void stopGuessJettyServer() {
		if (jettyGuessGame != null) {
			try {
				jettyGuessGame.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void stopMonoJettyServer() {
		if (jettyMonoGame != null) {
			try {
				jettyMonoGame.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void refreshDatabase(ServletContextEvent event) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		SetupDatabaseManager bean = context.getBean(SetupDatabaseManager.class);
		bean.refreshTables();
	}
}