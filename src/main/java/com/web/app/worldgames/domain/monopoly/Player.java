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