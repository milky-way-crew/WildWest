package com.web.app.worldgames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private final static Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/index.html", "/WildWest/" })
	public String home(HttpServletRequest request, HttpServletResponse responsce) {
		if (request.getSession().getAttribute("user") == null) {
			return "redirect:loginform.html";
		}
		log.info("HomeController: Passing through...");
		return "home";
	}
	
	
}
