package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Auction implements Runnable {
	private static final Logger log = Logger.getLogger(Auction.class);
	MonopolyManager manager;
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
		int seconds = 20;
		boolean isEnd = false;
		while (seconds >= 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
				secondsLeft.put("type", ButtonsLabel.AUCTION);
				secondsLeft.put("seconds_left", seconds--);
				manager.broadcast(secondsLeft);
				if (seconds == 0) {
					isEnd = true;
					log.info("max__________"+ manager.getMaxAuctionPrice());
					if (manager.getMaxAuctionPrice() > 0) {
						buttons.put(ButtonsLabel.BUY, false);
						buttons.putAll(ButtonsAction.buttonsAction(manager
								.getAuctionWinner()));
						card.auctionCityOrRail(manager.getAuctionWinner(),
								manager.auctionCreator(),
								manager.getMaxAuctionPrice());
						manager.setAuctionPrice(0);
						manager.setMaxAuctionPrice(0);
						log.info(":::::::::: auction price ::::: "
								+ manager.getAuctionPrice());
						log.info(":::::::::: max auction price::::: "
								+ manager.getMaxAuctionPrice());
						responseAuction.put("type", ButtonsLabel.AUCTION);
						responseAuction.put("auction_status", isEnd);
						responseAuction.put("auction_money",
								manager.getMaxAuctionPrice());
						responseAuction.put("auction_creator",
								manager.auctionCreator().getColor());
						responseAuction.put("auction_creator_money", manager
								.auctionCreator().getMoney());
						response.put("type", ButtonsLabel.BUY);
						response.put("player", manager.getAuctionWinner()
								.getColor());
						response.put("player_money", manager.getAuctionWinner()
								.getMoney());
						state.put("buttons", buttons);
						response.put("game_state", state);
						log.info(":::::::::: response ::::: " + response);
						manager.broadcast(response);
						manager.broadcast(responseAuction);
						for (Player player : manager.getMonopolyGame()
								.getAllPlayers()) {
							player.setAuctionCreator(false);
							player.setInAuction(false);
							player.setAuctionRates(0);
							player.setCanCreateAuction(false);
							log.info("Player info::::: "
									+ player.getAuctionRates() + " "
									+ player.isInAuction() + ":"
									+ player.isAuctionCreator());
						}
					} else if (manager.getMaxAuctionPrice() == 0) {
//						buttons.put(ButtonsLabel.BUY, manager.auctionCreator().canBuy(
//								(SellableCard) CardFactory.chooseCard(manager
//										.auctionCreator())));
//						state.put("buttons", buttons);
//						response.put("game_state", state);
//						manager.broadcast(response);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
