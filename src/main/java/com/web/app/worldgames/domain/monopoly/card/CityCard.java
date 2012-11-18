package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

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
		Map<String, Integer> buildings = new HashMap<String, Integer>();
		CityCard city = (CityCard) StartGame.boardCities().get(
				player.getPosition());
		if (getNumbersOfHouses() < 3) {

			if (canBuildHouse(player)) {
				player.setMoney(player.getMoney() - city.getHousePrice());
				numbersOfHouses++;
				setNumbersOfHouses(numbersOfHouses);
				player.setNumberOfBuildings(player.getNumberOfBuildings() + 1);
				buildings.put(city.getName(), city.getNumbersOfHouses());
				player.setBuildings(buildings);
			}
			log.info("[Message]: " + "You build castle. Number of houses are: "
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

	public boolean canBuildHotel(Player player) {
		return (getNumbersOfHouses() == 3 && !isMortage() && player
				.checkMoney(getHousePrice())) ? true : false;
	}

	public boolean buildHotel(Player player) {
		Map<String, Integer> buildings = new HashMap<String, Integer>();
		CityCard city = (CityCard) StartGame.boardCities().get(
				player.getPosition());
		if (canBuildHotel(player)) {
			log.info("[Message]: " + "You can build hotel");
			player.setMoney(player.getMoney() - city.getHotelPrice());
			setHotel(true);
			player.setNumberOfBuildings(player.getNumberOfBuildings() + 1);
			buildings.put(city.getName(), city.getNumbersOfHouses());
			player.setBuildings(buildings);
		} else {
			log.info("[Message]: " + "You cannot build hotel");
		}
		return isHotel();
	}

	@Override
	public void effectOnPlayer(Player player) {
		if (this.getOwner() != null) {
			if (this.canPayRent(player, this.getRent(player, this.getOwner()))) {
				log.info("[OWNER]: " + this.getOwner().getColor());
				this.payRentToOwner(player, this.getOwner(),
						this.getRent(player, this.getOwner()));
				log.info("[OWNER]: money" + this.getOwner().getMoney());
				log.info("[PLAYER]: money" + player.getMoney());
			}
		} else if (player == this.getOwner()) {
			log.info("[OWNER]: You are owner");
		}
	}

	@Override
	public int getRent(Player player, Player owner) {
		if (isMortage()) {
			player.setMoney(player.getMoney());
			player.setPosition(player.getPosition());
		} else {
			log.info("[OWNER]: " + this.getOwner().getName());
			int numberOfRegions = owner.getNumberOfRegions(owner,
					owner.getRegion(player.getPosition()));
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
		this.setOwner(player);
		player.addProperty(player);
		player.listPropertyForMortage();
		player.listPropertyForSell();
		log.info("[MESSAGE]: You are owner now");
		player.setMoney(player.getMoney() - getPrice());
		log.info("[MONEY]: " + player.getMoney());
		player.addRegions(player);
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

}
