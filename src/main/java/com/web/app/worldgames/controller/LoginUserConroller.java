package com.web.app.worldgames.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = {"/login", "/loginform.html"})
public class LoginUserConroller {

	@RequestMapping(method = RequestMethod.GET)
	public String showPage() {
		return "loginform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, @RequestParam(value="nickName") String nickName) {
		HttpSession session = request.getSession();
		session.setAttribute("user", nickName);
		return "redirect:index";
	}
}
