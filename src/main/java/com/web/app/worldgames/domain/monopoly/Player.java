package com.web.app.worldgames.domain.monopoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.card.CityCard;
import com.web.app.worldgames.domain.monopoly.card.RailCard;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;
import com.web.app.worldgames.domain.monopoly.game.Game;

public class Player {
	private int id;
	private String name;
	private int position;
	private int money;
	private String color;
	private boolean hasFreeCard;
	private static Random randDice = new Random();
	private List<String> listRegions = new ArrayList<String>();
	private int numberOfRailss = 0;
	private int circleInJail = 0;
	private static int diceOne = 0;
	private static int diceTwo = 0;
	private boolean inJail;
	private List<SellableCard> property = new ArrayList<SellableCard>();
	private List<SellableCard> forMortage = new ArrayList<SellableCard>();

	private List<SellableCard> forUnMortage = new ArrayList<SellableCard>();
	private boolean loss = false;
	private boolean rollAction = false;

	public boolean isRollAction() {
		return rollAction;
	}

	public void setRollAction(boolean rollAction) {
		this.rollAction = rollAction;
	}

	public Player(User user, int position, int money, boolean hasFreeCard) {
		this.id=user.getId();
		this.name = user.getNickname();
		this.position = position;
		this.money = money;
		this.hasFreeCard = hasFreeCard;
	}

	public Player(String name, int position, int money, boolean hasFreeCard, String color) {
		this.name = name;
		this.position = position;
		this.money = money;
		this.hasFreeCard = hasFreeCard;
		this.color=color;
	}

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

	public boolean isHasFreeCard() {
		return hasFreeCard;
	}

	public void setHasFreeCard(boolean hasFreeCard) {
		this.hasFreeCard = hasFreeCard;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
		circleInJail = 0;
	}

	public boolean isLoss() {
		return loss;
	}

	public void setLoss(boolean loss) {
		this.loss = loss;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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

	public int getDiceOne() {
		return diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public static int rollDiceOne() {
		diceOne = randDice.nextInt(6) + 1;
		System.out.println("dice one: " + diceOne);
		return diceOne;
	}

	public static int rollDiceTwo() {
		diceTwo = randDice.nextInt(6) + 1;
		System.out.println("dice two: " + diceTwo);
		return diceTwo;
	}

	public int rollDicesAndMove() {
		position = getPosition() + (rollDiceOne() + rollDiceTwo());
		if (position > 40) {
			position = position - 40;
		}
		setPosition(position);
		setRollAction(false);
		return position;

	}

	public int rollDicesAndWait() {
		position = getPosition() + (rollDiceOne() + rollDiceTwo());
		return position;

	}

	public static boolean doublePoints() {
		return (diceOne == diceTwo) ? true : false;
	}

	public void addRegions(Player player) {
		for (Cities cities : Cities.values()) {
			if (cities.getPosition() == player.getPosition()) {
				listRegions.add(cities.getRegion());
			}
		}
	}

	public void addRegionsSaleActivity(Player player, CityCard region) {
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
		numberOfRailss++;
	}

	public void subNumberOfRAils() {
		numberOfRailss--;
	}

	public int getNumberOfRails() {
		return numberOfRailss;
	}

	public void exitPlayer(Player player) {
		StartGame game = new StartGame();
		for (SellableCard card : player.playerProperty()) {
			card.setOwner(null);
			if (card.isMortage()) {
				card.setMortage(false);
			}
		}
		StartGame.deleteLoserPlayer(game.playersPermanentlyList(), player);

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

	public void addSaledProperty(SellableCard card) {
		property.add(card);
	}

	public void deleteProperty(Player player, SellableCard card) {
		property.remove(card);
	}

	public List<SellableCard> playerProperty() {
		return property;
	}

	public SellableCard cardByIndex(int index) {
		return property.get(index);
	}

	public boolean checkMoney(Player player, int price) {
		return ((player.getMoney() - price) >= 0) ? true : false;
	}

	public boolean checkProperty(Player player) {
		return (player.playerProperty().isEmpty()) ? false : true;
	}
//delete 
	public String playerAction() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return key;
	}

	public int circleInJail() {
		return circleInJail++;
	}

	public int getCircleInJail() {
		return circleInJail;
	}

	// public void exitFromJail() {
	// circleInJail = 0;
	// }
	public List<SellableCard> getForMortage() {
		return forMortage;
	}

	public List<SellableCard> getForUnMortage() {
		return forUnMortage;
	}

	public void printMortageList(List<SellableCard> list, Player player) {
		int n = 0;
		for (SellableCard c : list) {
			System.out.println(n + ": " + c.getName() + " index: "
					+ forMortage.indexOf(c) + " you will get: " + c.getPrice()
					/ 2);
			n++;
		}
		// if (forMortage.isEmpty()) {
		// System.out
		// .println("You haven't money and haven't any property to mortage. You lost the game. Good-bye!");
		// //StartGame.deleteLoserPlayer(game.playersPermanentlyList(), player);
		// player.setLoss(true);
		// }
	}

	public boolean canMortage() {
		if (getForMortage().isEmpty()) {
			return false;
		} else
			return true;
	}

	public void printUnmortageList(List<SellableCard> list, Player player) {
		int n = 0;
		for (SellableCard c : list) {
			System.out.println(n + ": " + c.getName() + " index: "
					+ forUnMortage.indexOf(c) + " you must pay: "
					+ c.getPrice() / 2);
			n++;
		}
		if (forUnMortage.isEmpty()) {
			System.out.println("You haven't mortage objects");
		}
	}

	public void listPropertyForMortage(Player player) {
		if (player.checkProperty(player)) {
			for (SellableCard card : player.playerProperty()) {
				if (!card.isMortage() && !forMortage.contains(card)) {
					forMortage.add(card);
				}
			}
		}
		// return forMortage;
	}

	public List<SellableCard> listPropertyForUnmortage(Player player) {
		if (player.checkProperty(player)) {
			for (SellableCard card : player.playerProperty()) {
				if (card.isMortage() && !forUnMortage.contains(card)) {
					forUnMortage.add(card);
				}
			}
		}
		return forUnMortage;
	}

	public void chooseAndMortage(List<SellableCard> mortageList,
			SellableCard card, Player player) {
		if (mortageList.contains(card)) {
			card.mortage(player);
			forUnMortage.add(card);
			forMortage.remove(card);
			System.out.println("You mortage: " + card.getName() + " you get: "
					+ card.getPrice() / 2);
		}
	}

	public void chooseAndUnMortage(List<SellableCard> unmortageList,
			SellableCard card, Player player) {
		if (unmortageList.contains(card) && card.isMortage()) {
			card.unMortage(player);
			forMortage.add(card);
			forUnMortage.remove(card);
			System.out.println("You unmortage: " + card.getName()
					+ " you pay: " + card.getPrice() / 2);
		}
	}

	public void mortageAction(Player player) {
		//System.out.println("You haven't money. Mortage you property: ");
		// if(forMortage.isEmpty()){
		// System.out.println("You haven't object");
		// player.setLoss(true);
		// }else{
		if(canMortage()){
			printMortageList(forMortage, player);

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int index = Integer.valueOf(key);
		SellableCard cityToMortage = forMortage.get(index);
		chooseAndMortage(forMortage, cityToMortage, player);
		}else System.out.println("no obj");
		// }
	}

	public void unMortageAction(Player player) {
		System.out.println("Choose object to unmortage: ");
		printUnmortageList(forUnMortage, player);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int index = Integer.valueOf(key);
		SellableCard cityToUnMortage = listPropertyForUnmortage(player).get(
				index);
		chooseAndUnMortage(forUnMortage, cityToUnMortage, player);
	}

	public boolean canRollDices(Player player) {
		Game game = new Game();
		if (game.playerList().contains(player) && player.getMoney() > 0) {
			setRollAction(true);
			return true;
		} else
			return false;
	}
}
=======
package com.web.app.worldgames.domain.monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	private int points;
	private List<String> listRegions = new CopyOnWriteArrayList<String>();
	// private List<String> listRegions = new ArrayList<String>();
	// private List<SellableCard> property = new ArrayList<SellableCard>();
	private String user;
	private List<SellableCard> property = new CopyOnWriteArrayList<SellableCard>();
	private List<SellableCard> forSell = new ArrayList<SellableCard>();
	private List<SellableCard> forMortage = new ArrayList<SellableCard>();
	private List<SellableCard> forUnMortage = new ArrayList<SellableCard>();
	private Map<Integer, Integer> buildings = new HashMap<Integer, Integer>();
	private int numberOfBuildings = 0;
	private boolean isAuctionCreator;
	private boolean canCreateAuction;
	private int auctionRates;

	public boolean isCanCreateAuction() {
		return canCreateAuction;
	}

	public void setCanCreateAuction(boolean canCreateAuction) {
		this.canCreateAuction = canCreateAuction;
	}

	private static final Logger log = Logger.getLogger(Player.class);

	Map<Integer, String> buildAvailable = new HashMap<Integer, String>();

	public Map<Integer, String> getBuildAvailable() {
		return buildAvailable;
	}

	public Player(User user, int position, int money, String color) {
		this.id = user.getId();
		this.name = user.getNickname();
		this.position = position;
		this.money = money;
		this.color = color;
	}
	public Player(String user, int position, int money, String color) {
		this.user = user;
		this.position = position;
		this.money = money;
		this.color = color;
	}
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

	public boolean isAuctionCreator() {
		return isAuctionCreator;
	}

	public void setAuctionCreator(boolean isAuctionCreator) {
		this.isAuctionCreator = isAuctionCreator;
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

	public int getAuctionRates() {
		return auctionRates;
	}

	public void setAuctionRates(int auctionRates) {
		this.auctionRates = auctionRates;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
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
		return position;

	}

	public int rollDicesAndWait() {
		//position = getPosition() + (rollDiceOne() + rollDiceTwo());
		position = rollDiceOne() + rollDiceTwo();
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
				property.add(new CityCard(cities, this));
			}
		}
		for (Rails rails : Rails.values()) {
			if (rails.getPosition() == player.getPosition()) {
				property.add(new RailCard(rails, this));
			}
		}
	}

	public void addForMortage() {
	}

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

	public Map<Integer, String> getMortageAvailable() {
		Map<Integer, String> mortageAvailable = new HashMap<Integer, String>();
		for (SellableCard p : this.getForMortage()) {
			mortageAvailable.put(p.getPosition(),
					"You can get $" + p.getPrice() / 2 + " after mortage");
		}
		return mortageAvailable;
	}

	public Map<Integer, String> getUnMortageAvailable() {
		Map<Integer, String> unMortageAvailable = new HashMap<Integer, String>();
		for (SellableCard p : this.getForUnMortage()) {
			unMortageAvailable.put(p.getPosition(),
					"You must pay $" + p.getPrice() / 2 + " for unmortage");
		}
		return unMortageAvailable;
	}

	public Map<Integer, String> getSellAvailable() {
		Map<Integer, String> sellAvailable = new HashMap<Integer, String>();
		for (SellableCard p : this.listPropertyForSell()) {
			sellAvailable.put(p.getPosition(), "You can get $" + p.getPrice()
					/ 2 + " after mortage");
		}
		return sellAvailable;
	}

	public Map<Integer, String> addBuildAvailable() {
		Map<Integer, String> buildAvailable = getBuildAvailable();
		buildAvailable.clear();
		CityCard city = null;
		for (SellableCard properties : property) {
			if (properties instanceof CityCard) {
				city = (CityCard) properties;
				if ((city.getRegion().equals("brown") || city.getRegion()
						.equals("blue"))
						&& this.getNumberOfRegions(this, city.getRegion()) == 2
						&& !city.isMortage()
						&& this.checkMoney(city.getHousePrice())
						&& city.getNumbersOfHouses() < 3) {
					buildAvailable.put(
							city.getPosition(),
							"Player " + this.getName()
									+ " can build house for $"
									+ city.getHousePrice());
				} else if ((city.getRegion().equals("brown") || city
						.getRegion().equals("blue"))
						&& this.getNumberOfRegions(this, city.getRegion()) == 2
						&& !city.isMortage()
						&& this.checkMoney(city.getHotelPrice())
						&& city.getNumbersOfHouses() == 3 && !city.isHotel()) {
					buildAvailable.put(
							city.getPosition(),
							"Player " + this.getName()
									+ " can build hotel for $"
									+ city.getHotelPrice());
				} else if (this.getNumberOfRegions(this, city.getRegion()) == 3
						&& !city.isMortage()
						&& this.checkMoney(city.getHousePrice())
						&& city.getNumbersOfHouses() < 3) {
					buildAvailable.put(
							city.getPosition(),
							" Player " + this.getName()
									+ " can build house for $"
									+ city.getHousePrice());
				} else if (this.getNumberOfRegions(this, city.getRegion()) == 3
						&& !city.isMortage()
						&& this.checkMoney(city.getHotelPrice())
						&& city.getNumbersOfHouses() == 3 && !city.isHotel()) {
					buildAvailable.put(
							city.getPosition(),
							"Player " + this.getName()
									+ " can build hotel for $"
									+ city.getHotelPrice());
				}
			}
		}
		return buildAvailable;
	}

	public List<SellableCard> getForUnMortage() {
		return forUnMortage;
	}

	public void listPropertyForMortage() {
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

	public Map<String, Object> currentPlayerState() {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("color", this.getColor());
		temp.put("position", this.getPosition());
		temp.put("money", this.getMoney());
		temp.put("nick", this.getName());
		return temp;
	}

	public boolean canMortage() {
		return !this.getForMortage().isEmpty();
		// return (!this.getForMortage().isEmpty()) ? true : false;
	}

	public boolean canUnmortage() {
		return !this.getForUnMortage().isEmpty() && this.getMoney() > 0;
		// return (!this.getForUnMortage().isEmpty() && this.getMoney() > 0) ?
		// true
		// : false;
	}

	public boolean canSell() {
		return !this.getSellAvailable().isEmpty();
		// return (!this.getSellAvailable().isEmpty()) ? true : false;
	}

	public boolean canRollDices() {
		boolean roll = false;
//		log.info(" getMoney>=0: "+(this.getMoney()>=0));
//		log.info(" double points: "+this.doublePoints());
//		log.info(" is rolled: "+this.isRolled());
		if (this.getMoney() >= 0 && this.doublePoints() && this.isRolled()) {
			roll = true;
		} else if (this.getMoney() >= 0 && !this.isRolled()) {
//			log.info(" getMoney>=0: "+(this.getMoney()>=0));
//			log.info(" is rolled: "+this.isRolled());
			roll = true;
		}
		return roll;
	}

	public boolean canContinueGame() {
//		log.info(" can mortage: "+this.canMortage());
//		log.info(" can sell: "+this.canSell());
		return this.canMortage() || this.canSell();
	}

	public boolean canBuild() {
		return !getBuildAvailable().isEmpty();
	}

	public boolean canAuction(int auctionPrice) {
		return this.checkMoney(auctionPrice);
	}
	
	public boolean canUpAuctionPrice(int upPrice){
		return this.checkMoney(this.getAuctionRates()+upPrice);
	}
	
	public boolean canBuy( SellableCard card){
		boolean buyState = false;
		if (!this.equals(card.getOwner())
				&& card.getOwner() != null && !card.isMortage()) {
			buyState = false;
		} else if (!this.equals(card.getOwner())
				&& card.getOwner() != null && card.isMortage()) {
			buyState = false;
		} else {
			buyState = card.canBuy(this);
		}
		if (this.equals(card.getOwner())) {
			buyState = false;
		}
		return buyState;
	}
	public String messages(SellableCard card){
		String messages=null;
		if (!this.equals(card.getOwner())
				&& card.getOwner() != null && !card.isMortage()) {
			messages = "Pay rent $"
					+ card.getRent(this, card.getOwner())
					+ " to " + card.getOwner().getName();
		} else if (this.equals(card.getOwner())) {
			messages = "Owner " + this.getName();
		} else if (!this.equals(card.getOwner())
				&& card.getOwner() != null && card.isMortage()) {
			messages = "Object is mortage";
		}
		return messages; 
	}
}
