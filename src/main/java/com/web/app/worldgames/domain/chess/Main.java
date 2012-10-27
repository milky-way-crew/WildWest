package com.web.app.worldgames.domain.chess;

import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		Board board = Board.createInitializedBoard();
		
		for (int i =0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(board.getFigure(new Position(i, j)).getType());
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println(board);
		
		
//		new TrickyChessGame(player1, player2)
	}
}
