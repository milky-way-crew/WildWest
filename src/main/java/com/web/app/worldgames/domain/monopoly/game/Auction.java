package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Auction implements Runnable {
	MonopolyManager manager;
	SellableCard card = (SellableCard) CardFactory.chooseCard(manager
			.getAuctionWinner());

	public Auction(MonopolyManager manager) {
		this.manager = manager;
	}

	@Override
	public void run() {
		Map<String, Object> secondsLeft = new HashMap<String, Object>();
		int seconds = 60;

		while (seconds >= 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
				seconds = seconds--;
				secondsLeft.put("type", ButtonsLabel.AUCTION);
				secondsLeft.put("seconds_left", seconds);
				manager.broadcast(secondsLeft);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (seconds == 0) {
			card.auctionCityOrRail(manager.getAuctionWinner(),
					manager.getMaxAuctionPrice());
		}
	}

}
