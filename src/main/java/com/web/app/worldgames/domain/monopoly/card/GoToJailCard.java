package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.CellPositions;
import com.web.app.worldgames.domain.monopoly.Player;

public class GoToJailCard extends Cell {

	@Override
	public void effectOnPlayer(Player player) {
		player.setPosition(CellPositions.JAIL);
		JailCard jailCard = new JailCard();
		jailCard.effectOnPlayer(player);
	}

	@Override
	public String info() {
		return "You are going to jail. ";
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
