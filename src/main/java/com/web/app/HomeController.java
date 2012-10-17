package com.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String home() {
		//light comment		
		System.out.println("HomeController: Passing through...");
		return "WEB-INF/views/home.jsp";
	}
}
