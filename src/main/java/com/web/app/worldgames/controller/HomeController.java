package com.web.app.worldgames.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private final static Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/index.html" })
	public String home() {
		// light comment
//		if (session.getAttribute("user") == null) {
//			return "redirect:login";
//		}
		log.info("HomeController: Passing through...");
		return "home";
	}
}
