package com.web.app.worldgames.domain.chess;

public class Utils {
	private Utils() {
	}
	
	public static void printBoard(Board board) {
		for (int i = 0; i < Board.BOARD_SIZE_X; i++) {
			for (int j = 0; j < Board.BOARD_SIZE_Y; j++) {
				if (i == 0 && j == 0) {
					System.out.println("  0 1 2 3 4 5 6 7 8");
					System.out.println("  -----------------");
				}
				if (j == 0) {
					System.out.print(i + "|");
				}

				FigureTypesEnum type = board.getFigure(new Position(i, j))
						.getType();
				System.out.print(type == null ? "." : type);
				System.out.print(" ");
			}
			System.out.print("|" + i);
			System.out.println();
		}
		System.out.println("  -----------------");
		System.out.println("  0 1 2 3 4 5 6 7 8");
	}
	
	public static Position indexToPosition(int index) {
		// TODO: not sure
		int y = index % Board.BOARD_SIZE_Y;
		int x = index / Board.BOARD_SIZE_Y;
		return new Position(x, y);
	}
	
	public static int positiontToIndex(Position key) {
		return key.getX() * Board.BOARD_SIZE_Y + key.getY();
	}

	public static Position stringToPosition(String pos) {
		//12
		if (pos.length() != 2) {
			throw new RuntimeException("Unsupported operation");
		}

		return new Position(Integer.parseInt(pos.substring(0,1)), Integer.parseInt(pos.substring(1,2)));

	}
}
