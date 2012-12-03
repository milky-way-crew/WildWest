package com.web.app.worldgames.domain.monopoly.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class MonopolyManager {
	private static final Logger log = Logger.getLogger(MonopolyManager.class);
	private Game monopolyGame;
	private User creator;
	private Player auctionWinner;
	private int auctionPrice = 0;
	private int maxAuctionPricePrice = 0;

	public Player getAuctionWinner() {
		return auctionWinner;
	}

	public void setAuctionWinner(Player auctionWinner) {
		this.auctionWinner = auctionWinner;
	}

	public int getMaxAuctionPrice() {
		return maxAuctionPricePrice;
	}

	public void setMaxAuctionPrice(int maxAuctionPricePrice) {
		this.maxAuctionPricePrice = maxAuctionPricePrice;
	}

	public int getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

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
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		try {
		Map<String, Object> rates = new HashMap<String, Object>();
		String messages = null;
		ObjectMapper objectMapper = new ObjectMapper();
		Player currentPlayer = getPlayerById(idPlayer);
		SellableCard card = (SellableCard) CardFactory.chooseCard(monopolyGame
				.getCurrentPlayer());
		Player auctionCreator = monopolyGame.getCurrentPlayer();
		auctionCreator.setAuctionCreator(true);
		// System.out.println("info::"
		// + (currentPlayer.equals(auctionCreator) && !card
		// .isAuctionStarted()));
		if (currentPlayer.equals(auctionCreator) && !card.isAuctionStarted()) {
			card.setAuctionStarted(true);
			auctionCreator.setCanCreateAuction(true);
		}
		// System.out.println("information :: " + card.isAuctionStarted() + ";;"
		// + currentPlayer.equals(auctionCreator) + "::"
		// + auctionCreator.isCanCreateAuction());
		response.put("type", ButtonsLabel.AUCTION);
		response.put("invoker", auctionCreator.getColor());
		rates.put("player", currentPlayer.getColor());
		rates.put("rates", currentPlayer.getAuctionRates());
		if (card.isAuctionStarted() && currentPlayer.equals(auctionCreator)
				&& auctionCreator.isCanCreateAuction()) {
			auctionCreator.setCanCreateAuction(false);
			Timer timer = new Timer();
			// long start = new Date().getTime();
			// response.put("start_Auction_time",);
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					SellableCard card = (SellableCard) CardFactory
							.chooseCard(monopolyGame.getCurrentPlayer());
					log.info("---------- card AUCTION----" + card.getName());
					log.info("----AUCTION ENDED----");
					if (getMaxAuctionPrice() != 0) {
						card.auctionCityOrRail(getAuctionWinner(),
								getMaxAuctionPrice());
					}
					log.info("MAX PRICE === " + getMaxAuctionPrice());
				}
			}, 60000);
		}
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
					currentPlayer.setAuctionRates(currentPlayer.getAuctionRates()+price);
					log.info("-----AUCTION START PRICE------"
							+ monopolyGame.getAuctionPrice());
					monopolyGame.setAuctionPrice(price);
					log.info("-----AUCTION START PRICE----NEXT--"
							+ getAuctionPrice());
					// response.put("player", currentPlayer.getColor());
					response.put("auction_price", getMaxAuctionPrice());
					response.put("card", auctionCreator.getPosition());
					rates.put("player", currentPlayer.getColor());
					rates.put("rates", currentPlayer.getAuctionRates());
					if (currentPlayer
							.canAuction(monopolyGame.getAuctionPrice())) {
						setAuctionPrice(price);
						setMaxAuctionPrice(getAuctionPrice());
						log.info("-----AUCTION START PRICE------"
								+ getAuctionPrice());
						setAuctionWinner(currentPlayer);
						response.put("player_winner", getAuctionWinner().getColor());
						response.put("player_money", getAuctionWinner().getMoney());
					} else {
						messages = "You cann't continue auction";
						price = 0;
					}
					log.info(" NOW PRICE======"
							+ monopolyGame.getAuctionPrice());
				} else {
					monopolyGame
							.setAuctionPrice(monopolyGame.getAuctionPrice());
					log.info("PRICE IS LESS=========");
					messages = "If you want to continue auction set high price";
				}
			} else {
				messages = "No player wants to buy this object";
				log.info("no price: ");
				// response.put("", value)
				setMaxAuctionPrice(0);
			}
		buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
		buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
		buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
		buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
		buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
		buttons.put(ButtonsLabel.BUY, false);
		state.put("buttons", buttons);
		state.put("messages", messages);
		response.put("rates", rates);
		response.put("game_state", state);
		log.info("auction state " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				// response.put("player_money", currentPlayer.getMoney());
				JsonNode tree = null;
				try {
					tree = objectMapper.readTree(data);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JsonNode dataBlock = tree.path("data");
				if (dataBlock.has("position")) {
					CityCard city = null;
					int pos = dataBlock.path("position").getIntValue();
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
				buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
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
				// response.put("player_money", currentPlayer.getMoney());
				JsonNode tree = null;
				try {
					tree = objectMapper.readTree(data);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JsonNode dataBlock = tree.path("data");
				if (dataBlock.has("position")) {
					SellableCard card = null;
					int pos = dataBlock.path("position").getIntValue();
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
					log.info("list after sell: " + currentPlayer.getForSell());
					// Cell cell = CardFactory.chooseCard(currentPlayer);
					// log.info("CHECK card" + cell.getName());
					// if (cell instanceof SellableCard) {
					// if (((SellableCard) cell).getOwner() == null) {
					// log.info("CHECK BUY TRUE/FALSE "
					// + ((SellableCard) cell)
					// .canBuy(currentPlayer));
					// buttons.put(ButtonsLabel.BUY,
					// ((SellableCard) cell).canBuy(currentPlayer));
					// } else {
					// buttons.put(ButtonsLabel.BUY, false);
					// }
					// }
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
		Map<String, Object> turn = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		currentPlayer.setRolled(false);
		log.info("[CURRENT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		getMonopolyGame().setCurrentPlayer(getMonopolyGame().getNextPlayer());
		log.info("[NEXT PLAYER] : " + getMonopolyGame().getCurrentPlayer());
		currentPlayer = getMonopolyGame().getCurrentPlayer();
		log.info("[Player: ]" + currentPlayer.getName() + ":"
				+ currentPlayer.getColor() + " can roll dice");
		turn.put("type", ButtonsLabel.TURN);
		buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
		buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
		buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
		buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
		buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
		turn.put("player", currentPlayer.getColor());
		state.put("buttons", buttons);
		turn.put("game_state", state);
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
				// response.put("player_money", currentPlayer.getMoney());
				JsonNode tree = null;
				try {
					tree = objectMapper.readTree(data);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JsonNode dataBlock = tree.path("data");

				if (dataBlock.has("position")) {
					SellableCard city = null;
					int pos = dataBlock.path("position").getIntValue();
					city = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					city.mortage(currentPlayer);
					currentPlayer
							.removeObj(currentPlayer.getForMortage(), city);
					log.info("list after mortage: "
							+ currentPlayer.getForMortage());
					currentPlayer.getForUnMortage().add(city);
					if (currentPlayer.listPropertyForSell().contains(city)) {
						currentPlayer.removeObj(
								currentPlayer.listPropertyForSell(), city);
					}
					// Cell card = CardFactory.chooseCard(currentPlayer);
					// if (card instanceof SellableCard) {
					// if (((SellableCard) card).getOwner() == null) {
					// log.info("CHECK BUY TRUE/FALSE"
					// + ((SellableCard) card)
					// .canBuy(currentPlayer));
					// buttons.put(ButtonsLabel.BUY,
					// ((SellableCard) card).canBuy(currentPlayer));
					// } else {
					// buttons.put(ButtonsLabel.BUY, false);
					// }
					// }
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
			buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
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
				// response.put("player_money", currentPlayer.getMoney());
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

				if (dataBlock.has("position")) {
					SellableCard city = null;
					int pos = dataBlock.path("position").getIntValue();
					city = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
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
					log.info("list property for sell after unmortage: "
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
			buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
			state.put("buttons", buttons);
			response.put("game_state", state);
		}
		broadcast(response);
	}

	private void onPay(int idPlayer, String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		if (currentPlayer.getId() == idPlayer) {
			log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
			Cell cell = CardFactory.chooseCard(currentPlayer);
			if (((JailCard) cell).canPayRansom(currentPlayer)) {
				((JailCard) cell).payRansom(currentPlayer);
			}
			buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			buttons.put(ButtonsLabel.AUCTION, false);
			state.put("buttons", buttons);
			response.put("type", ButtonsLabel.PAY);
			response.put("player", currentPlayer.getColor());
			response.put("player_money", currentPlayer.getMoney());
			response.put("game_state", state);
		}
		broadcast(response);
	}

	private void onBuy(int idPlayer, String type, Map<String, Object> response) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		if (currentPlayer.getId() == idPlayer) {
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(currentPlayer);
			if (card.canBuy(currentPlayer)) {
				card.buyCityOrRail(currentPlayer);
				monopolyGame.addToActivBoard(card);
				response.put("type", ButtonsLabel.BUY);
				response.put("player", currentPlayer.getColor());
				response.put("player_money", currentPlayer.getMoney());
			}
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			buttons.put(ButtonsLabel.AUCTION, false);
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
		boolean move = false;
		String messages = null;
		if (!monopolyGame.isEnd()) {
			if (currentPlayer.getId() == idPlayer) {
				if (currentPlayer.canRollDices()) {
					if (currentPlayer.isInJail()) {
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
						move = true;
						response.put("type", ButtonsLabel.ROLL);
						response.put("was", currentPlayer.getPosition());
						currentPlayer.rollDicesAndMove();
						log.info("[Dice1: ]" + currentPlayer.getDiceOne());
						log.info("[Dice2: ]" + currentPlayer.getDiceTwo());
						log.info("[-----------------Cell:----------------------------- ]"
								+ CardFactory.chooseCard(currentPlayer).info());
						response.put("dice1", currentPlayer.getDiceOne());
						response.put("dice2", currentPlayer.getDiceTwo());
						response.put("move", move);
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
						move = false;
						response.put("type", ButtonsLabel.ROLL);
						response.put("move", move);
						buttons.put(ButtonsLabel.DONE, true);
						buttons.put(ButtonsLabel.MORTAGE,
								currentPlayer.canMortage());
						buttons.put(ButtonsLabel.UNMORTAGE,
								currentPlayer.canUnmortage());
						buttons.put(ButtonsLabel.BUILD,
								currentPlayer.canBuild());
						buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
						buttons.put(ButtonsLabel.ROLL,
								currentPlayer.canRollDices());
						response.put("game_state", buttons);
					} else {
						log.info("---------------CAN CONTINUE GAME------PLAYER---"
								+ currentPlayer.canContinueGame()
								+ ": "
								+ currentPlayer.getColor());
						currentPlayer.setLosser(true);
						response.put("type", ButtonsLabel.LOGIC);
						messages = "Player " + currentPlayer.getColor()
								+ " leave game!";
						buttons.put(ButtonsLabel.DONE, true);
						leaveGame(currentPlayer);
						log.info("[Player money(loser)]:"
								+ currentPlayer.getMoney());
						response.put("loser", currentPlayer.getColor());
						state.put("buttons", buttons);
						state.put("massages", messages);
						response.put("game_state", state);
						if (hasWinner()) {
							Player winner = monopolyGame.getAllPlayers().get(0);
							winner.setWinner(true);
							monopolyGame.setEnd(true);
							monopolyGame.setStarted(false);
							log.info("[WINNER ]:" + winner.getColor());
							response.put("type", ButtonsLabel.LOGIC);
							response.put("winner", winner.getColor());
							buttons.put(ButtonsLabel.DONE, false);
							buttons.put(ButtonsLabel.AUCTION, false);
							buttons.put(ButtonsLabel.BUILD, false);
							buttons.put(ButtonsLabel.BUY, false);
							buttons.put(ButtonsLabel.MORTAGE, false);
							buttons.put(ButtonsLabel.PAY, false);
							buttons.put(ButtonsLabel.ROLL, false);
							buttons.put(ButtonsLabel.SELL, false);
							buttons.put(ButtonsLabel.UNMORTAGE, false);
							messages = "Game ended. Winner is "
									+ winner.getName();
							state.put("messages", messages);
							state.put("buttons", buttons);
							response.put("game_state", state);
							// broadcast(response);
						}
					}
				}
			}
			broadcast(response);
		}
	}

	private void onStart(Map<String, Object> response) {
		Map<String, Object> turn = null;
		response.put("type", ButtonsLabel.LOGIC);
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
		response.put("board", monopolyGame.refreshBoard());
		response.put("players", monopolyGame.refreshPlayers());
		transport.sendMessage(idPlayer, response);

		Map<String, Object> welcome = new HashMap<String, Object>();
		welcome.put("type", "chat");
		welcome.put("message", "Welcome " + getPlayerById(idPlayer).getName());

		// monopolyGame.refreshBoard();
		// monopolyGame.refreshPlayers();
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
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		List<Integer> index = new ArrayList<Integer>();
		String messages = null;
		if (player.isLosser()) {
			monopolyGame.setCurrentPlayer(monopolyGame.getPreventPlayer());
			this.deleteLoserPlayer(monopolyGame.getAllPlayers(),
					monopolyGame.getAllLosers(), player);
			log.info("[PLAYER LEAVE THIS GAME: ]" + player.getColor());
			log.info("[PLAYER LIST ]" + monopolyGame.getAllPlayers());
			log.info("[LOSER LIST ]" + monopolyGame.getAllLosers());
			log.info("******************Cards after leave game*************");
			for (SellableCard card : player.playerProperty()) {
				index.add(card.getPosition());
				if (card instanceof CityCard) {
					SellableCard city = StartGame.boardCities.get(card
							.getPosition());
					city.setOwner(null);
					if (city.isMortage()) {
						city.setMortage(false);
					}
					log.info(city.info());
					player.playerProperty().remove(city);
					player.listRegions(player).remove(
							((CityCard) card).getRegion());
				} else if (card instanceof RailCard) {
					SellableCard rail = StartGame.boardRails.get(card
							.getPosition());
					rail.setOwner(null);
					if (rail.isMortage()) {
						rail.setMortage(false);
					}
					log.info(rail.info());
					player.playerProperty().remove(rail);
				}
			}
			messages = "Player " + player.getName() + " leave game";
			log.info("list property players: " + player.playerProperty());
			buttons.put(ButtonsLabel.DONE, true);
			state.put("buttons", buttons);
			state.put("messages", messages);
			state.put("player_loser", player.getColor());
			state.put("index", index);
			state.put("player_money", player.getMoney());
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
