package com.web.app.worldgames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.worldgames.domain.User;

@Controller
@RequestMapping(value = { "/loginform.html" })
public class LoginUserConroller {
	private static final Logger log = Logger.getLogger(LoginUserConroller.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(Model model) {
		log.info("LoginController GET");
		
		model.addAttribute("user", new User());
		return "loginform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute User user,
			BindingResult result) {
		
		// validate here
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "redirect:index.html";
	}
}
