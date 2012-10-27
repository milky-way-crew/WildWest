package com.web.app.worldgames.domain.chess;

public class Figure {
	private FigureTypesEnum type;
	private Position position;
	private Player owner;

	public Figure(FigureTypesEnum type, Position position, Player owner) {
		super();
		this.type = type;
		this.position = position;
		this.owner = owner;
	}

	public Figure() {
	}

	public FigureTypesEnum getType() {
		return type;
	}

	public void setType(FigureTypesEnum type) {
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
