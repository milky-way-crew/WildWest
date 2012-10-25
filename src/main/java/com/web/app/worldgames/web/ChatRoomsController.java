package com.web.app.worldgames.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/chatRooms")
public class ChatRoomsController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chatRooms");
		return modelAndView;
		
	}
	
}