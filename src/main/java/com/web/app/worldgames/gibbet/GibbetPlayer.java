package com.web.app.worldgames.gibbet;


import com.web.app.worldgames.domain.User;



public class GibbetPlayer {
	private int id;
	private String nick;
	private GibbetPlayerTypesEnum type;
	private boolean isReady = false;
	private String opponent; 
	private String myword;
	
	
	public GibbetPlayer(User user) {
		this(user.getId(), user.getNickname(), null, null,null);
	}


	public GibbetPlayer(int id, String nick, GibbetPlayerTypesEnum type, String opponent, String myword) {
		super();
		this.id = id;
		this.nick = nick;
		this.type = type;
		this.opponent = opponent;
		this.myword = myword;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setMyWord(String myword) {
		this.myword = myword;
	}
	
	public String getMyWord() {
		return myword;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public GibbetPlayerTypesEnum getType() {
		return type;
	}

	public void setType(GibbetPlayerTypesEnum type) {
		this.type = type;
	}
	
	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	

	
}
