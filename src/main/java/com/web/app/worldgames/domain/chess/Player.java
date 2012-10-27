package com.web.app.worldgames.domain.chess;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Player {
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

	public Set<Entry<Position,Figure>> getFiguresFromBoad(Board board) {
		Set<Entry<Position,Figure>> allFigures = board.getAllFigures();
		Set<Entry<Position,Figure>> ownerFigures = new HashSet<Entry<Position,Figure>>();
		for (Entry<Position, Figure> entry : allFigures) {
			if (entry.getValue().getOwner().equals(this)) {
				ownerFigures.add(entry);
			}
		}
		return ownerFigures;
	}
	
	public Position makePromt() {
		
		return new Position(1,2);
		
	}
	
}
