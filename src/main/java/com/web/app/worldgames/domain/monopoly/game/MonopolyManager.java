package com.web.app.worldgames.domain.monopoly.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.ButtonsLabel;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	// private static final String ROLL = "roll";
	private static final String START = "start";
	private static final String READY = "ready";
	private static final String TURN = "turn";
	// private static final String ROLL_EVENTS = "";
	// private static final String TURN = "turn";
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
	}

	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {

		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
		log.info("DATA: " + data);
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
				onMortage(response, data);
			}
			if ($(type).equals(ButtonsLabel.UNMORTAGE)) {
				onUnMortage(response);
			}
			// if ($(type).equals(ButtonsLabel.OK)) {
			// onOk(idPlayer, response);
			// }
			if ($(type).equals(ButtonsLabel.DONE)) {
				onDone(type, response);
			}
			if ($(type).equals(ButtonsLabel.SELL)) {
				onSell(type, response);
			}
			// if ($(type).equals("ok")) {
			// onMortage(idPlayer, type, response);
			// }
		}
		return response;
	}

	// private void onMortageAction(int idPlayer, String type,
	// Map<String, Object> response) {
	// Player currentPlayer = getMonopolyGame().getCurrentPlayer();
	// // SellableCard card = (SellableCard) CardFactory
	// // .chooseCard1((Integer) response.get("position"));
	// SellableCard card = (SellableCard) currentPlayer
	// .cardByPosition((Integer) response.get("position"));
	// card.mortage(currentPlayer);
	// currentPlayer.getForMortage().remove(card);
	// currentPlayer.getForUnMortage().add(card);
	// broadcast(response);
	// }

	private void onSell(String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.canSell()) {
			response.put("type", ButtonsLabel.SELL);
			response.put("sell_list", currentPlayer);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
		broadcast(response);
	}

	private void onDone(String type, Map<String, Object> response) {
		System.out.println("DONE!!!!" + response);
		Map<String, Object> turn = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		currentPlayer.setRolled(false);
		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
		log.info("[CURRENT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		getMonopolyGame().setCurrentPlayer(getMonopolyGame().getNextPlayer());
		log.info("[NEXT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		currentPlayer = getMonopolyGame().getCurrentPlayer();
		log.info("[Player: ]" + currentPlayer.getName() + ":"
				+ currentPlayer.getColor() + " can roll dice");
		turn.put("type", TURN);
		turn.put("player", currentPlayer.getColor());
		// }
		broadcast(turn);
	}

	// private void onOk(int idPlayer, Map<String, Object> response) {
	// Player currentPlayer = getMonopolyGame().getCurrentPlayer();
	// if (currentPlayer.getId() == idPlayer) {
	// Cell card = CardFactory.chooseCard(currentPlayer);
	// card.effectOnPlayer(currentPlayer);
	// response.put("type", ButtonsLabel.OK);
	// response.put("player", currentPlayer.getColor());
	// response.put("player_money", currentPlayer.getMoney());
	// }
	// broadcast(response);
	// }

	private void onMortage(Map<String, Object> response, String data) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.canMortage()) {
			response.put("type", ButtonsLabel.MORTAGE);
			response.put("mortage_list",
					currentPlayer.getMortageAvaliable());
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
		JsonNode tree = null;
		try {
			tree = objectMapper.readTree(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode dataBlock = tree.path("data");

		if (dataBlock.has("position")) {
			SellableCard city = null;
			int pos = dataBlock.path("position").getIntValue();
			log.info("received position: " + pos);
			log.info("player money: " + currentPlayer.getMoney());
			city = currentPlayer.cardByPosition(pos);
			city.mortage(currentPlayer);
			log.info("mortage city: " + city.getName());
			currentPlayer.getForMortage().remove(city);
			currentPlayer.getForUnMortage().add(city);
			log.info("player money after mortage: " + currentPlayer.getMoney());
		}else {
			log.info("no position: ");
			log.info("List: ");
			
		}

		// if (response.get("position") != null) {
		// SellableCard card = (SellableCard) currentPlayer
		// .cardByPosition((Integer) response.get("position"));
		// card.mortage(currentPlayer);
		// currentPlayer.getForMortage().remove(card);
		// currentPlayer.getForUnMortage().add(card);
		// }
		broadcast(response);
	}

	private void onUnMortage(Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.canUnmortage()) {
			response.put("type", ButtonsLabel.UNMORTAGE);
			response.put("unmortage_list", currentPlayer.getForUnMortage());
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
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
		Map<String, Object> buttons = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		if (currentPlayer.canRollDices()) {
			if (currentPlayer.getId() == idPlayer) {
				if (currentPlayer.isInJail()) {
					// int points = currentPlayer.rollDicesAndWait();
					log.info("[Player: " + currentPlayer.getColor()
							+ "in jail]" + currentPlayer.isInJail());
					JailCard jail = new JailCard();
					response.put("type", ButtonsLabel.ROLL);
					response.put(
							"game_state",
							jail.rollAndWait(currentPlayer,
									currentPlayer.rollDicesAndWait()));
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
					response.put("dice1", currentPlayer.getDiceOne());
					response.put("dice2", currentPlayer.getDiceTwo());
					response.put("money", currentPlayer.getMoney());
					response.put("game_state", GameAction.action(
							CardFactory.chooseCard(currentPlayer),
							currentPlayer));
					response.put("cell", CardFactory.chooseCard(currentPlayer));
				}
			}
		} else {
			response.put("type", ButtonsLabel.ROLL);
			buttons.put(ButtonsLabel.DONE, true);
			response.put("game_state", buttons);
			currentPlayer.setLosser(true);
			leaveGame(currentPlayer);
			if (hasWinner()) {
				currentPlayer.setWinner(true);
				log.info("[WINNER ]:" + currentPlayer.getColor());
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
		for (Player player : monopolyGame.getAllLosers()) {

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

	public void deleteLoserPlayer(List<Player> players, List<Player> losers,
			Player player) {
		players.remove(player);
		losers.add(player);
	}

	// public void
	public void leaveGame(Player player) {
		if (player.isLosser()) {
			this.deleteLoserPlayer(monopolyGame.getAllPlayers(),
					monopolyGame.getAllLosers(), player);
			log.info("[PLAYER LEAVE THIS GAME: ]" + player.getColor());
			log.info("[PLAYER LIST ]" + monopolyGame.getAllPlayers());
			log.info("[LOSER LIST ]" + monopolyGame.getAllLosers());
			for (SellableCard card : player.playerProperty()) {
				card.setOwner(null);
				if (card.isMortage()) {
					card.setMortage(false);
				}
			}
		}
	}

	public boolean hasWinner() {
		if (monopolyGame.getAllPlayers().size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void endGame() {
		if (hasWinner()) {
			monopolyGame.setEnd(true);
		}
	}
}
