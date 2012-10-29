package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Board {
	private List<Figure> board = new ArrayList<Figure>(BOARD_SIZE_X * BOARD_SIZE_Y) {
		{
			for (int i = 0; i < BOARD_SIZE_X * BOARD_SIZE_Y; i++) {
				this.add(Figure.EMPTY);
			}
		}
	};
	public static final int BOARD_SIZE_X = 7;
	public static final int BOARD_SIZE_Y = 9;

	private static class BoardInitializer {
		private static final int SIZE_OF_2_ROWS = BOARD_SIZE_Y * 2;

		private void initBoard(Board board) {
			randomizePlayerUp(board);
			randomizePlayerDown(board);
		}

		private void randomizePlayerDown(Board board) {
			int size = BOARD_SIZE_Y * BOARD_SIZE_X;
			List<Figure> subList = board.getBoard().subList(size - SIZE_OF_2_ROWS, size);
			for (int i=0; i < subList.size(); i++) {
				subList.set(i, getRandomFigure());
			}
		}

		private void randomizePlayerUp(Board board) {
			List<Figure> subList = board.getBoard().subList(0, SIZE_OF_2_ROWS);
			for (int i=0; i < subList.size(); i++) {
				subList.set(i, getRandomFigure());
			}
		}

		private static Figure getRandomFigure() {
			Random random = new Random();
			int typeId = random.nextInt(FigureTypesEnum.values().length - 2); // minus
																				// flag
																				// and
																				// empty
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
		boardInitializer.initBoard(this);
	}

	public Figure getFigure(Position key) {
		return board.get(positiontToIndex(key));
	}

	public Set<Entry<Position, Figure>> getAllFigures() {
		// TODO: FIXME
		return null;
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
		int index = positiontToIndex(key);
		System.out.println(key);
		System.out.println(index + "\n");
		return board.set(index, value);
	}

	public Figure putFigureByIndex(int index, Figure value) {
		return board.set(index, value);
	}

	private int positiontToIndex(Position key) {
		return key.getX() * BOARD_SIZE_Y + key.getY();
	}
}
