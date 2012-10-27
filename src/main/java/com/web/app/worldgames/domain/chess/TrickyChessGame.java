package com.web.app.worldgames.domain.chess;

import java.util.Map;


public class TrickyChessGame {
	private static final int COUNT_PLAYERS = 2;

	private Board board = new Board();
	private Player[] players;
	
	public TrickyChessGame(Player player1, Player player2) {
		players = new Player[] { player1, player2 };
	}
	
	public void startGame() {
		while (!this.isEnded()) {
			Player nextPlayer = getNextPlayer();
			Move playerMove = nextPlayer.askForNextMove();
			ResultEnum resultOfBattle = updateBoard(playerMove);
			notifyPlayers(resultOfBattle);
			
		}
	}


	private void notifyPlayers(ResultEnum resultOfBattle) {
		// TODO Auto-generated method stub
		// TODO: Implement
	}

	private ResultEnum updateBoard(Move playerMove) {
		// TODO: Totally wrong.
		Map<Position, Figure> map = board.getBoard();
		Figure figurePlayer = map.remove(playerMove.getStart());
		Figure figureOponent = map.get(playerMove.getEnd());
		if (figureOponent.getType() == null) {
			// Empty cell
			return null;
		} else {
			// Returning result of battle
			return figurePlayer.getType().beat(figureOponent.getType());
		}
		
	}


	private int _cursor = 0;
	private Player getNextPlayer() {
		_cursor = (_cursor >= players.length) ? _cursor - players.length : _cursor;
		return players[_cursor++];
	}
	
	public boolean isEnded() {
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
}
