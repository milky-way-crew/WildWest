package com.web.app.worldgames.domain.chess;

import java.util.List;
import java.util.Random;

public class BoardRandomizer {
		private static final int SIZE_OF_2_ROWS = Board.BOARD_SIZE_Y * 2;
		private static final int BOARD_SIZE = Board.BOARD_SIZE_Y * Board.BOARD_SIZE_X;

		void initBoard(Board board) {
			// For "up" player
			randomizeArea(board, ChessPlayerTypesEnum.WHITE);
			// For "down" player
			randomizeArea(board, ChessPlayerTypesEnum.BLACK);
		}
		
		public void randomizeArea(Board board, ChessPlayerTypesEnum type) {
			if (type == ChessPlayerTypesEnum.WHITE) {
				randomizePlayerArea(board, 0, SIZE_OF_2_ROWS, ChessPlayerTypesEnum.WHITE);
			} else if (type == ChessPlayerTypesEnum.BLACK) {
				randomizePlayerArea(board, BOARD_SIZE - SIZE_OF_2_ROWS, BOARD_SIZE, ChessPlayerTypesEnum.BLACK);
			} else {
				System.out.println("Unknown type of player");
			}
		}

		private void randomizePlayerArea(Board board, int start, int end, ChessPlayerTypesEnum owner) {
			List<Figure> subList = board.getBoard().subList(start, end);
			for (int i=0; i < subList.size(); i++) {
				Figure figure = subList.get(i);
				figure.setType(getRandomFigure().getType());
				figure.setOwner(owner);
			}
			
			randomizeFlagAndTrap(owner, subList);
		}

		private void randomizeFlagAndTrap(ChessPlayerTypesEnum owner, List<Figure> subList) {
			Random random = new Random();
			
			int flagPosition = random.nextInt(subList.size());
			
			Figure flag = subList.get(flagPosition);
			flag.setType(FigureTypesEnum.FLAG);
//			flag.setOwner(owner);
//			subList.set(flagPosition, flag);
			
			while (true) {
				int trapPosition = random.nextInt(subList.size());
				if (subList.get(trapPosition).getType() == FigureTypesEnum.FLAG) {
					continue;
				} else {
					Figure trap = subList.get(trapPosition);
					trap.setType(FigureTypesEnum.TRAP);
//					flag.setOwner(owner);
//					subList.set(trapPosition, trap);
					break;
				}
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