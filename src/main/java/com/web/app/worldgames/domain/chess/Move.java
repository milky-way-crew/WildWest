package com.web.app.worldgames.domain.chess;

public class Move {
	private Position start;
	private Position end;

	public Move() {
	}

	public Move(Position start, Position end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}

	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

}
