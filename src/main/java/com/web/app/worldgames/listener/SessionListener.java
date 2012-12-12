package com.web.app.worldgames.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.web.app.worldgames.service.ChatRoomServiceManager;
import com.web.app.worldgames.web.ChatRoomsController;

public class SessionListener implements HttpSessionListener {

    private static ChatRoomServiceManager manager = ChatRoomsController
	    .getManager();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
	HttpSession session = event.getSession();
	ServletContext context = session.getServletContext();
	context.setAttribute(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
	HttpSession session = event.getSession();
	manager.onSessionClose(session.getId());
	ServletContext context = session.getServletContext();
	context.removeAttribute(session.getId());
    }

    public static HttpSession getSession(HttpServletRequest request,
	    String sessionId) {
	ServletContext context = request.getServletContext();
	HttpSession session = (HttpSession) context.getAttribute(sessionId);
	return session;
    }

}
