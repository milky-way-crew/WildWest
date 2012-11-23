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
import com.web.app.worldgames.domain.monopoly.StartGame;
import com.web.app.worldgames.domain.monopoly.card.CardFactory;
import com.web.app.worldgames.domain.monopoly.card.Cell;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.JailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	private Game monopolyGame;
	private User creator;
	private int auctionStartPrice;
	private static final String ID_NODE = "id";

	public MonopolyManager(Game monopolyGame) {
		this.setMonopolyGame(monopolyGame);
	}

	public User getCreator() {
		return creator;
	}

	public int getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(int auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public void price() {

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
		if ($(type).equals(ButtonsLabel.READY)) {
			onReady(idPlayer, response);
		}
		if ($(type).equals(ButtonsLabel.START)) {
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
				onMortage(idPlayer, type, response, data);
			}
			if ($(type).equals(ButtonsLabel.UNMORTAGE)) {
				onUnMortage(idPlayer, type, response, data);
			}
			if ($(type).equals(ButtonsLabel.BUILD)) {
				onBuild(idPlayer, type, response, data);
			}
			if ($(type).equals(ButtonsLabel.DONE)) {
				onDone(type, response);
			}
			if ($(type).equals(ButtonsLabel.SELL)) {
				onSell(idPlayer, type, response, data);
			}
			if ($(type).equals(ButtonsLabel.AUCTION)) {
				onAuction(idPlayer, type, response, data);
			}
		}
		return response;
	}

	private void onAuction(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Player currentPlayer = getPlayerById(idPlayer);
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();
//		SellableCard card = null;
//		if (StartGame.boardCities.containsKey(currentPlayer.getPosition())) {
//			card = StartGame.boardCities.get(currentPlayer.getPosition());
//		} else if (StartGame.boardRails
//				.containsKey(currentPlayer.getPosition())) {
//			card = StartGame.boardRails.get(currentPlayer.getPosition());
//		}
		//setAuctionStartPrice(100);
		response.put("type", ButtonsLabel.AUCTION);
		response.put("player", currentPlayer.getColor());
		JsonNode tree = null;
		try {
			tree = objectMapper.readTree(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonNode dataBlock = tree.path("data");
		if (dataBlock.has("price")) {
			int price = dataBlock.path("price").getIntValue();
			if (price > monopolyGame.getAuctionPrice()) {
//				log.info("price"+price);
				monopolyGame.setAuctionPrice(price);
				log.info(" NOW PRICE======" + monopolyGame.getAuctionPrice());
			} else {
				monopolyGame.setAuctionPrice(monopolyGame.getAuctionPrice());
				log.info("PRICE IS LESS=========");
			}
		} else {
			log.info("no price: ");
		}
//		currentPlayer.addBuildAvailable();
		// buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
		// buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
		// buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
		// buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
		// state.put("buttons", buttons);
		// response.put("game_state", state);
		// }
		broadcast(response);
	}

	private void onBuild(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<Integer, String> buildings = currentPlayer.getBuildAvailable();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canBuild()) {
				response.put("type", ButtonsLabel.BUILD);
				response.put("player_money", currentPlayer.getMoney());
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
					CityCard city = null;
					int pos = dataBlock.path("position").getIntValue();
					log.info("received position: " + pos);
					log.info("player money: " + currentPlayer.getMoney());
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					city = (CityCard) currentPlayer.cardByPosition(pos);
					if ((city.getNumbersOfHouses() < 3 || !city.isHotel())
							|| !city.isMortage()) {
						city.build(currentPlayer);
						log.info("build process: " + city.getName());
						log.info("list for build: " + buildings);
						response.put("player_money", currentPlayer.getMoney());
					} else if (city.getNumbersOfHouses() == 3
							&& !city.isMortage()) {
						city.build(currentPlayer);
						log.info("HOTEL BUILT");
					}
				} else {
					log.info("no position: ");
					response.put("type", ButtonsLabel.BUILD);
					response.put("build_list",
							currentPlayer.addBuildAvailable());
					response.put("player", currentPlayer.getColor());
				}
				currentPlayer.addBuildAvailable();
				buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
				buttons.put(ButtonsLabel.UNMORTAGE,
						currentPlayer.canUnmortage());
				buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
				buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
				state.put("buttons", buttons);
				response.put("game_state", state);
			}
		}
		broadcast(response);
	}

	private void onSell(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canSell()) {
				response.put("type", ButtonsLabel.SELL);
				response.put("player_money", currentPlayer.getMoney());
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
					SellableCard card = null;
					int pos = dataBlock.path("position").getIntValue();
					log.info("received position: " + pos);
					log.info("list before sell: " + currentPlayer.getForSell());
					log.info("player money before sell: "
							+ currentPlayer.getMoney());
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					card = currentPlayer.cardByPosition(pos);
					card.sell(currentPlayer);
					currentPlayer.removeObj(currentPlayer.getProperty(), card);
					if (currentPlayer.getForMortage().contains(card)) {
						currentPlayer.removeObj(currentPlayer.getForMortage(),
								card);
					}
					if (currentPlayer.getForSell().contains(card)) {
						currentPlayer.removeObj(currentPlayer.getForSell(),
								card);
					}
					monopolyGame.removeFromActivBoard(card);
					log.info("sell city: " + card.getName());
					log.info("list after sell: " + currentPlayer.getForSell());
					log.info("player money after sell: "
							+ currentPlayer.getMoney());
					Cell cell = CardFactory.chooseCard(currentPlayer);
					log.info("CHECK card" + cell.getName());
					if (cell instanceof SellableCard) {
						if (((SellableCard) cell).getOwner() == null) {
							log.info("CHECK BUY TRUE/FALSE "
									+ ((SellableCard) cell)
											.canBuy(currentPlayer));
							buttons.put(ButtonsLabel.BUY,
									((SellableCard) cell).canBuy(currentPlayer));
						} else {
							buttons.put(ButtonsLabel.BUY, false);
						}
					}
					response.put("player_money", currentPlayer.getMoney());
				} else {
					log.info("no position: ");
					response.put("type", ButtonsLabel.SELL);
					response.put("sell_list", currentPlayer.getSellAvailable());
					response.put("player", currentPlayer.getColor());
				}
			}
			currentPlayer.addBuildAvailable();
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			state.put("buttons", buttons);
			response.put("game_state", state);
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
		turn.put("type", ButtonsLabel.TURN);
		turn.put("player", currentPlayer.getColor());
		broadcast(turn);
	}

	private void onMortage(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canMortage()) {
				response.put("type", "mortage");
				response.put("player_money", currentPlayer.getMoney());
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
					log.info("player money before: " + currentPlayer.getMoney());
					city = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					city.mortage(currentPlayer);
					currentPlayer
							.removeObj(currentPlayer.getForMortage(), city);
					log.info("list after mortage: "
							+ currentPlayer.getForMortage());
					currentPlayer.getForUnMortage().add(city);

					log.info("player money after mortage: "
							+ currentPlayer.getMoney());
					// response.put("mortage_list",
					// currentPlayer.getMortageAvailable());
					if (currentPlayer.listPropertyForSell().contains(city)) {
						currentPlayer.removeObj(
								currentPlayer.listPropertyForSell(), city);
					}
					Cell card = CardFactory.chooseCard(currentPlayer);
					log.info("CHECK card" + card.getName());
					if (card instanceof SellableCard) {
						if (((SellableCard) card).getOwner() == null) {
							log.info("CHECK BUY TRUE/FALSE"
									+ ((SellableCard) card)
											.canBuy(currentPlayer));
							buttons.put(ButtonsLabel.BUY,
									((SellableCard) card).canBuy(currentPlayer));
						} else {
							buttons.put(ButtonsLabel.BUY, false);
						}
					}
					log.info("list property for sell after mortage: "
							+ currentPlayer.listPropertyForSell());
					response.put("player_money", currentPlayer.getMoney());
					// broadcast(response);
					// currentPlayer.listPropertyForSell();
				} else {
					log.info("no position: ");
					response.put("type", ButtonsLabel.MORTAGE);
					response.put("mortage_list",
							currentPlayer.getMortageAvailable());
					response.put("player", currentPlayer.getColor());
					// broadcast(response);
				}
			}
			currentPlayer.addBuildAvailable();
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			state.put("buttons", buttons);
			response.put("game_state", state);
		}
		broadcast(response);
	}

	private void onUnMortage(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canUnmortage()) {
				response.put("type", ButtonsLabel.UNMORTAGE);
				response.put("player_money", currentPlayer.getMoney());
				// response.put("player_money", currentPlayer.getMoney());
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
					log.info("player money before: " + currentPlayer.getMoney());
					city = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					city.unMortage(currentPlayer);
					log.info("unmortage city: " + city.getName());
					currentPlayer.removeObj(currentPlayer.getForUnMortage(),
							city);
					log.info("list after unmortage: "
							+ currentPlayer.getForUnMortage());
					currentPlayer.getForMortage().add(city);
					log.info("player money after unmortage: "
							+ currentPlayer.getMoney());
					currentPlayer.listPropertyForSell();
					log.info("list property for sell after mortage: "
							+ currentPlayer.listPropertyForSell());
					response.put("player_money", currentPlayer.getMoney());
				} else {
					log.info("no position: ");
					response.put("type", ButtonsLabel.UNMORTAGE);
					response.put("unmortage_list",
							currentPlayer.getUnMortageAvailable());
					response.put("player", currentPlayer.getColor());
				}
			}
			currentPlayer.addBuildAvailable();
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			state.put("buttons", buttons);
			response.put("game_state", state);
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
			// response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
		}
		broadcast(response);
	}

	private void onBuy(int idPlayer, String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();

		if (currentPlayer.getId() == idPlayer) {
			log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			if (card.canBuy(currentPlayer)) {
				log.info("[Cell: ]" + card.info());
				card.buyCityOrRail(currentPlayer);
				monopolyGame.addToActivBoard(card);
				log.info("---------------------game board----"
						+ monopolyGame.getActiveBoard());
				log.info("[Player: ]" + currentPlayer.getColor()
						+ " buy this cell");
				log.info("[Player: ]" + currentPlayer.getColor() + " money: "
						+ currentPlayer.getMoney());
				response.put("type", ButtonsLabel.BUY);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
			}
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			state.put("buttons", buttons);
			response.put("game_state", state);
			currentPlayer.addBuildAvailable();
		}
		broadcast(response);
	}

	private void onRoll(int idPlayer, Map<String, Object> response) {
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		String messages = null;
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canRollDices()) {
				if (currentPlayer.isInJail()) {
					log.info("[Was ]" + currentPlayer.getPosition());
					log.info("[Player: " + currentPlayer.getColor()
							+ "in jail]" + currentPlayer.isInJail());
					response.put("type", ButtonsLabel.ROLL);
					response.put("was", currentPlayer.getPosition());
					JailCard jail = new JailCard();
					response.put(
							"game_state",
							jail.rollAndWait(currentPlayer,
									currentPlayer.rollDicesAndWait()));
					log.info("[Dice1: ]" + currentPlayer.getDiceOne());
					log.info("[Dice2: ]" + currentPlayer.getDiceTwo());
				} else {
					response.put("type", ButtonsLabel.ROLL);
					response.put("was", currentPlayer.getPosition());
					currentPlayer.rollDicesAndMove();
					log.info("[Player: ]" + currentPlayer.getName() + " : "
							+ currentPlayer.getColor());
					log.info("[Player moving to position: ]"
							+ currentPlayer.getPosition());
					log.info("[Dice1: ]" + currentPlayer.getDiceOne());
					log.info("[Dice2: ]" + currentPlayer.getDiceTwo());
					log.info("[-----------------Cell:----------------------------- ]"
							+ CardFactory.chooseCard(currentPlayer).info());
					response.put("dice1", currentPlayer.getDiceOne());
					response.put("dice2", currentPlayer.getDiceTwo());
					// response.put("money", currentPlayer.getMoney());
					response.put("game_state", GameAction.action(
							CardFactory.chooseCard(currentPlayer),
							currentPlayer));
				}
			} else {
				log.info("------------------------CAN ROLL------------PLAYER----"
						+ currentPlayer.getColor()
						+ ": "
						+ currentPlayer.canRollDices());
				log.info("---------------CAN CONTINUE GAME------PLAYER---"
						+ currentPlayer.canContinueGame() + ": "
						+ currentPlayer.getColor());
				if (currentPlayer.canContinueGame()) {
					response.put("type", ButtonsLabel.ROLL);
					buttons.put(ButtonsLabel.DONE, true);
					buttons.put(ButtonsLabel.ROLL, false);
					response.put("game_state", buttons);
				} else {
					log.info("---------------CAN CONTINUE GAME------PLAYER---"
							+ currentPlayer.canContinueGame() + ": "
							+ currentPlayer.getColor());
					currentPlayer.setLosser(true);
					leaveGame(currentPlayer);
					log.info("[Player money(loser)]:"
							+ currentPlayer.getMoney());
					if (hasWinner()) {
						Player winner = monopolyGame.getAllPlayers().get(0);
						winner.setWinner(true);
						log.info("[WINNER ]:" + winner.getColor());
						response.put("type", ButtonsLabel.ROLL);
						buttons.put(ButtonsLabel.DONE, false);
						buttons.put(ButtonsLabel.AUCTION, false);
						buttons.put(ButtonsLabel.BUILD, false);
						buttons.put(ButtonsLabel.BUY, false);
						buttons.put(ButtonsLabel.MORTAGE, false);
						buttons.put(ButtonsLabel.PAY, false);
						buttons.put(ButtonsLabel.ROLL, false);
						buttons.put(ButtonsLabel.SELL, false);
						buttons.put(ButtonsLabel.UNMORTAGE, false);
						messages = "Game ended. Winner is " + winner.getName();
						state.put("messages", messages);
						state.put("buttons", buttons);
						response.put("game_state", state);
						broadcast(response);
					}
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
			turn.put("type", ButtonsLabel.TURN);
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
		response.put("type", ButtonsLabel.READY);
		response.put("player", currentPlayer.getColor());
		response.put("ready", currentPlayer.isReadyToStart());
		broadcast(response);
	}

	private void onInit(int idPlayer, Map<String, Object> response) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		response.put("type", ButtonsLabel.INIT);
		response.put("color", getPlayerById(idPlayer).getColor());
		response.put("money", getPlayerById(idPlayer).getMoney());
		response.put("isCreator", getCreator().getId() == idPlayer);
		transport.sendMessage(idPlayer, response);
		Map<String, Object> welcome = new HashMap<String, Object>();
		welcome.put("type", "chat");
		welcome.put("message", "Welcome " + getPlayerById(idPlayer).getName());
		monopolyGame.refreshBoard();
		monopolyGame.refreshPlayers();
		broadcast(welcome);
	}

	public void broadcast(Map<String, ? extends Object> message) {
		WebSocketTransport transport = WebSocketTransport.getInstance();
		for (Player player : getMonopolyGame().getAllPlayers()) {
			log.info("Sending message to user with id=" + player.getId());
			transport.sendMessage(player.getId(), message);
		}
		for (Player player : monopolyGame.getAllLosers()) {
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

	public void deleteLoserPlayer(List<Player> players, List<Player> losers,
			Player player) {
		players.remove(player);
		losers.add(player);
	}

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
