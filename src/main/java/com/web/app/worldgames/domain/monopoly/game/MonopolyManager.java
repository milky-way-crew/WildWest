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
import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Cities;
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
	private int auctionPrice;
	private boolean auctionEnd = false;
	private int maxAuctionPricePrice = 0;
	private Thread auction = null;
	
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

	public String protect(String message) {
		message = message.replaceAll(">", "&gt;");
		message = message.replaceAll("<", "&lt;");
		return message;
	}
	public Player auctionCreator(){
		Player player=null;
		for(Player players:monopolyGame.getAllPlayers()){
			if(players.isAuctionCreator()){
				player=players;
			}
		}
		return player;
	}
	public Map<String, ? extends Object> onMessage(int idPlayer, String type,
			String data) {

		log.info("[RECIEVING MESSAGE] OF TYPE: " + type);
		log.info("DATA: " + data);
		Map<String, Object> response = new HashMap<String, Object>();
		if ($(type).equals(ButtonsLabel.CHAT)) {
			onChat(idPlayer, type, response, data);
		}
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
				onDone(idPlayer, type, response);
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

	private void onChat(int idPlayer, String type,
			Map<String, Object> response, String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		Player currentPlayer = getPlayerById(idPlayer);
		JsonNode tree = null;
		try {
			tree = objectMapper.readTree(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonNode dataBlock = tree.path("data");
		String message = dataBlock.path("message").getTextValue();
		message = protect(message);
		response.put("type", ButtonsLabel.CHAT);
		response.put("message", "[ " + currentPlayer.getName() + " ]: "
				+ message);
		broadcast(response);
	}

	private void onAuction(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> rates = new HashMap<String, Object>();
		Map<String, Object> highest = new HashMap<String, Object>();
		int tempAuctionPrice = 0;
		try {
			String messages = null;
			ObjectMapper objectMapper = new ObjectMapper();
			Player currentPlayer = getPlayerById(idPlayer);
			currentPlayer.setAuctionRates(getMaxAuctionPrice());
			SellableCard card = (SellableCard) CardFactory
					.chooseCard(monopolyGame.getCurrentPlayer());
			Player auctionCreator = monopolyGame.getCurrentPlayer();
			auctionCreator.setAuctionCreator(true);
			log.info(" ---------------player-------" + currentPlayer.getColor()
					+ "set start rate:::::" + currentPlayer.getAuctionRates());
			if (currentPlayer.equals(auctionCreator)
					&& !card.isAuctionStarted()) {
				card.setAuctionStarted(true);
				auctionCreator.setCanCreateAuction(true);
			}
			response.put("type", ButtonsLabel.AUCTION);
			response.put("invoker", auctionCreator.getColor());
			rates.put("player", currentPlayer.getColor());
			rates.put("rates", currentPlayer.getAuctionRates());
			buttons.put(ButtonsLabel.UP10, currentPlayer.canUpAuctionPrice(CardPrices.UP10_AUCTION_PRICE+10));
			buttons.put(ButtonsLabel.UP50, currentPlayer.canUpAuctionPrice(CardPrices.UP50_AUCTION_PRICE+50));
			response.put("card", auctionCreator.getPosition());

			if (card.isAuctionStarted() && currentPlayer.equals(auctionCreator)
					&& auctionCreator.isCanCreateAuction()) {
				auctionCreator.setCanCreateAuction(false);
				auction = new Thread(new Auction(this, card));
				auction.start();
//				rates.put("player", currentPlayer.getColor());
//				rates.put("rates", currentPlayer.getAuctionRates());
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
				tempAuctionPrice = getAuctionPrice();
				log.info("temp::: " + tempAuctionPrice);
				setAuctionPrice(price);
				log.info("auction price::::" + getAuctionPrice());
				log.info("check:::::::::::::::::::::::"
						+ (currentPlayer.getAuctionRates() > getAuctionPrice()));
				if (currentPlayer.getAuctionRates() > getAuctionPrice()) {
					if ((getAuctionPrice() - tempAuctionPrice) == 50) {
						setMaxAuctionPrice(currentPlayer.getAuctionRates() + 50);
					} else if ((getAuctionPrice() - tempAuctionPrice) == 10) {
						setMaxAuctionPrice(currentPlayer.getAuctionRates() + 10);
					}
				} else if (currentPlayer.getAuctionRates() <= getAuctionPrice()) {
					setMaxAuctionPrice(price);
				}
					log.info("max::::" + getMaxAuctionPrice());
					currentPlayer.setAuctionRates(getMaxAuctionPrice());
					log.info("rate:::::player"
							+ currentPlayer.getAuctionRates() + ":::"
							+ currentPlayer.getColor());
//					rates.put("player", currentPlayer.getColor());
//					rates.put("rates", currentPlayer.getAuctionRates());
					log.info("chect can auction:::"
							+ (currentPlayer.canAuction(getMaxAuctionPrice())));
					// if (currentPlayer.canAuction(getMaxAuctionPrice())) {
					setAuctionWinner(currentPlayer);
					highest.put("who", getAuctionWinner().getColor());
					highest.put("money", getMaxAuctionPrice());
					// }
					// else {
					// messages = "You cann't continue auction";
					// price = 0;
					// }
				
				log.info(" NOW PRICE======" + getMaxAuctionPrice());
			} else {
				messages = "No player wants to buy this object";
				log.info("no price: ");
				setMaxAuctionPrice(0);
				setAuctionPrice(0);
			}
			buttons.put(ButtonsLabel.MORTAGE, currentPlayer.canMortage());
			buttons.put(ButtonsLabel.UNMORTAGE, currentPlayer.canUnmortage());
			buttons.put(ButtonsLabel.BUILD, currentPlayer.canBuild());
			buttons.put(ButtonsLabel.SELL, currentPlayer.canSell());
			buttons.put(ButtonsLabel.ROLL, currentPlayer.canRollDices());
			buttons.put(ButtonsLabel.BUY, false);
			rates.put("player", currentPlayer.getColor());
			rates.put("rates", currentPlayer.getAuctionRates());
			state.put("buttons", buttons);
			state.put("messages", messages);
			response.put("rates", rates);
			response.put("highest", highest);
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

	private void onDone(int idPlayer, String type, Map<String, Object> response) {
		Map<String, Object> turn = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		Map<String, Object> state = new HashMap<String, Object>();
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		// if (currentPlayer.getId() == idPlayer) {
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
		// }
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
					SellableCard cell = null;
					int pos = dataBlock.path("position").getIntValue();
					cell = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
					response.put("player", currentPlayer.getColor());
					log.info(":::::::::::::::::::CITY FOR MORTAGE:::::"+cell.info());
					cell.mortage(currentPlayer);
					if(cell instanceof CityCard){
						StartGame.boardCities.get(pos).setMortage(true);
					}
					if(cell instanceof RailCard){
						StartGame.boardRails.get(pos).setMortage(true);
					}
					log.info(":::::::::::::::::::cell AFTER MORTAGE:::::"+cell.info());
					currentPlayer
							.removeObj(currentPlayer.getForMortage(), cell);
					log.info("list after mortage: "
							+ currentPlayer.getForMortage());
					currentPlayer.getForUnMortage().add(cell);
					if (currentPlayer.listPropertyForSell().contains(cell)) {
						currentPlayer.removeObj(
								currentPlayer.listPropertyForSell(), cell);
					}
					log.info("list property for sell after mortage: "
							+ currentPlayer.listPropertyForSell());
					response.put("player_money", currentPlayer.getMoney());
				} else {
					log.info("no position: ");
					response.put("type", ButtonsLabel.MORTAGE);
					response.put("mortage_list",
							currentPlayer.getMortageAvailable());
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

	private void onUnMortage(int idPlayer, String type,
			Map<String, Object> response, String data) {
		Player currentPlayer = getMonopolyGame().getCurrentPlayer();
		Map<String, Object> state = new HashMap<String, Object>();
		Map<String, Object> buttons = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();
		if (currentPlayer.getId() == idPlayer) {
			if (currentPlayer.canUnmortage()) {
				response.put("type", ButtonsLabel.UNMORTAGE);
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
					SellableCard cell = null;
					int pos = dataBlock.path("position").getIntValue();
					cell = currentPlayer.cardByPosition(pos);
					response.put("position", pos);
					cell.unMortage(currentPlayer);
					if(cell instanceof CityCard){
						StartGame.boardCities.get(pos).setMortage(false);
					}
					if(cell instanceof RailCard){
						StartGame.boardRails.get(pos).setMortage(false);
					}

					log.info("unmortage city: " + cell.getName());
					currentPlayer.removeObj(currentPlayer.getForUnMortage(),
							cell);
					log.info("list after unmortage: "
							+ currentPlayer.getForUnMortage());
					currentPlayer.getForMortage().add(cell);
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
		try{
			
		
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
		}catch(Exception e){
			e.printStackTrace();
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
		Map<String, Object> status = new HashMap<String, Object>();
		response.put("type", ButtonsLabel.INIT);
		response.put("color", getPlayerById(idPlayer).getColor());
		response.put("money", getPlayerById(idPlayer).getMoney());
		response.put("isCreator", getCreator().getId() == idPlayer);
		response.put("board", monopolyGame.refreshBoard());
		response.put("players", monopolyGame.refreshPlayers());
		status.put("game_started", monopolyGame.isStarted());
		status.put("game_isEnd", monopolyGame.isEnd());
		response.put("game_status", status);
		response.put("buttons",
				ButtonsAction.buttonsAction(getPlayerById(idPlayer)));
		transport.sendMessage(idPlayer, response);
		// Map<String, Object> welcome = new HashMap<String, Object>();
		// welcome.put("type", ButtonsLabel.CHAT);
		// welcome.put("message", "Welcome " +
		// getPlayerById(idPlayer).getName());
		Map<String, Object> connect = new HashMap<String, Object>();
		connect.put("type", ButtonsLabel.LOGIC);
		connect.put("subType", ButtonsLabel.CONNECT);
		connect.put("player", getPlayerById(idPlayer).getColor());
		connect.put("position", getPlayerById(idPlayer).getPosition());
		connect.put("money", getPlayerById(idPlayer).getMoney());
		connect.put("nick", getPlayerById(idPlayer).getName());
		broadcast(connect);
		// broadcast(welcome);
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
//					SellableCard city = game.boardCities().get(card
//							.getPosition());
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
	// public static void main(String[] args) {
	// CityCard c = new CityCard(Cities.ATHENS);
	// Thread t = new Thread(new Auction(new MonopolyManager(monopolyGame), c));
	// }
}
