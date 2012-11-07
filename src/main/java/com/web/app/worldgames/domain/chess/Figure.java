package com.web.app.worldgames.domain.chess;

public class Figure {
	public static final Figure EMPTY = new Figure();
	private FigureTypesEnum type = null;
	private Position position = null;
	private PlayerType owner = null;
	private boolean isShownToEnemy = false;
	
	public boolean isShownToEnemy() {
		return isShownToEnemy;
	}

	public void setShownToEnemy(boolean isShownToEnemy) {
		this.isShownToEnemy = isShownToEnemy;
	}
	public Figure(FigureTypesEnum type, Position position, PlayerType owner) {
		super();
		this.type = type;
		this.position = position;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Figure [type=" + type + ", position=" + position + ", owner="
				+ owner + "]";
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

	public PlayerType getOwner() {
		return owner;
	}

	public void setOwner(PlayerType owner) {
		this.owner = owner;
	}
}
