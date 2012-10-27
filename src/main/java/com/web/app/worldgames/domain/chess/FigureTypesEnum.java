package com.web.app.worldgames.domain.chess;

public enum FigureTypesEnum {
	ROCK, PAPER, SCISSORS;

	public ResultEnum beat(FigureTypesEnum that) {
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
}
