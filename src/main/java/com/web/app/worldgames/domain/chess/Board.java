package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Board {
	private static final Logger log = Logger.getLogger(Board.class); 
	public static final int BOARD_SIZE_X = 7; // was 7
	public static final int BOARD_SIZE_Y = 7; // was 7
	
	private List<Figure> board = new ArrayList<Figure>(BOARD_SIZE_X * BOARD_SIZE_Y);

	public static Board createInitializedBoard() {
		return new Board(new BoardRandomizer());
	}

	private void initWithEmptyFigures() {
		for (int i = 0; i < BOARD_SIZE_X * BOARD_SIZE_Y; i++) {
			board.add(null); // FIXME: must be another way
			putFigureByIndex(i, new Figure());
		}
		log.info("After initializing with empty figure. Board size="  + board.size());
		com.web.app.worldgames.domain.chess.Utils.printBoard(this);
	}

	public Board() {
		initWithEmptyFigures();
	}

	public Board(BoardRandomizer boardInitializer) {
		initWithEmptyFigures();
		boardInitializer.initBoard(this);
	}

	public Figure getFigure(Position key) {
		return board.get(Utils.positiontToIndex(key));
	}

	public List<Figure> getBoard() {
		return board;
	}

	public void setBoard(List<Figure> board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Board [board=" + board + "]";
	}

	public Figure putFigure(Position key, Figure value) {
		int index = Utils.positiontToIndex(key);
		value.setPosition(key);
		log.trace("Putting figure into position");
		log.trace(key);
		log.trace(index + "\n");
		return board.set(index, value);
	}

	public Figure putFigureByIndex(int index, Figure value) {
		value.setPosition(Utils.indexToPosition(index));
		return board.set(index, value);
	}
}
