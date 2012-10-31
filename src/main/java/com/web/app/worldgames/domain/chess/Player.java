package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public abstract class Player {
	private int id;
	private String nick;
	
	public Player() {
	}

	public Player(int id, String nick) {
		super();
		this.id = id;
		this.nick = nick;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<Figure> getFiguresFromBoad(Board board) {
		ArrayList<Figure> ownerFigures = new ArrayList<Figure>();
		for (Figure figure : board.getBoard()) {
			if (figure.getOwner().equals(this)) {
				ownerFigures.add(figure);
			}
		}
		return ownerFigures;
	}
	
	public abstract Move askForNextMove();
}
