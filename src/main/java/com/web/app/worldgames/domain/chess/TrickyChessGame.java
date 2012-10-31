package com.web.app.worldgames.domain.chess;


public class TrickyChessGame {
	@SuppressWarnings("unused") // Yes, i know it unused. So what?
	private static final int COUNT_PLAYERS = 2;

	private Board board = new Board();
	private Player[] players;

	public TrickyChessGame() {
		// TODO Beeeeeep
	}

	public TrickyChessGame(Player player1, Player player2) {
		players = new Player[] { player1, player2 };
	}

	public void startGame() {
		while (!this.isEnded()) {
			Utils.printBoard(board);
			Player nextPlayer = getNextPlayer();
			System.out.println("Move of player: " + nextPlayer.getNick());
			Move playerMove = nextPlayer.askForNextMove();
			moveTo(playerMove);

			// ResultEnum resultOfBattle = updateBoard(playerMove);
			// notifyPlayers(resultOfBattle);
		}
	}

	// TODO: Don't even touch that, it works perfect
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

		//		if (figure.getOwner() != null && isValidMove(from, to)) {
		if (figure.getType() != null && isValidMove(from, to)) {
			// Potential opponent figure
			Figure figure2 = board.getFigure(to);
			if (figure2.getType() != null) {
				// Ok its not empty -> Battle
				battle(from, to, figure, figure2);
			} else {
				_move(from, to, figure);
			}
			// Valid move; Was (battle || empty space) there
			return true;
		} else {
			System.err.println("* Cannot move there.");
			// Totally invalid move; Cannot move there
			return false;
		}
	}

	private void battle(Position from, Position to, Figure figure,
			Figure figure2) {
		ResultEnum resultOfBattle = battle(figure, figure2);
		if (resultOfBattle == ResultEnum.WIN) {
			// Logs here
			System.err.println("* WIN: Updating position");
			_move(from, to, figure);
		} else if (resultOfBattle == ResultEnum.LOOSE) {
			// Logs here
			System.err.println("* LOOSE: Removing figure.");
			_remove(from);
		}
	}

	private void _remove(Position from) {
		board.putFigure(from, new Figure());
	}

	private void _move(Position from, Position to, Figure figure) {
		System.err.println("* Moving to empty cell");
		_remove(from);
		board.putFigure(to, figure);
	}

	private ResultEnum battle(Figure figure, Figure figure2) {
		return figure.getType().beat(figure2.getType());
	}

	public boolean isValidMove(Position from, Position to) {
		int xDelta = Math.abs(from.getX() - to.getX());
		int yDelta = Math.abs(from.getY() - to.getY());

		if (to.getX() > Board.BOARD_SIZE_X || to.getX() < 0
				|| to.getY() > Board.BOARD_SIZE_Y || to.getY() < 0) {
			return false;
		}

		if (yDelta > 1 || xDelta > 1) {
			return false; // Moves on distant >1 cells not allowed
		} else if (yDelta - xDelta == 0) {
			return false; // Diagonal moves not allowed
		} else {
			return true; // Everything is OK.
		}
	}
}
