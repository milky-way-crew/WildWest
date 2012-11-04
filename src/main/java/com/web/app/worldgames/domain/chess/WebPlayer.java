package com.web.app.worldgames.domain.chess;

import com.web.app.worldgames.domain.User;

public class WebPlayer extends Player {

	public WebPlayer(User user) {
		this.setId(user.getId());
		this.setNick(user.getNickname());
	}
	
	@Override
	public Move askForNextMove() {
		// TODO Auto-generated method stub
		return null;
	}

}
