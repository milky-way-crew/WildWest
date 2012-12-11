package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class ButtonsAction {
	static Map<String, Object> buttons = new HashMap<String, Object>();

	public static Map<String, Object> buttonsAction(Player player) {
		if (player.isInJail()) {
			buttons.put(ButtonsLabel.PAY, ((JailCard) CardFactory
					.chooseCard(player)).canPayRansom(player));
		}
//		if (CardFactory.chooseCard(player) instanceof SellableCard) {
//			buttons.put(ButtonsLabel.BUY, player
//					.canBuy((SellableCard) CardFactory.chooseCard(player)));
//		}
		buttons.put(ButtonsLabel.BUILD, player.canBuild());
		buttons.put(ButtonsLabel.MORTAGE, player.canMortage());
		if (player.isInAuction() && player.isAuctionCreator()) {
			buttons.put(ButtonsLabel.ROLL, player.canRollDices());

		} else if (player.isInAuction()) {
			buttons.put(ButtonsLabel.ROLL, false);

		}else{
			buttons.put(ButtonsLabel.ROLL, player.canRollDices());
		}
		buttons.put(ButtonsLabel.SELL, player.canSell());
		buttons.put(ButtonsLabel.UNMORTAGE, player.canUnmortage());
		return buttons;
	}
}
