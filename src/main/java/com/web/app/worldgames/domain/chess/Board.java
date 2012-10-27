package com.web.app.worldgames.domain.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

public class Board {
	private Map<Position, Figure> board = new HashMap<Position, Figure>();

	private static class BoardInitializer {
		private void initBoard(Map<Position, Figure> board) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Figure figure = getRandomFigure();
					board.put(new Position(i, j), figure);
				}
			}
		}

		private Figure getRandomFigure() {
			Random random = new Random();
			int typeId = random.nextInt(FigureTypesEnum.values().length);
			Figure figure = new Figure();
			figure.setType(FigureTypesEnum.getTypeById(typeId));
			return figure;
		}
	}

	public static Board createInitializedBoard() {
		return new Board(new BoardInitializer());
	}

	public Board() {
	}
	public Board(BoardInitializer boardInitializer) {
		boardInitializer.initBoard(board);
	}

	public Figure getFigure(Position key) {
		return getBoard().get(key);
	}

	public Figure setFigure(Position key, Figure value) {
		return getBoard().put(key, value);
	}

	public Set<Entry<Position, Figure>> getAllFigures() {
		return getBoard().entrySet();
	}

	public Map<Position, Figure> getBoard() {
		return board;
	}

	public void setBoard(Map<Position, Figure> board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Board [board=" + board + "]";
	}
	
	
}
