package com.web.app.worldgames.domain.chess;

import com.web.app.worldgames.domain.chess.Move;
import org.apache.log4j.Logger;

public class ChessGame {
	private static final Logger log = Logger.getLogger(ChessGame.class);

	@SuppressWarnings("unused")
	// Yes, i know it unused. So what?
	private static final int COUNT_PLAYERS = 2;

	private Board board = new Board();
	private Player[] players;
	private boolean isStarted = false;
	private Player white, black;
	private Player currentPlayer;
	private boolean isEnded;

	private Move lastMove;
	private ResultEnum lastMoveResult;

	public ResultEnum getLastMoveResult() {
		return lastMoveResult;
	}

	public void setLastMoveResult(ResultEnum result) {
		lastMoveResult = result;
	}

	public void setLastMove(Move move) {
		this.lastMove = move;
	}

	public Move getLastMove() {
		return this.lastMove;
	}

	public Player getPlayerById(int id) {
		for (Player player : players) {
			if (player.getId() == id) {
				return player;
			}
		}
		return null;
	}

	public boolean canBeStarted() {
		return white != null && black != null && board != null;
	}

	public ChessGame() {
		// TODO Beeeeeep
	}

	public ChessGame(Player player1, Player player2) {
		players = new Player[] { player1, player2 };
	}

	public void startGame() {
		log.debug("Trying to start game");
		if (this.canBeStarted()) {
			log.debug("Game can be started");
			this.setStarted(true);
			if (getCurrentPlayer() == null) {
				this.players = new Player[] { white, black };
				setCurrentPlayer(white);
			}
			log.debug("Setting current player to WHITE");
		}
	}

	public Player getNextPlayer() {
		if (this.isStarted()) {
			return currentPlayer.getId() == white.getId() ? black : white;
		}
		return null;
	}

	public boolean isEnded() {
		// TODO: FIXME: TODO: FIXME

		if (lastMoveResult == ResultEnum.ABSOLUTE_WIN) {
			return true;
		}

		for (Player player : players) {
			if (player.getFiguresFromBoad(board).size() == 0) {
				return true;
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

	public ResultEnum moveTo(Move move) {
		return moveFigure(move.getStart(), move.getEnd());
	}

	public ResultEnum moveFigure(Position from, Position to) {
		Figure figure = board.getFigure(from);

		// if (figure.getOwner() != null && isValidMove(from, to)) {
		if (figure.getType() != null && isValidMove(from, to)) {
			// Potential opponent figure
			Figure figure2 = board.getFigure(to);
			if (figure2.getType() != null) {
				// Ok its not empty -> Battle
				return battle(from, to, figure, figure2);
			} else {
				_move(from, to, figure);
			}
			// Valid move; Was (battle || empty space) there
			return ResultEnum.EMPTY;
		} else {
			log.debug("*** Totally invalid move, cannot move there");
			// Totally invalid move; Cannot move there
			return null;
		}
	}

	private ResultEnum battle(Position from, Position to, Figure figureFrom,
			Figure figureTo) {
		ResultEnum resultOfBattle = battle(figureFrom, figureTo);
		if (resultOfBattle == ResultEnum.WIN) {
			// Logs here
			System.err.println("* WIN: Updating position");
			_move(from, to, figureFrom);
			figureFrom.setShownToEnemy(true);

		} else if (resultOfBattle == ResultEnum.LOOSE) {
			// Logs here
			System.err.println("* LOOSE: Removing figure.");
			_remove(from);
			figureTo.setShownToEnemy(true);
		} else if (resultOfBattle == ResultEnum.DRAW) {
			//
		}
		return resultOfBattle;
	}

	private void _remove(Position from) {
		board.putFigure(from, new Figure());
	}

	private void _move(Position from, Position to, Figure figure) {
		// "* Moving to empty cell"
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

	public Player getWhite() {
		return white;
	}

	public void setWhite(Player white) {
		white.setType(PlayerType.WHITE);
		this.white = white;
	}

	public Player getBlack() {
		return black;
	}

	public void setBlack(Player black) {
		black.setType(PlayerType.BLACK);
		this.black = black;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean canResolveDraw() {
		return white.getDrawChoice() != null && black.getDrawChoice() != null;
	}

	public ResultEnum resolveDraw() {
		Player opponent = getOpponentOf(currentPlayer);

		Position start = lastMove.getStart();
		Figure figure = board.getFigure(start);
		log.info("PLAYER ID: " + opponent.getId());
		log.info("CHANGED FIGURE TYPE FROM: " + figure.getType() + " TO "
				+ opponent.getDrawChoice());
		figure.setType(getOpponentOf(currentPlayer).getDrawChoice());

		Position end = lastMove.getEnd();
		figure = board.getFigure(end);
		log.info("PLAYER ID: " + currentPlayer.getId());
		log.info("CHANGED FIGURE TYPE FROM: " + figure.getType() + " TO "
				+ currentPlayer.getDrawChoice());

		figure.setType(currentPlayer.getDrawChoice());

		return this.moveTo(lastMove);
	}

	public Player getOpponentOf(Player player) {
		return player.getId() == white.getId() ? black : white;

	}

	public void resetPlayerChoices() {
		white.setDrawChoice(null);
		black.setDrawChoice(null);
	}
}
