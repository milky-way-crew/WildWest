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

	public static Move valueOf(String coords) {
		// FIXME: Add regex check [0-9]{2}.[0-9]{2}
		String[] split = coords.split("[^0-9]");
		return new Move(Utils.stringToPosition(split[0]), Utils.stringToPosition(split[1]));
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
