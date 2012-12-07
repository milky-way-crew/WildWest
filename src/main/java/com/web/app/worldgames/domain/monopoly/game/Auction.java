package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Auction implements Runnable {
	private static final Logger log = Logger.getLogger(Auction.class);
	MonopolyManager manager;
	// SellableCard card = (SellableCard) CardFactory.chooseCard(player);
	SellableCard card = null;

	public Auction(MonopolyManager manager, SellableCard card) {
		this.manager = manager;
		this.card = card;
	}

	@Override
	public void run() {
		Map<String, Object> secondsLeft = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> responseAuction = new HashMap<String, Object>();
		int seconds = 60;
		boolean isEnd = false;
		while (seconds >= 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
				secondsLeft.put("type", ButtonsLabel.AUCTION);
				secondsLeft.put("seconds_left", seconds--);
				manager.broadcast(secondsLeft);
				if (seconds == 0) {
					isEnd = true;
					buttons.put(ButtonsLabel.BUY, false);
					buttons.putAll(ButtonsAction.buttonsAction(manager.getAuctionWinner()));
					log.info(":::::::::: second ::::: " + seconds);
					card.auctionCityOrRail(manager.getAuctionWinner(),manager.auctionCreator(),
							manager.getMaxAuctionPrice());
					manager.setAuctionPrice(0);
					log.info(":::::::::: auction price ::::: "
							+ manager.getAuctionPrice());
					manager.setMaxAuctionPrice(0);
					log.info(":::::::::: max auction price::::: "
							+ manager.getMaxAuctionPrice());
					responseAuction.put("type", ButtonsLabel.AUCTION);
					responseAuction.put("auction_status", isEnd);
					responseAuction.put("auction_money",
							manager.getMaxAuctionPrice());
					responseAuction.put("auction_creator", manager.auctionCreator());
					responseAuction.put("auction_creator_money", manager.auctionCreator().getMoney());
					response.put("type", ButtonsLabel.BUY);
					response.put("player", manager.getAuctionWinner()
							.getColor());
					response.put("player_money", manager.getAuctionWinner()
							.getMoney());
					buttons.putAll(ButtonsAction.buttonsAction(manager.getAuctionWinner()));
					state.put("buttons",buttons);
					response.put("game_state", state);
					log.info(":::::::::: response ::::: " + response);
					manager.broadcast(response);
					manager.broadcast(responseAuction);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
