package com.web.app.worldgames.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.chess.Board;
import com.web.app.worldgames.domain.chess.Figure;

@Controller
public class ChessContoller {
	private static final Logger log = Logger.getLogger(ChessContoller.class);
	
	
	@RequestMapping(value="/chess")
	public String test() {
		return "chess";
	}
	

	@RequestMapping(value="/chess2")
	public @ResponseBody Map<String, ? extends Object> test2() {
		HashMap<String,List<Figure>> map = new HashMap<String, List<Figure>>();
		map.put("board", Board.createInitializedBoard().getBoard());
		return map;
	}

}
