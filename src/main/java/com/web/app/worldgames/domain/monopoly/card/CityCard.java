package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.Cities;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class CityCard extends SellableCard {
	private String name;
	private final String region;
	private int price;
	private final int taxOneCard;
	private final int taxTwoCard;
	private final int taxThreeCard;
	private int housePrice;
	private int numbersOfHouses;
	private int taxOneHouse;
	private int taxTwoHouse;
	private int taxThreeHouse;
	private int hotelPrice;
	private boolean isHotel;
	private int taxHotel;
	private int position;

	//private final static Logger LOG = Logger.getLogger(CityCard.class);

	Cities cities = null;

	public CityCard(Cities cities) {
		this.name = cities.getCityName();
		this.region = cities.getRegion();
		this.price = cities.getPrice();
		this.taxOneCard = cities.getTaxOneCard();
		this.taxTwoCard = cities.getTaxTwoCard();
		this.taxThreeCard = cities.getTaxThreeCard();
		this.housePrice = cities.getHousePrice();
		this.numbersOfHouses = cities.getNumbersOfHouses();
		this.taxOneHouse = cities.getTaxOneHouse();
		this.taxTwoHouse = cities.getTaxTwoHouse();
		this.taxThreeHouse = cities.getTaxThreeHouse();
		this.hotelPrice = cities.getHotelPrice();
		this.taxHotel = cities.getTaxHotel();
		this.isHotel = cities.isHotel();
		this.position = cities.getPosition();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public int getTaxOneCard() {
		return taxOneCard;
	}

	public int getTaxTwoCard() {
		return taxTwoCard;
	}

	public int getTaxThreeCard() {
		return taxThreeCard;
	}

	public int getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	public int getNumbersOfHouses() {
		return numbersOfHouses;
	}

	public void setNumbersOfHouses(int numbersOfHouses) {
		this.numbersOfHouses = numbersOfHouses;
	}

	public int getTaxOneHouse() {
		return taxOneHouse;
	}

	public void setTaxOneHouse(int taxOneHouse) {
		this.taxOneHouse = taxOneHouse;
	}

	public int getTaxTwoHouse() {
		return taxTwoHouse;
	}

	public void setTaxTwoHouse(int taxTwoHouse) {
		this.taxTwoHouse = taxTwoHouse;
	}

	public int getTaxThreeHouse() {
		return taxThreeHouse;
	}

	public void setTaxThreeHouse(int taxThreeHouse) {
		this.taxThreeHouse = taxThreeHouse;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(int hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public boolean isHotel() {
		return isHotel;
	}

	public void setHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}

	public int getTaxHotel() {
		return taxHotel;
	}

	public void setTaxHotel(int taxHotel) {
		this.taxHotel = taxHotel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Cities getCities() {
		return cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	public void buildHouse(Player player) {
		CityCard city = (CityCard) StartGame.boardCities().get(
				player.getPosition());
		if (getNumbersOfHouses() < 3) {

			if (canBuildHouse(player)) {
				player.setMoney(player.getMoney() - city.getHousePrice());
				numbersOfHouses++;
				setNumbersOfHouses(numbersOfHouses);
			}
			System.out.println("You build castle. Number of houses are: "
					+ getNumbersOfHouses());
		}
	}

	public boolean canBuildHouse(Player player) {
		if ((region.equals("brown") || region.equals("blue"))
				&& player.getNumberOfRegions(player,
						player.getRegion(player.getPosition())) == 2
				&& !isMortage() && player.checkMoney(getHousePrice())) {
			return true;
		} else if (player.getNumberOfRegions(player,
				player.getRegion(player.getPosition())) == 3
				&& !isMortage() && player.checkMoney(getHousePrice())) {
			return true;
		} else
			return false;
	}

	public boolean buildHotel(Player player) {
		CityCard city = (CityCard) StartGame.boardCities().get(
				player.getPosition());
		if (getNumbersOfHouses() == 3 && !isMortage()
				&& player.checkMoney(getHousePrice())) {
			System.out.println("You can build hotel");
			player.setMoney(player.getMoney() - city.getHotelPrice());
			setHotel(true);
			System.out.println("You build hotel.");
		} else {
			System.out.println("You cannot build hotel");
		}
		return isHotel();
	}

	@Override
	public void effectOnPlayer(Player player) {
		Player owner = null;
		CityCard cell = (CityCard) StartGame.boardCities().get(
				player.getPosition());
		System.out.println(info());
		String action = null;
		// if city haven't owner player may buy this city or not
		if (cell.getOwner() == null) {
			System.out.println("You can buy it. Press 1 if you agree");
			action = player.playerAction();
			if (action.equals("1")) {
				cell.buyOrMortage(cell, player);
			}
		} else {
			owner = cell.getOwner();
			// if owner isn't player
			if (!owner.equals(player)) {
				// if this city isn't mortage player pay rent to owner else no
				// action
				payOrMortage(cell, player, owner);
			} else if (owner.equals(player)) {
				if (canBuildHouse(player)) {
					System.out
							.println("You can build house. Press: 1 - build house. 2 -  build hotel)");
					action = player.playerAction();
					if (action.equals("1")) {
						buildHouse(player);

					} else if (action.equals("2")) {
						buildHotel(player);
					}
				}
			}
		}
	}

	@Override
	public void payOrMortage(SellableCard cell, Player player, Player owner) {
		boolean check = true;
		int price = getRent(cell, player, owner);
		if (player.checkMoney(price)) {
			payRentToOwner(player, owner, price);
		} else {
			while (check) {
				player.mortageAction(player);
				if (player.checkMoney(price)) {
					payRentToOwner(player, owner, price);
					check = false;
				} else {
					check = true;
				}

			}
		}
	}

	// public boolean canPayRent(){
	//
	// }
	@Override
	public int getRent(SellableCard cell, Player player, Player owner) {
		if (isMortage()) {
			player.setMoney(player.getMoney());
			player.setPosition(player.getPosition());
		} else {
			System.out.println("Owner of this city is: "
					+ cell.getOwner().getName());
			int numberOfRegions = owner.getNumberOfRegions(owner,
					owner.getRegion(player.getPosition()));
			System.out.println(" Number of regions are: " + numberOfRegions);
			if (numberOfRegions == 1) {
				return getTaxOneCard();
			} else if (numberOfRegions == 2) {
				return getTaxTwoCard();
			} else if (numberOfRegions == 3) {

				if (getNumbersOfHouses() == 1) {
					return getTaxThreeCard() + getTaxOneHouse();
				} else if (getNumbersOfHouses() == 2) {
					return getTaxThreeCard() + getTaxTwoHouse();
				} else if (getNumbersOfHouses() == 3) {
					return getTaxThreeCard() + getTaxThreeHouse();
				}
				if (isHotel()) {
					return getTaxHotel();
				}
			}
		}
		return 0;
	}

	@Override
	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}

	@Override
	public String info() {
		return "\t" + getName() + "\n\tregion: " + getRegion() + "\n\tprice "
				+ getPrice() + "\n\towner: " + getOwner() + "\n\tmortage: "
				+ isMortage() + "\n\t tax1: " + getTaxOneCard() + " tax2: "
				+ getTaxTwoCard() + " tax3: " + getTaxThreeCard()
				+ "\n\thouses: " + getNumbersOfHouses() + "house price: "
				+ getHousePrice() + "\n\ttax1 house: " + getTaxOneHouse()
				+ " tax2 house: " + getTaxTwoHouse() + " tax3 house: "
				+ getTaxThreeHouse() + "\n\thoutel price: " + getHotelPrice()
				+ " is hotel: " + isHotel() + " tax hotel: " + getTaxHotel();
	}

	@Override
	public void buyCityOrRail(SellableCard cell, Player player) {
		cell.setOwner(player);
		player.addProperty(player);
		System.out.println("You are owner now");
		player.setMoney(player.getMoney() - getPrice());
		System.out.println("Your money: " + player.getMoney());
		player.addRegions(player);
		System.out.println("Your regions: " + player.listRegions(player));

	}

	@Override
	public boolean canMortage(Player player) {
		return (this.numbersOfHouses == 0 && !this.isHotel && this.getOwner() == player) ? true
				: false;
	}

	@Override
	public boolean canUnMortage(Player player) {
		return (this.getOwner()==player&& this.isMortage());
	}


	@Override
	public Map<String, ? extends Object> action(Player player, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
