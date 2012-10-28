package com.web.app.worldgames.domain.chess;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Position {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	private Integer x;
	private Integer y;

	public Position() {
	}

	@Override
	public String toString() {
		// Like a json 
		return "{ \"x\" :" + x + ", \"y\":" + y + "}";
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Position(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}


}