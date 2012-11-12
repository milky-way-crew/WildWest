package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayersActivity;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.ChanseCard;
import com.web.app.worldgames.domain.monopoly.card.CommunityChestCard;
import com.web.app.worldgames.domain.monopoly.card.FreeStation;
import com.web.app.worldgames.domain.monopoly.card.GoCard;
import com.web.app.worldgames.domain.monopoly.card.GoToJailCard;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.domain.monopoly.card.StartCard;
import com.web.app.worldgames.domain.monopoly.card.TaxCard;

public class GameManagerService {
	private final static Logger LOG = Logger
			.getLogger(GameManagerService.class);
	// Game game = new Game();
	static Map<String, Object> messageFromPlayer = new HashMap<String, Object>();

	// @Override
	public Map<String, Object> sendMessageFromPlayer(String type, Player player) {
		Map<String, Object> message = new HashMap<String, Object>();
		Map<Object, Object> result = new HashMap<Object, Object>();
		// ObjectMapper mapper = new ObjectMapper();
		CardFactory cardFactory = new CardFactory();
		Cell cell = cardFactory.chooseCard(player);
		SellableCard card = (SellableCard) cell;
		if (type.toLowerCase().trim().equals("start")) {
			StartCard startCard = new StartCard();
			startCard.effectOnPlayer(player);
			message.put("started", player);
		}
		if (type.toLowerCase().trim().equals("roll")) {
			player.rollDicesAndMove();
			result.put("dice1 " + player.getDiceOne(),
					"dice2 " + player.getDiceTwo());
			message.put("rolled", result);
		}
		if (type.toLowerCase().trim().equals("buy")) {
			card.buyCityOrRail(card, player);
			result.put(cell, player);
			message.put("buy", result);
		}
		if (type.toLowerCase().trim().equals("mortage")) {
			result.put(cell, player);
			player.mortageAction(player);
			message.put("mortaged", result);

		}
		if (type.toLowerCase().trim().equals("unmortage")) {
			result.put(cell, player);
			player.unMortageAction(player);
			message.put("unmortaged", result);

		}
		if (type.toLowerCase().trim().equals("sale")) {
			PlayersActivity.saleAction(player);
			result.put(cell, player);
			message.put("sale", result);

		}
		if (type.toLowerCase().trim().equals("pay_ransom")) {
			((JailCard) cell).payRansom(player);
			result.put(cell, player);
			message.put("pay_ransom", result);

		}
		return message;
	}

	public Map<String, Object> sendMessageToPlayer(
			Map<String, Object> response, Player player) {
		Game game = new Game();
		Cell cell = null;
		CardFactory cardFactory = new CardFactory();
		if (!game.isStarted()) {
			if (game.isReadyToStart()) {
				game.start();
			}
		} else if (game.isStarted() && !game.isEnd()) {
			if (response.containsKey("rolled")) {
				cell = cardFactory.chooseCard(player);

			} else if (response.containsKey("")) {

			}
		}
		return response;
	}

	public void action(Cell cell, Player player) {
		if (cell instanceof SellableCard) {

		} else {
			String message;
			if (cell instanceof GoCard) {
				LOG.debug("You stay at GoCard");
				cell.effectOnPlayer(player);
			} else if (cell instanceof TaxCard) {
				if (((TaxCard) cell).canPayTax(player)) {
					LOG.debug("You stay TaxCard");
					cell.effectOnPlayer(player);
					message = player.getName() + " pay tax";
				} else {
					LOG.debug("You havent money");
					message = "You haven't money. Mortage your property";
				}
			} else if (cell instanceof JailCard) {
				LOG.debug("You are in jail");
				if (player.isHasFreeCard()) {
					cell.effectOnPlayer(player);
					LOG.debug("You used a card");
					message = "You use card. You are giong from jail";
				} else {
					cell.effectOnPlayer(player);
					if (((JailCard) cell).canPayRansom(player)) {
						LOG.debug("You pay ransom");

						// ((JailCard) cell).payRansom(player);
						message = "You are giong from jail";
					} else {

					}
				}
			} else if (cell instanceof FreeStation) {
				LOG.debug("You stay at FreeStation");
				cell.effectOnPlayer(player);
				message = "Take a rest";

			} else if (cell instanceof GoToJailCard) {
				LOG.debug("You stay at GoToGail");
				cell.effectOnPlayer(player);
				message = "You are in jail";

			} else if (cell instanceof ChanseCard) {

			} else if (cell instanceof CommunityChestCard) {

			}
		}
	}
}
