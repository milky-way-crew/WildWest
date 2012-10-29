package com.web.app.worldgames.domain.chess;

import java.util.Scanner;

public class ConsolePlayer extends Player {

	@Override
	public Move askForNextMove() {
		Position from = new Position(), to = new Position();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Go from:");
		from.setX(scanner.nextInt());
		from.setY(scanner.nextInt());
		System.out.println(from);
		
		System.out.print("Go to:");
		to.setX(scanner.nextInt());
		to.setY(scanner.nextInt());
		System.out.println(to);
		
		return new Move(from, to);
	}

}
