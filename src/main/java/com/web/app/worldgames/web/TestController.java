package com.web.app.worldgames.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView showPage() {
		return new ModelAndView("test");
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onPost(@RequestParam String message) {
		return null;
		
	}
}
