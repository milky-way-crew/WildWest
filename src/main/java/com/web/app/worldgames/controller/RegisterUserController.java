package com.web.app.worldgames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"register", "registration.html"})
public class RegisterUserController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String showPage() {
		return "registration";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit() {
		return "index";
	}
}
