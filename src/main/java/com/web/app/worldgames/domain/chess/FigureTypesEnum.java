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
	public ResultEnum beat(FigureTypesEnum that) {
		if (that == TRAP) {
			return ResultEnum.LOOSE;
		}
		
		if (that == FLAG) {
			return ResultEnum.WIN;
		}
		
		
		switch (this) {
		case PAPER: {
			switch (that) {
			case PAPER:
				return ResultEnum.DRAW;
			case ROCK:
				return ResultEnum.WIN;
			case SCISSORS:
				return ResultEnum.LOOSE;
			}
		}
		case ROCK:
			switch (that) {
			case PAPER:
				return ResultEnum.LOOSE;
			case ROCK:
				return ResultEnum.DRAW;
			case SCISSORS:
				return ResultEnum.WIN;
			}
		case SCISSORS:
			switch (that) {
			case PAPER:
				return ResultEnum.WIN;
			case ROCK:
				return ResultEnum.LOOSE;
			case SCISSORS:
				return ResultEnum.DRAW;
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
