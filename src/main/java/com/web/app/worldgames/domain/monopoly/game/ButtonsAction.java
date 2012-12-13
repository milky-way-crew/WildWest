package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;

public class ButtonsAction {
	static Map<String, Object> buttons = new HashMap<String, Object>();

	/*
	 * Method define available buttons for player
	 */
	public static Map<String, Object> buttonsAction(Player player) {
		buttons.put(ButtonsLabel.BUILD, player.canBuild());
		buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
		buttons.put(ButtonsLabel.SELL, player.canSell());
		buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
		return buttons;
	}
}
