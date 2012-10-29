package com.web.app.worldgames.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.app.worldgames.web.SetupDatabaseManager;
import com.web.app.worldgames.websocket.ChatWebSocketHandler;

public class ContextLoaderHandler extends ContextLoaderListener {
	private static final int PORT = 8888;
	private final static Logger log = Logger
			.getLogger(ContextLoaderHandler.class);
	private Server jetty;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.info("RESETING DATABASE");
		refreshDatabase(event);
		log.info("STARTING JETTY SERVER");
		startJettyEmbededServer();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void startJettyEmbededServer() {
		try {
			jetty = new Server(PORT);
			ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();
			chatWebSocketHandler.setHandler(new DefaultHandler());
			jetty.setHandler(chatWebSocketHandler);
//			ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
////			handler.setContextPath("/");
//			jetty.setHandler(handler);
			jetty.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
