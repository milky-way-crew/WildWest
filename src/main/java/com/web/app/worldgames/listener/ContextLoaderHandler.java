package com.web.app.worldgames.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.app.worldgames.web.SetupDatabaseManager;

public class ContextLoaderHandler extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		SetupDatabaseManager bean = context.getBean(SetupDatabaseManager.class);
		bean.refreshTables();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}
}
