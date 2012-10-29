package com.web.app.worldgames.domain.chess;

import java.util.Scanner;


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
		
//		while (true) {
			
//			game.isValidMove(from, to);
//			//			game.moveFigure(from, to);
//			System.out.println(game.isValidMove(from, to));
//		}
	}

	private static void promt() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Go from:");
		from.setX(scanner.nextInt());
		from.setY(scanner.nextInt());
		System.out.println(from);
		System.out.print("Go to:");
		to.setX(scanner.nextInt());
		to.setY(scanner.nextInt());
		System.out.println(to);
	}

}
