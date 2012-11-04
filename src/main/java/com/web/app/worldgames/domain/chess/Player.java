package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class Player {
	private int id;
	private String nick;
	private PlayerType type;
	private Deque<GameAction> notifiers = new LinkedList<GameAction>();

	public void notifyAbout(GameAction action) {
		notifiers.addLast(action);
	}

	public GameAction popAction() {
		return notifiers.pollFirst();
	}

	private FigureTypesEnum drawChoice = null;	
	
	public void setDrawChoice(FigureTypesEnum drawChoice) {
		this.drawChoice = drawChoice;
	}

	public FigureTypesEnum getDrawChoice() {
		return this.drawChoice;
	}


	public Player() {
	}

	public Player(int id, String nick, PlayerType type) {
		super();
		this.id = id;
		this.nick = nick;
		this.type = type;
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
			if (figure.getOwner() != null && figure.getOwner().equals(this.getType())) {
				ownerFigures.add(figure);
			}
		}
		return ownerFigures;
	}
	
	public abstract Move askForNextMove();

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}
}
