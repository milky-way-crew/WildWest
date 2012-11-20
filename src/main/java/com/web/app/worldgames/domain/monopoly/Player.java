package com.web.app.worldgames.domain.monopoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class Player {
	private int id;
	private String name;
	private int position;
	private int money;
	private String color;
	// for test----
	private PlayerColors colors;
	// -----
	// private boolean hasFreeCard = false;
	private int numberFreeCard = 0;
	private static Random randDice = new Random();
	private int numberOfRails = 0;
	private boolean rolled;
	private int circleInJail = 0;
	private int circle = 0;
	private static int diceOne = 0;
	private static int diceTwo = 0;
	private boolean inJail;
	private boolean readyToStart = false;
	private boolean isLosser = false;
	private boolean isWinner = false;
	private List<String> listRegions = new ArrayList<String>();
	private List<SellableCard> property = new CopyOnWriteArrayList<SellableCard>();
//	private List<SellableCard> property = new ArrayList<SellableCard>();
	
	private List<SellableCard> forMortage = new ArrayList<SellableCard>();
	private List<SellableCard> forUnMortage = new ArrayList<SellableCard>();
	private List<SellableCard> forSell = new CopyOnWriteArrayList<SellableCard>();
//	private List<SellableCard> forSell = new ArrayList<SellableCard>();
	private Map<Integer, Integer> buildings = new HashMap<Integer, Integer>();
	private Map<String, Integer> regions = new HashMap<String, Integer>();
	private int numberOfBuildings = 0;
	private static final Logger log = Logger.getLogger(Player.class);

	// -----------MAYBY ONLY FOR TEST
	Map<Integer, Integer> buildAvailable = new HashMap<Integer, Integer>();

	public Map<Integer, Integer> getBuildAvailable() {
		return buildAvailable;
	}

	// --------------
	public Player(User user, int position, int money, String color) {
		this.id = user.getId();
		this.name = user.getNickname();
		this.position = position;
		this.money = money;
		this.color = color;
		// this.hasFreeCard = hasFreeCard;
	}

	// ----------for test
	public Player(String name, int position, int money, PlayerColors colors) {
		this.name = name;
		this.position = position;
		this.money = money;
		this.colors = colors;
	}

	// -----------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// public boolean isHasFreeCard() {
	// return hasFreeCard;
	// }
	//
	// public void setHasFreeCard(boolean hasFreeCard) {
	// this.hasFreeCard = hasFreeCard;
	// }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void addCircleInJail() {
		circleInJail++;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public boolean isLosser() {
		return isLosser;
	}

	public void setLosser(boolean isLosser) {
		this.isLosser = isLosser;
	}

	public int getCircle() {
		return circle;
	}

	public void setCircle(int circle) {
		this.circle = circle;
	}

	public boolean isReadyToStart() {
		return readyToStart;
	}

	public void setReadyToStart(boolean readyToStart) {
		this.readyToStart = readyToStart;
	}

	public int getDiceOne() {
		return diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public int getNumberFreeCard() {
		return numberFreeCard;
	}

	public void setNumberFreeCard(int numberFreeCard) {
		this.numberFreeCard = numberFreeCard;
	}

	// public void addFreeCard() {
	// numberFreeCard++;
	// }

	public int getNumberOfBuildings() {
		return numberOfBuildings;
	}

	public void setNumberOfBuildings(int numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}

	public Map<Integer, Integer> getBuildings() {
		return buildings;
	}
	public List<SellableCard> getProperty() {
		return property;
	}

	public void setBuildings(Map<Integer, Integer> buildings) {
		this.buildings = buildings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean isRolled() {
		return rolled;
	}

	public void setRolled(boolean rolled) {
		this.rolled = rolled;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static int rollDiceOne() {
		diceOne = randDice.nextInt(6) + 1;
		return diceOne;
	}

	public static int rollDiceTwo() {
		diceTwo = randDice.nextInt(6) + 1;
		return diceTwo;
	}

	public int rollDicesAndMove() {
		// if (this.canRollDices()) {
		position = getPosition() + (rollDiceOne() + rollDiceTwo());
		this.setRolled(true);
		int c = this.getCircle();
		if (position > 40) {
			this.setCircle(c++);
			this.setMoney(this.getMoney() + CardPrices.CIRCLE_MONEY);
			log.info("[-----PLAYER:-------] " + this.getName()
					+ " GET CIRCLE MONEY +$200");
			position = position - 40;
		}
		setPosition(position);
		// setRollAction(false);
		// } else {
		// log.info("[PLAYER:] " + this.getName() + " cannot roll");
		// }
		return position;

	}

	public int rollDicesAndWait() {
		position = getPosition() + (rollDiceOne() + rollDiceTwo());
		return position;

	}

	public boolean doublePoints() {
		return (diceOne == diceTwo) ? true : false;
	}

	public void addRegions(Player player) {
		for (Cities cities : Cities.values()) {
			if (cities.getPosition() == player.getPosition()) {
				listRegions.add(cities.getRegion());
			}
		}
	}

	// public void addRegion() {
	// for (Cities cities : Cities.values()) {
	// if (cities.getPosition() == this.getPosition()) {
	// System.out.println("add new region");
	// Integer r = regions.get(cities.getRegion());
	// regions.put(cities.getRegion(), r == null ? 1 : r + 1);
	// System.out.println(" region"+regions);
	// }
	// }
	// }

	// public Map<String, Integer> getRegions() {
	// return regions;
	// }
	//
	// public void setRegions(Map<String, Integer> regions) {
	// this.regions = regions;
	// }

	public List<SellableCard> getForSell() {
		return forSell;
	}

	public void addRegionsSellActivity(Player player, CityCard region) {
		listRegions.add(region.getRegion());
	}

	public List<String> listRegions(Player player) {
		return listRegions;

	}

	public String getRegion(int position) {
		String region = null;
		for (Cities regions : Cities.values()) {
			if (regions.getPosition() == position) {
				region = regions.getRegion();
			}
		}
		return region;
	}

	public int getNumberOfRegions(Player player, String region) {
		int regionsNumber = 0;
		for (String regions : listRegions(player)) {
			if (regions.equals(region)) {
				regionsNumber++;
			}
		}
		return regionsNumber;
	}

	public void addNumberOfRails() {
		numberOfRails++;
	}

	public void subNumberOfRails() {
		numberOfRails--;
	}

	public int getNumberOfRails() {
		return numberOfRails;
	}

	public void setNumberOfRails(int numberOfRails) {
		this.numberOfRails = numberOfRails;
	}

	public void addProperty(Player player) {
		for (Cities cities : Cities.values()) {
			if (cities.getPosition() == player.getPosition()) {
				property.add(new CityCard(cities));
			}
		}
		for (Rails rails : Rails.values()) {
			if (rails.getPosition() == player.getPosition()) {
				property.add(new RailCard(rails));
			}
		}
	}

	public void addForMortage() {
	}

//	public void addSelledProperty(SellableCard card) {
//		property.add(card);
//	}

	public void deleteProperty(Player player, SellableCard card) {
		property.remove(card);
	}

	public List<SellableCard> playerProperty() {
		return property;
	}

	public SellableCard cardByPosition(int position) {
		SellableCard card = null;
		for (SellableCard cards : playerProperty()) {
			if (cards.getPosition() == position) {
				card = cards;
			}
		}
		return card;
	}

	public SellableCard cardByIndex(int index) {
		return property.get(index);
	}

	public boolean checkMoney(int price) {
		return ((this.getMoney() - price) >= 0) ? true : false;
	}

	public boolean checkProperty() {
		return (playerProperty().isEmpty()) ? false : true;
	}

	public void setCircleInJail(int circleInJail) {
		this.circleInJail = circleInJail;
	}

	public int getCircleInJail() {
		return circleInJail;
	}

	public List<SellableCard> getForMortage() {
		return forMortage;
	}

	public Map<Integer, Integer> getMortageAvailable() {
		Map<Integer, Integer> mortageAvailable = new HashMap<Integer, Integer>();
		for (SellableCard p : this.getForMortage()) {
			mortageAvailable.put(p.getPosition(), p.getPrice() / 2);
		}
		return mortageAvailable;
	}

	public Map<Integer, Integer> getUnMortageAvailable() {
		Map<Integer, Integer> unMortageAvailable = new HashMap<Integer, Integer>();
		for (SellableCard p : this.getForUnMortage()) {
			unMortageAvailable.put(p.getPosition(), p.getPrice() / 2);
		}
		return unMortageAvailable;
	}
	public Map<Integer, Integer> getSellAvailable() {
		Map<Integer, Integer> sellAvailable = new HashMap<Integer, Integer>();
		for (SellableCard p : this.listPropertyForSell()) {
			sellAvailable.put(p.getPosition(), p.getPrice() / 2);
		}
		return sellAvailable;
	}

	public Map<Integer, Integer> addBuildAvailable() {
		Map<Integer, Integer> buildAvailable = getBuildAvailable();
		buildAvailable.clear();
		CityCard city = null;
		// for (SellableCard properties : property) {
		// if (properties instanceof CityCard) {
		// city = (CityCard) properties;
		// // log.info("[IN BUILD AVALIABLE METHOD]: " + "REGION "
		// // + city.getRegion() + " NUM REGIONS: "
		// // + this.getRegions().get(city.getRegion()));
		// if ((city.getRegion().equals("brown") || city.getRegion()
		// .equals("blue"))
		// && this.getRegions().get(city.getRegion()) == 2
		// && !city.isMortage()
		// && this.checkMoney(city.getHousePrice())
		// && city.getNumbersOfHouses() < 3) {
		// buildAvailable
		// .put(city.getPosition(), city.getHousePrice());
		// if (city.getNumbersOfHouses() == 3) {
		// buildAvailable.put(city.getPosition(),
		// city.getHotelPrice());
		// }
		// } else if (this.getRegions().get(city.getRegion()) == 3
		// && !city.isMortage()
		// && this.checkMoney(city.getHousePrice())
		// && city.getNumbersOfHouses() < 3) {
		// buildAvailable
		// .put(city.getPosition(), city.getHousePrice());
		// if (city.getNumbersOfHouses() == 3) {
		// buildAvailable.put(city.getPosition(),
		// city.getHotelPrice());
		// }
		// }
		// }
		// }
		for (SellableCard properties : property) {
			if (properties instanceof CityCard) {
				city = (CityCard) properties;
				// log.info("[IN BUILD AVALIABLE METHOD]: " + "REGION "
				// + city.getRegion() + " NUM REGIONS: "
				// + this.getRegions().get(city.getRegion()));
				if ((city.getRegion().equals("brown") || city.getRegion()
						.equals("blue"))
						&& this.getNumberOfRegions(this, city.getRegion()) == 2
						&& !city.isMortage()
						&& this.checkMoney(city.getHousePrice())
						&& city.getNumbersOfHouses() < 3) {
					buildAvailable
							.put(city.getPosition(), city.getHousePrice());
					if (city.getNumbersOfHouses() == 3) {
						buildAvailable.put(city.getPosition(),
								city.getHotelPrice());
					}
				} else if (this.getNumberOfRegions(this, city.getRegion()) == 3
						&& !city.isMortage()
						&& this.checkMoney(city.getHousePrice())
						&& city.getNumbersOfHouses() < 3) {
					buildAvailable
							.put(city.getPosition(), city.getHousePrice());
					if (city.getNumbersOfHouses() == 3) {
						buildAvailable.put(city.getPosition(),
								city.getHotelPrice());
					}
				}
			}
		}
		return buildAvailable;
	}

	public List<SellableCard> getForUnMortage() {
		return forUnMortage;
	}

	public List<SellableCard> listPropertyForMortage() {
		CityCard city = null;
		if (this.checkProperty()) {
			for (SellableCard card : this.playerProperty()) {
				if (card instanceof CityCard) {
					city = (CityCard) card;
					if (!city.isMortage() && !forMortage.contains(city)
							&& city.getNumbersOfHouses() == 0
							&& !city.isHotel()) {
						forMortage.add(city);
					}
				} else if (card instanceof RailCard) {
					if (!card.isMortage() && !forMortage.contains(card)) {
						forMortage.add(card);
					}
				}
			}
		} else {
			log.info("[MESSAGE]: property list is empty");
		}
		return forMortage;
	}

	public List<SellableCard> listPropertyForSell() {
		if (this.checkProperty()) {
			for (SellableCard card : this.playerProperty()) {
				if (!card.isMortage() && !forSell.contains(card)) {
					forSell.add(card);
				}
			}
		} else {
			log.info("[MESSAGE]: property list is empty");
		}
		return forSell;
	}

	public void removeObj(List<SellableCard> list, SellableCard card) {
		list.remove(card);
	}

	public List<SellableCard> listPropertyForUnmortage() {
		if (this.checkProperty()) {
			for (SellableCard card : this.playerProperty()) {
				if (card.isMortage() && !forUnMortage.contains(card)) {
					forUnMortage.add(card);
				}
			}
		}
		return forUnMortage;
	}

	public boolean canMortage() {
		return (!this.getForMortage().isEmpty()) ? true : false;
	}

	public boolean canUnmortage() {
		return (!this.getForUnMortage().isEmpty() && this.getMoney() > 0) ? true
				: false;
	}

	public boolean canSell() {
		//return (!this.forSell.isEmpty()) ? true : false;
		return (!this.getSellAvailable().isEmpty())?true:false;
	}

	public boolean canRollDices() {
		boolean roll = false;
		if (this.getMoney() > 0 && this.doublePoints() && isRolled()) {
			roll = true;
		} else if (this.getMoney() > 0 && !isRolled()) {
			roll = true;
		}
		return roll;
	}

	public boolean canContinueGame() {
		return ((this.canMortage() && this.canSell()) || this.getMoney() > 0) ? true
				: false;
	}

	// public boolean canHandOverBuilding() {
	// return (this.getNumberOfBuildings() > 0) ? true : false;
	// }

	public boolean canBuild() {
		return (!getBuildAvailable().isEmpty()) ? true : false;
	}

	public void handOverBuilding(CityCard city) {
		if (city.isHotel()) {
			city.setHotel(false);
			this.setMoney(this.getMoney() + city.getHotelPrice());
		} else {
			city.setNumbersOfHouses(city.getNumbersOfHouses() - 1);
			this.setMoney(this.getMoney() + city.getHousePrice());
		}
	}
}
