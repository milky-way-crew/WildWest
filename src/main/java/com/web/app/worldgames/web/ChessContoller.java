package com.web.app.worldgames.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.chess.Board;

@Controller
public class ChessContoller {
	
	@RequestMapping(value="/chess")
	public String test() {
		return "chess";
	}
	

	@RequestMapping(value="/chess2")
	public @ResponseBody Board test2() {
		return Board.createInitializedBoard();
	}

}
