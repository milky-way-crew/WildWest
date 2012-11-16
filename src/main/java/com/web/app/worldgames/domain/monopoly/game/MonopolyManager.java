package com.web.app.worldgames.domain.monopoly.game;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.domain.monopoly.card.TaxCard;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	// private static final String ROLL = "roll";
	private static final String START = "start";
	private static final String READY = "ready";
	private static final String TURN = "turn";
	private static final String ROLL_EVENTS = "";
	//private static final String TURN = "turn";
	// private static final String DONE = "done";
	// private static final String BUY = "buy";
	private Game monopolyGame;
	private User creator;

	public MonopolyManager(Game monopolyGame) {
		this.setMonopolyGame(monopolyGame);
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
		log.info("[CREATOR] " + creator);
		getMonopolyGame().addPlayers(creator);
		// monopolyGame.setCurrentPlayer(new Player(creator,
		// CellPositions.START, CardPrices.START_MONEY,
		// "RED"));
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {

		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);

		Map<String, Object> response = new HashMap<String, Object>();
		if ($(type).equals("init")) {
			onInit(idPlayer, response);
		}
		if ($(type).equals(READY)) {
			onReady(idPlayer, response);
		}
		if ($(type).equals(START)) {
			onStart(response);
		}
		if (getMonopolyGame().isStarted()) {
			if ($(type).equals(ButtonsLabel.ROLL)) {
				onRoll(idPlayer, response);
			}
			if ($(type).equals(ButtonsLabel.BUY)) {
				onBuy(idPlayer, type, response);
			}
			if ($(type).equals(ButtonsLabel.PAY)) {
				onPay(idPlayer, type, response);
			}
			if ($(type).equals(ButtonsLabel.MORTAGE)) {
				onMortage(response);
			}
			if ($(type).equals(ButtonsLabel.OK)) {
				onOk(idPlayer, response);
			}
			if ($(type).equals(ButtonsLabel.DONE)) {
				onDone(type, response);
			}
		}
		// socketTransport.sendMessage(idPlayer, response);

		// socketTransport.sendMessage(idPlayer, response);
		// not sure if we need this
		return response;
	}

	private void onDone(String type, Map<String, Object> response) {
		Map<String, Object> turn = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		currentPlayer.setRolled(false);
		// if (currentPlayer.getId() == idPlayer) {
		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
		log.info("[CURRENT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		getMonopolyGame().setCurrentPlayer(getMonopolyGame().getNextPlayer());
		log.info("[NEXT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		currentPlayer = getMonopolyGame().getCurrentPlayer();
		log.info("[Player: ]" + currentPlayer.getName() + ":"
				+ currentPlayer.getColor() + " can roll dice");
		// response.put("type", ButtonsLabel.DONE);
		// response.put("player", currentPlayer.getColor());
		turn.put("type", TURN);
		turn.put("player", currentPlayer.getColor());
		// }
		broadcast(turn);
	}

	private void onOk(int idPlayer, Map<String, Object> response) {
		// Player currentPlayer = getPlayerById(idPlayer);
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.getId() == idPlayer) {
			Cell card = CardFactory.chooseCard(currentPlayer);
			card.effectOnPlayer(currentPlayer);
			response.put("type", ButtonsLabel.OK);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
		broadcast(response);
	}

	private void onMortage(Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		SellableCard card = (SellableCard) CardFactory
				.chooseCard(currentPlayer);
		card.mortage(currentPlayer);
		response.put("type", ButtonsLabel.MORTAGE);
		response.put("player", currentPlayer.getColor());
		response.put("player_money", currentPlayer.getMoney());
		broadcast(response);
	}

	private void onPay(int idPlayer, String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.getId() == idPlayer) {
			log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
			Cell cell = CardFactory.chooseCard(currentPlayer);
			if (cell instanceof SellableCard) {
				SellableCard card = (SellableCard) cell;
				card.payRentToOwner(currentPlayer, card.getOwner(),
						card.getRent(currentPlayer, card.getOwner()));
				log.info("[Player: ]" + card.getOwner() + " get rent");
				log.info("[Player: ]" + card.getOwner() + " money"
						+ card.getOwner().getMoney());
			} else if (cell instanceof JailCard) {
				JailCard card = (JailCard) cell;
				card.payRansom(currentPlayer);
				log.info("[Player: ]" + " pay ransom"
						+ currentPlayer.getMoney());
			}
			response.put("type", ButtonsLabel.PAY);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
		broadcast(response);
	}

	private void onBuy(int idPlayer, String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.getId() == idPlayer) {
			log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
			// Player currentPlayer = getPlayerById(idPlayer);
			// Player currentPlayer =
			// getMonopolyGame().getCurrentPlayer();
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			if (card.canBuy(currentPlayer)) {

				log.info("[Cell: ]" + card.info());
				card.buyCityOrRail(currentPlayer);
				log.info("[Player: ]" + currentPlayer.getColor()
						+ " buy this cell");
				log.info("[Player: ]" + currentPlayer.getColor() + " money: "
						+ currentPlayer.getMoney());
				response.put("type", ButtonsLabel.BUY);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
			}
		}
		broadcast(response);
	}

	private void onRoll(int idPlayer, Map<String, Object> response) {
		// log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.canRollDices()) {

			if (currentPlayer.getId() == idPlayer) {
				if (currentPlayer.isInJail()) {
					int points = currentPlayer.rollDicesAndWait();
					log.info("[Player: " + currentPlayer.getColor()
							+ "in jail]" + currentPlayer.isInJail());
					JailCard card = new JailCard();
					response.put("type", ButtonsLabel.ROLL);
					response.put("event", "city");
					response.put("dice1", currentPlayer.getDiceOne());
					response.put("dice2", currentPlayer.getDiceTwo());
					response.put("buttons", GameAction.action(
							CardFactory.chooseCard(currentPlayer),
							currentPlayer));
					response.put("cell", CardFactory.chooseCard(currentPlayer));
					if (card.rollAndWait(currentPlayer, points)) {
						response.put("Move to:", points);
					}
					response.put("inJail", currentPlayer.isInJail());
					response.put("player", currentPlayer.getColor());
				} else {
					response.put("was", currentPlayer.getPosition());
					currentPlayer.rollDicesAndMove();
					log.info("[Player: ]" + currentPlayer.getName() + " : "
							+ currentPlayer.getColor());
					log.info("[Player moving to position: ]"
							+ currentPlayer.getPosition());
					log.info("[Dice1: ]" + currentPlayer.getDiceOne());
					log.info("[Dice2: ]" + currentPlayer.getDiceTwo());
					log.info("[Cell: ]"
							+ CardFactory.chooseCard(currentPlayer).info());
					response.put("type", ButtonsLabel.ROLL);
					response.put("player", currentPlayer.getColor());
					response.put("dice1", currentPlayer.getDiceOne());
					response.put("dice2", currentPlayer.getDiceTwo());
					response.put("money", currentPlayer.getMoney());
					response.put("buttons", GameAction.action(
							CardFactory.chooseCard(currentPlayer),
							currentPlayer));
					response.put("cell", CardFactory.chooseCard(currentPlayer));
				}
			}
		}
		broadcast(response);
	}

	private void onStart(Map<String, Object> response) {
		Map<String, Object> turn = null;
		response.put("type", "logic");
		if (getMonopolyGame().isReadyToStart()
				&& !getMonopolyGame().isStarted()) {
			getMonopolyGame().start();
			log.info("[GAME IS STARTED] " + getMonopolyGame().isStarted());
			response.put("game_status", "start");
			turn = new HashMap<String, Object>();
			Player currentPlayer = getMonopolyGame().getCurrentPlayer();
			turn.put("type", TURN);
			turn.put("player", currentPlayer.getColor());

		} else {
			response.put("game_status", "waiting");
		}
		broadcast(response);
		if (turn != null) {
			broadcast(turn);
		}
	}

	private void onReady(int idPlayer, Map<String, Object> response) {
		Player currentPlayer = getPlayerById(idPlayer);
		currentPlayer.setReadyToStart(true);
		log.info("[PLAYER IS READY TO START: ]" + currentPlayer.getColor()
				+ " : " + currentPlayer.isReadyToStart());
		response.put("type", READY);
		response.put("player", currentPlayer.getColor());
		response.put("ready", currentPlayer.isReadyToStart());
		broadcast(response);
	}

	private void onInit(int idPlayer, Map<String, Object> response) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		response.put("type", "init");
		response.put("color", getPlayerById(idPlayer).getColor());
		response.put("money", getPlayerById(idPlayer).getMoney());
		response.put("isCreator", getCreator().getId() == idPlayer);
		transport.sendMessage(idPlayer, response);
		Map<String, Object> welcome = new HashMap<String, Object>();
		welcome.put("type", "chat");
		welcome.put("message", "Welcome " + getPlayerById(idPlayer).getName());
		broadcast(welcome);
	}

	public void broadcast(Map<String, ? extends Object> message) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		for (Player player : getMonopolyGame().getAllPlayers()) {
			transport.sendMessage(player.getId(), message);
		}
	}

	/**
	 * Pretify string
	 * 
	 * @param s
	 * @return
	 */
	private String $(String s) {
		return s.toLowerCase().trim();
	}

	public Player getPlayerById(int idPlayer) {
		for (Player players : getMonopolyGame().getAllPlayers()) {
			if (players.getId() == idPlayer) {
				return players;
			}
		}
		return null;
	}

	public void addClient(User client) {
		log.info("[ADD NEW PLAYER] " + client);
		getMonopolyGame().addPlayers(client);
	}

	public Game getMonopolyGame() {
		return monopolyGame;
	}

	public void setMonopolyGame(Game monopolyGame) {
		this.monopolyGame = monopolyGame;
	}
}
