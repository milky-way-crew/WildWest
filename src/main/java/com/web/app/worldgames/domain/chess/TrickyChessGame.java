package com.web.app.worldgames.domain.chess;


public class TrickyChessGame {
	private static final int COUNT_PLAYERS = 2;

	private Board board = new Board();
	private Player[] players;

	public TrickyChessGame() {
		// TODO Auto-generated constructor stub
	}

	public TrickyChessGame(Player player1, Player player2) {
		players = new Player[] { player1, player2 };
	}

	public void startGame() {
		while (!this.isEnded()) {
			printBoard(board);
			Player nextPlayer = getNextPlayer();
			System.out.println("Move of player: " + nextPlayer.getNick());
			Move playerMove = nextPlayer.askForNextMove();
			moveTo(playerMove);

			// ResultEnum resultOfBattle = updateBoard(playerMove);
			// notifyPlayers(resultOfBattle);
		}
	}

	// TODO: Don't even touch that
	private int _cursor = 0;

	private Player getNextPlayer() {
		_cursor = (_cursor >= players.length) ? _cursor - players.length
				: _cursor;
		return players[_cursor++];
	}

	public boolean isEnded() {
		// TODO: FIXME: TODO: FIXME
		if (false) {
			for (Player player : players) {
				if (player.getFiguresFromBoad(board).size() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean moveTo(Move move) {
		return moveFigure(move.getStart(), move.getEnd());
	}

	public boolean moveFigure(Position from, Position to) {

		Figure figure = board.getFigure(from);
		if (figure != Figure.EMPTY && isValidMove(from, to)) {
			// Potential opponent figure
			Figure figure2 = board.getFigure(to);
			if (figure2 != Figure.EMPTY) {
				battle(from, to, figure, figure2);
			} else {
				move_to_empty_cell(from, to, figure);
			}
			// Valid move; Was battle or empty space there
			return true;
		} else {
			// Totally invalid move; Cannot move there
			return false;
		}
	}

	private void battle(Position from, Position to, Figure figure,
			Figure figure2) {
		ResultEnum resultOfBattle = battle(figure, figure2);
		if (resultOfBattle == ResultEnum.WIN) {
			// Logs here
			System.out
					.println(figure.getType() + " beats " + figure2.getType());
			move_to_empty_cell(from, to, figure);
		} else if (resultOfBattle == ResultEnum.LOOSE) {
			// Logs here
			System.out
					.println(figure2.getType() + " beats " + figure.getType());
			board.putFigure(from, Figure.EMPTY);
		}
	}

	private void move_to_empty_cell(Position from, Position to, Figure figure) {
		board.putFigure(from, Figure.EMPTY);
		board.putFigure(to, figure);
	}

	private ResultEnum battle(Figure figure, Figure figure2) {
		return figure.getType().beat(figure2.getType());
	}

	public boolean isValidMove(Position from, Position to) {
		return true;
//		int x_offset = Math.abs(from.getX() - to.getX());
//		int y_offset = Math.abs(from.getY() - to.getY());
//
//		if (to.getX() > Board.BOARD_SIZE_X || to.getX() < 0
//				|| to.getY() > Board.BOARD_SIZE_Y || to.getY() < 0) {
//			return false;
//		}
//
//		if (y_offset > 1 || x_offset > 1) {
//			return false; // Moves on distant >1 cells not allowed
//		} else if (y_offset - x_offset == 0) {
//			return false; // Diagonal moves not allowed
//		} else {
//			return true;
//		}
	}

	static void printBoard(Board board) {
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

}
