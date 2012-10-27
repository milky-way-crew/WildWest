package com.web.app.worldgames.domain.chess;


public class TrickyChessGame {
	private static final int COUNT_PLAYERS = 2;

	private Board board = new Board();
	private Player[] players;
	
	public TrickyChessGame(Player player1, Player player2) {
		players = new Player[] { player1, player2 };
	}
	
	public void startGame() {
		Player next = getNextPlayer();
	}


	private int _cursor = 0;
	private Player getNextPlayer() {
		_cursor = (_cursor > players.length) ? _cursor - players.length : _cursor;
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
}
