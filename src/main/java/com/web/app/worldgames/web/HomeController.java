package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private final static Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/index.html", "/home" })
	public String home(HttpServletRequest request, HttpServletResponse responsce, Model model) {
		Object loginedUser = request.getSession().getAttribute("user");
		if (loginedUser == null) {
			return "redirect:login";
		}
		log.info("HomeController: Passing through...");
		model.addAttribute("user", loginedUser);
		log.info(loginedUser.toString());
		return "home";
	}
	
	
}
