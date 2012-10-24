package com.web.app.worldgames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/chatRooms.html")
public class ChatRoomsController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chatRooms");
		return modelAndView;
		
	}
	
}
