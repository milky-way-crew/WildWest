package com.web.app.worldgames.domain.chess;

public enum ResultEnum {
	WIN(1), LOOSE(-1), DRAW(0);
	
	private int value;
	
	private ResultEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
