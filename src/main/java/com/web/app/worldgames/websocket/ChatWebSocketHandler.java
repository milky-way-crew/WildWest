package com.web.app.worldgames.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.app.worldgames.service.interfaces.IUserServiceManager;

public class ChatWebSocketHandler extends WebSocketHandler {
	private final static Logger log = Logger
			.getLogger(ChatWebSocketHandler.class);
	
	@Autowired
	private IUserServiceManager userService;

	private final Set<ChatWebSocket> webSockets = new CopyOnWriteArraySet<ChatWebSocket>();

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
//		request.getSession()
//		Object attribute = request.getSession().getAttribute("user");
//		log.info(attribute);
		return new ChatWebSocket();
	}

	private class ChatWebSocket implements WebSocket.OnTextMessage {
		private Connection connection;
		private String userName = "anon";
		private final Pattern authCmdPattern = Pattern
				.compile("^\\/auth ([\\S]+).*");
		private final Pattern getUsersCmdPattern = Pattern
				.compile("^\\/getUsers.*");
		private final Pattern helpCmdPattern = Pattern.compile("^\\/help.*");

		public ChatWebSocket() {
//			String user = (String) session.getAttribute("user");
//			this.userName = (user == null) ? "Anon" : user;
		}

		@Override
		public void onOpen(Connection connection) {
			this.connection = connection;
			webSockets.add(this);
		}

		@Override
		public void onMessage(String data) {
			data = data.replaceAll("<", "<").replaceAll(">", ">");
			if (authCmdPattern.matcher(data).matches()) {
				Matcher matcher = authCmdPattern.matcher(data);
				matcher.find();
				
				userName = matcher.group(1);

				try {
					for (ChatWebSocket webSocket : webSockets) {
						webSocket.connection
								.sendMessage("inf|"
										+ (webSocket.equals(this) ? "Вы успешно авторизировались"
												: ("В чат подключился <b>"
														+ userName + "</b>")));
					}
				} catch (IOException x) {
					connection.close();
				}

			} else if (getUsersCmdPattern.matcher(data).matches()) {
				String userList = "";

				for (ChatWebSocket webSocket : webSockets) {
					userList += webSocket.userName + ", ";
				}
				userList = userList.substring(0, userList.length() - 2);

				try {
					connection
							.sendMessage("inf|Список активных пользователей: "
									+ userList);

				} catch (IOException x) {
					connection.close();
				}
			} else if (helpCmdPattern.matcher(data).matches()) {
				String helpMessage = "Отправлять сообщения можно просто написав "
						+ "их в поле для ввода и нажать Enter.<br />"
						+ "Чат поддерживает три команды:<br />"
						+ "<ul><li><b>/help</b> - для распечатки этого сообщения</li>"
						+ "<li><b>/getUsers</b> - для получения списка пользователей</li>"
						+ "<li><b>/auth <i>ник</i></b> - для авторизации</li></ul>";

				try {
					connection.sendMessage("inf|" + helpMessage);
				} catch (IOException x) {
					connection.close();
				}
			} else {
				try {
					if (userName == null) {
						connection
								.sendMessage("err|Вы не авторизированны<br />"
										+ "Используйте команду <b>/help</b> для помощи");
						return;
					}
					for (ChatWebSocket webSocket : webSockets) {
						webSocket.connection.sendMessage((webSocket
								.equals(this) ? "out|"
								: ("in|" + userName + "|"))
								+ data);
					}
				} catch (IOException x) {
					connection.close();
				}
			}
		}

		@Override
		public void onClose(int closeCode, String message) {
			webSockets.remove(this);
		}

	}
}