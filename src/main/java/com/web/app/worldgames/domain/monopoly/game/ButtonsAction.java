package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.JailCard;

public class ButtonsAction {
	static Map<String, Object> buttons = new HashMap<String, Object>();
public static Map<String, Object> buttonsAction(Player player){
	if(player.isInJail()){
		buttons.put(ButtonsLabel.PAY, ((JailCard) CardFactory.chooseCard(player)).canPayRansom(player));
	}
	buttons.put(ButtonsLabel.BUILD, player.canBuild());
	buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
	buttons.put(ButtonsLabel.ROLL, player.canRollDices());
	buttons.put(ButtonsLabel.SELL, player.canSell());
	buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
	return buttons;
}
}
