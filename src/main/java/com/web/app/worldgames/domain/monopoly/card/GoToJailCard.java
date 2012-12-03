package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;

public class GoToJailCard extends Cell {

	@Override
	public void effectOnPlayer(Player player) {
		player.setPosition(CellPositions.JAIL);
		player.setMoney(player.getMoney() + 200);
		//CardFactory.chooseCard(player).effectOnPlayer(player);
	}

	@Override
	public String info() {
		return "You are going to jail. ";
	}

}
