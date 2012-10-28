package com.web.app.worldgames.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpErrorController {
	
	@RequestMapping(value="error/404.html")
	public String on404() {
		return "error/404";
	}
	
	@RequestMapping(value="error/500.html")
	public String on500() {
		return "error/500";
		
	}
}
