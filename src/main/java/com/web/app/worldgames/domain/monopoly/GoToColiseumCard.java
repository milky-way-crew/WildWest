package com.web.app.worldgames.domain.monopoly;

public class GoToColiseumCard extends Cell {

	@Override
	void effectOnPlayer(Player player) {
		player.setPosition(CellPositions.GO_TO_COLISEUM);
	}

}
