package com.web.app.worldgames.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/chat.html")
public class ChatController{

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPage(HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chat");
		modelAndView.addObject("user", req.getAttribute("user"));
		return modelAndView;
	}

}
