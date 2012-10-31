package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Board {
	private List<Figure> board = new ArrayList<Figure>(BOARD_SIZE_X * BOARD_SIZE_Y);
	public static final int BOARD_SIZE_X = 7;
	public static final int BOARD_SIZE_Y = 9;

	private static class BoardInitializer {
		private static final int SIZE_OF_2_ROWS = BOARD_SIZE_Y * 2;
		private static final int BOARD_SIZE = BOARD_SIZE_Y * BOARD_SIZE_X;

		private void initBoard(Board board) {
			// For "up" player
			randomizePlayerArea(board, 0, SIZE_OF_2_ROWS, new Player() {
				{
					setId(1);
				}
				@Override
				public Move askForNextMove() {
					return null;
				}
				
			});
			// For "down" player
			randomizePlayerArea(board, BOARD_SIZE - SIZE_OF_2_ROWS, BOARD_SIZE, new Player() {
				{
					setId(2);
				}
				@Override
				public Move askForNextMove() {
					return null;
				}
			});
		}

		private void randomizePlayerArea(Board board, int start, int end, Player owner) {
			List<Figure> subList = board.getBoard().subList(start, end);
			for (int i=0; i < subList.size(); i++) {
				Figure figure = subList.get(i);
				figure.setType(getRandomFigure().getType());
				figure.setOwner(owner);
			}
		}

		private static Figure getRandomFigure() {
			Random random = new Random();
			// * Minus flag and empty cell = 2
			int typeId = random.nextInt(FigureTypesEnum.values().length - 2);
			Figure figure = new Figure();
			figure.setType(FigureTypesEnum.getTypeById(typeId));
			return figure;
		}
	}

	public static Board createInitializedBoard() {
		return new Board(new BoardInitializer());
	}

	private void initWithEmptyFigures() {
		for (int i = 0; i < BOARD_SIZE_X * BOARD_SIZE_Y; i++) {
			getBoard().add(null); // FIXME: must be another way
			putFigureByIndex(i, new Figure());
		}
	}

	public Board() {
	}

	public Board(BoardInitializer boardInitializer) {
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
		System.out.println(key);
		System.out.println(index + "\n");
		return board.set(index, value);
	}

	public Figure putFigureByIndex(int index, Figure value) {
		value.setPosition(Utils.indexToPosition(index));
		return board.set(index, value);
	}
}
