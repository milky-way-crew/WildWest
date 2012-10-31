package com.web.app.worldgames.domain.chess;

public class Figure {
	public static final Figure EMPTY = new Figure();
	private FigureTypesEnum type;
	private Position position;
	private Player owner;
	
	public Figure(FigureTypesEnum type, Position position, Player owner) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figure other = (Figure) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
