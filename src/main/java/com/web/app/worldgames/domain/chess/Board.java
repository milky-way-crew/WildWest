package com.web.app.worldgames.domain.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Board {
	private Map<Position, Figure> board = new HashMap<Position, Figure>();
	
	private void initBoard(Map<Position, Figure> board) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board.put(new Position(i, j), new Figure());
			}
		}
		
	}
	public Board() {
		initBoard(getBoard());
	}

	public Figure getFigure(Position key) {
		return getBoard().get(key);
	}

	public Figure setFigure(Position key, Figure value) {
		return getBoard().put(key, value);
	}
	
	public Set<Entry<Position,Figure>> getAllFigures() {
		return getBoard().entrySet();
	}
	public Map<Position, Figure> getBoard() {
		return board;
	}
	public void setBoard(Map<Position, Figure> board) {
		this.board = board;
	}
}
