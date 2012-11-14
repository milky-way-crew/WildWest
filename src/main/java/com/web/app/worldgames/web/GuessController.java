package com.web.app.worldgames.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuessController {

	@RequestMapping(value = "/1")
	public String showPage() {
		return "guess-game";
	}
}
