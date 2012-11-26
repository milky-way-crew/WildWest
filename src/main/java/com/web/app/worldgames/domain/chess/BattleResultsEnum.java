package com.web.app.worldgames.domain.chess;

public enum BattleResultsEnum {
	WIN(1), LOOSE(-1), ABSOLUTE_WIN(2), ABSOLUTE_LOOSE(-2), DRAW(0), EMPTY(-3);
	
	private int value;
	
	private BattleResultsEnum(int value) {
		this.value = value;
	}
  
	public int getValue() {
		return value;
	}
}
