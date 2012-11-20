package com.web.app.worldgames.domain.chess;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.web.app.worldgames.domain.User;

public class ChessPlayer {
	private int id;
	private String nick;
	private ChessPlayerTypesEnum type;
	private Deque<GameAction> notifiers = new LinkedList<GameAction>();
	private List<String> mail = new ArrayList<String>();
	private boolean isReady = false;
	
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


	public ChessPlayer(User user) {
		this(user.getId(), user.getNickname(), null);
	}

	public ChessPlayer(int id, String nick, ChessPlayerTypesEnum type) {
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
	
	public ChessPlayerTypesEnum getType() {
		return type;
	}

	public void setType(ChessPlayerTypesEnum type) {
		this.type = type;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public List<String> getMail() {
		return mail;
	}

	public void setMail(List<String> mail) {
		this.mail = mail;
	}
}
