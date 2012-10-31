package com.web.app.worldgames.domain.chess;

public class Main {
	static Position from = new Position(), to = new Position();

	public static void main(String[] args) {
		Board board = Board.createInitializedBoard();

		TrickyChessGame game = new TrickyChessGame(new ConsolePlayer() {
			{
				setNick("ONE");
			}
		}, new ConsolePlayer() {
			{
				setNick("TWO");
			}
		});

		game.setBoard(board);
		game.startGame();
	}
}
