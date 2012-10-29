package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Position from = new Position(), to = new Position();

	public static void main(String[] args) {
		 Board board = Board.createInitializedBoard();
//		Board board = new Board();

		TrickyChessGame game = new TrickyChessGame(new ConsolePlayer() {
			{
				setNick("ONE");
			}
		}, new ConsolePlayer() {
			{
				setNick("TWO");
			}
		});
		Figure figure = new Figure();
		figure.setType(FigureTypesEnum.PAPER);

		board.putFigure(new Position(0, 1), figure);
		board.putFigure(new Position(5, 8), figure);

		game.setBoard(board);
		game.startGame();

		// TrickyChessGame.printBoard(test);
	}
}
