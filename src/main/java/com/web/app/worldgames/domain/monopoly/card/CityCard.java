package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Cities;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
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
	private final static Logger log = Logger.getLogger(CityCard.class);
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

	public CityCard(Cities cities, Player owner) {
		this(cities);
		setOwner(owner);
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

	/**
	 * 
	 * @param player
	 * @return number of buildings on defined position
	 */
	public Map<Integer, Integer> build(Player player) {
		Map<Integer, Integer> buildings = new HashMap<Integer, Integer>();
		if (player.canBuild()) {
			// build house
			if (this.getNumbersOfHouses() < 3) {
				player.setMoney(player.getMoney() - this.getHousePrice());
				numbersOfHouses++;
				setNumbersOfHouses(numbersOfHouses);
				player.setNumberOfBuildings(player.getNumberOfBuildings() + 1);
				buildings.put(this.getPosition(), this.getNumbersOfHouses());
				player.setBuildings(buildings);
				log.info("[Message]: "
						+ "You build house. Number of houses are: "
						+ getNumbersOfHouses());
			} else if (this.getNumbersOfHouses() == 3) {
				// build hotel
				player.setMoney(player.getMoney() - this.getHotelPrice());
				setHotel(true);
				player.setNumberOfBuildings(player.getNumberOfBuildings() + 1);
				buildings
						.put(this.getPosition(), this.getNumbersOfHouses() + 1);
				player.setBuildings(buildings);
				log.info("[Message]: " + "You build houtel. Number of  are: "
						+ getNumbersOfHouses());
			}

		}
		return buildings;
	}

	@Override
	public void effectOnPlayer(Player player) {
		if (this.getOwner() != null) {
			log.info("[OWNER before effect]: money"
					+ this.getOwner().getMoney());
			log.info("[PLAYER before effect]: money" + player.getMoney());
			if (this.canPayRent(player, this.getRent(player, this.getOwner()))) {
				log.info("[OWNER]: " + this.getOwner().getColor());
				log.info("MORTAGE: " + this.isMortage());
				this.payRentToOwner(player, this.getOwner(),
						this.getRent(player, this.getOwner()));
				log.info("[OWNER]: money" + this.getOwner().getMoney());
				log.info("[PLAYER]: money" + player.getMoney());
			} else {
				player.setMoney(player.getMoney());
			}
		} else if (player == this.getOwner()) {
			log.info("[OWNER]: You are owner");
		}
	}

	@Override
	public int getRent(Player player, Player owner) {
		if (this.isMortage()
				&& owner.getForUnMortage().contains(this.getPosition())) {
			player.setMoney(player.getMoney());
			player.setPosition(player.getPosition());
			log.info("[CITY IS MORTAGE]: " + this.isMortage());
		} else if (!this.isMortage()
				&& owner.getForMortage().contains(this.getPosition())) {
			log.info("--------GET RENT TEST METHOD------FOR UNMORTAGE CONTAIN THIS OBJECT: "
					+ owner.getForMortage().contains(this.getPosition()));
			log.info("[OWNER]: " + this.getOwner().getName());
			int numberOfRegions = owner.getNumberOfRegions(owner,
					owner.getRegion(player.getPosition()));
			log.info("[NUMBER OF REGIONS NEW]: " + numberOfRegions);
			log.info("[REGIONS]: " + numberOfRegions);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cities == null) ? 0 : cities.hashCode());
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
		CityCard other = (CityCard) obj;
		if (cities != other.cities)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public void buyCityOrRail(Player player) {
		System.out.println("buyyy cityyy");
		this.setOwner(player);
		player.addProperty(player);
		player.listPropertyForMortage();
		player.listPropertyForSell();
		log.info("[MESSAGE]: You are owner now");
		player.setMoney(player.getMoney() - getPrice());
		log.info("[MONEY]: " + player.getMoney());
		log.info("[BUY::: OWNER]: " + this.getOwner());
		// player.addRegion();
		player.addRegions(player);
		player.addBuildAvailable();
		log.info("[BUILD AVAILABLE]: " + player.getBuildAvailable());
		log.info("[REGIONS LIST]: " + player.listRegions(player));

	}

	@Override
	public boolean canMortage(Player player) {
		return (this.numbersOfHouses == 0 && !this.isHotel && this.getOwner() == player) ? true
				: false;
	}

	@Override
	public boolean canUnMortage(Player player) {
		return (this.getOwner() == player && this.isMortage());
	}

	@Override
	public boolean canSell(Player player) {
		return (!this.isMortage() && this.getOwner() == player) ? true : false;
	}

	@Override
	public void sell(Player player) {
		log.info("--------------------------- IN SELL METHOD--------------");
		if (this.getNumbersOfHouses() > 0) {
			this.setNumbersOfHouses(this.getNumbersOfHouses() - 1);
			player.setMoney(player.getMoney() + this.getHousePrice() / 2);
			if (this.isHotel()) {
				this.setHotel(false);
				player.setMoney(player.getMoney() + this.getHotelPrice() / 2);
			}
		} else {
			log.info("OWNER BEFORE SELL CITY " + this.getOwner());
			SellableCard sell_city = StartGame.boardCities.get(this
					.getPosition());
			log.info("POSIOTION OF SELLABLE CARD" + this.getPosition()
					+ " sell_city: " + sell_city);
			sell_city.setOwner(null);
			log.info("NOW OWNER OF THIS CITY: " + sell_city.getOwner());
			log.info("OWNER AFTER SELL CITY " + this.getOwner());
			player.setMoney(player.getMoney() + this.getPrice() / 2);
			player.listRegions(player).remove(this.getRegion());
		}
	}

	public static void main(String[] args) {
		Player owner = new Player("ajsdhc", 12, 1000, PlayerColors.PLAYER1);
		Player p = new Player("ajsdhefdc", 15, 1000, PlayerColors.PLAYER2);
		CityCard c = new CityCard(Cities.ATHENS, null);
		// CityCard c1 = new CityCard(Cities.TOKYO);
		c.buyCityOrRail(owner);
		c.mortage(owner);
		c.effectOnPlayer(p);
	}
}
