package com.web.app.worldgames.domain.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

public class Board {
	private Map<Position, Figure> board = new HashMap<Position, Figure>();
	public static final int BOARD_SIZE_X = 6;
	public static final int BOARD_SIZE_Y = 8;

	private static class BoardInitializer {
		private void initBoard(Map<Position, Figure> board) {
			for (int i = 0; i < BOARD_SIZE_X + 1; i++) {
				for (int j = 0; j < BOARD_SIZE_Y + 1; j++) {
					Figure figure;

					if (!(i > 1 && i < 7)) {
						figure = getRandomFigure();
					} else {
						figure = Figure.EMPTY;
					}
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

	public Figure putFigure(Position key, Figure value) {
		return board.put(key, value);
	}
}
