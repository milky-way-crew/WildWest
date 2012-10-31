package com.web.app.worldgames.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;

@Controller
public class TestController {
	private static final Logger log = Logger.getLogger(TestController.class);

	private List<ChatUser> chatUsers = new ArrayList<ChatUser>();

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("user");
//		ChatUser chatUser = new ChatUser(user);

//		request.getSession().setAttribute("chatUser", chatUser);
//		chatUsers.add(chatUser);

		return new ModelAndView("test");
	}

	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	public @ResponseBody
	String onMessage(HttpServletRequest request,
			@RequestParam("type") String type, @RequestParam("data") String data) {
		ChatUser user = getChatUser(request);

		if (type.toLowerCase().trim().equals("update")) {
			log.debug("Update request from user: " + user.getNick());
			return updateUserMessages(user);
		} else if (type.toLowerCase().trim().equals("message")) {
			log.debug("Broadcast request from user: " + user.getNick());
			broadcast(user, data);
		}
		return "";
	}

	private void broadcast(ChatUser user, String data) {
		for (ChatUser chatUser : chatUsers) {
			if (chatUser.getId() != user.getId()) {
				chatUser.addMessage(user.getNick() + ":" + data);
			}
		}
	}

	private ChatUser getChatUser(HttpServletRequest request) {
		return (ChatUser) request.getSession().getAttribute("chatUser");
	}

	private String updateUserMessages(ChatUser user) {
		List<String> messages = user.getMessages();
		return join(messages, "\n");
	}

	private String join(List<String> messages, String delimiter) {
		if (messages.size() < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message);
			sb.append(delimiter);
		}
		return sb.toString();
	}
}