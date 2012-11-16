package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;

public class GoToJailCard extends Cell {
//	private int movePosition;
//
//	public int getMovePosition() {
//		return movePosition;
//	}
//
//	public void setMovePosition(int movePosition) {
//		this.movePosition = movePosition;
//	}

	@Override
	public void effectOnPlayer(Player player) {
		player.setPosition(CellPositions.JAIL);
		//setMovePosition(CellPositions.JAIL);
	}

	@Override
	public String info() {
		return "You are going to jail. ";
	}

}
