package com.web.app.worldgames.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private final static Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/home" })
	public String home() {
		// light comment
		log.info("HomeController: Passing through...");
		return "home";
	}
}
