package com.web.app.worldgames.domain.chess;

public enum FigureTypesEnum {
	ROCK(0, "R"), PAPER(1, "P"), SCISSORS(2, "S"), TRAP(3, "T"), FLAG(4, "F");

	private int id;
	private String view;

	private FigureTypesEnum(int id, String view) {
		this.id = id;
		this.view = view;
	}

	// Silent, its complete
	@SuppressWarnings("incomplete-switch")
	public BattleResultsEnum beat(FigureTypesEnum that) {
		if (that == TRAP) {
			return BattleResultsEnum.LOOSE;
		} 

		if (that == FLAG) {
			return BattleResultsEnum.ABSOLUTE_WIN;
		}
		
		
		switch (this) {
		case PAPER: {
			switch (that) {
			case PAPER:
				return BattleResultsEnum.DRAW;
			case ROCK:
				return BattleResultsEnum.WIN;
			case SCISSORS:
				return BattleResultsEnum.LOOSE;
			}
		}
		case ROCK:
			switch (that) {
			case PAPER:
				return BattleResultsEnum.LOOSE;
			case ROCK:
				return BattleResultsEnum.DRAW;
			case SCISSORS:
				return BattleResultsEnum.WIN;
			}
		case SCISSORS:
			switch (that) {
			case PAPER:
				return BattleResultsEnum.WIN;
			case ROCK:
				return BattleResultsEnum.LOOSE;
			case SCISSORS:
				return BattleResultsEnum.DRAW;
			}
		}
		System.err.println("It cannot be, argument out of range.");
		return null;
	}

	public int getId() {
		return id;
	}

	public static FigureTypesEnum getTypeById(int typeId) {
		// TODO: add types
		return typeId == 0 ? FigureTypesEnum.ROCK
				: (typeId == 1 ? FigureTypesEnum.PAPER
						: (typeId == 2 ? FigureTypesEnum.SCISSORS 
								: null));
	}

	public String getView() {
		return view;
	}

	@Override
	public String toString() {
		return view;
	}
}
