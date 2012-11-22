package com.web.app.worldgames.domain.monopoly.card;

import java.util.List;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Cities;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.PlayerColors;
import com.web.app.worldgames.domain.monopoly.Rails;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public abstract class SellableCard extends Cell {
	private int price;
	private boolean mortage;
	private Player owner;

	public abstract int getRent(Player player, Player owner);

	// public abstract void payRentToOwner(Player player, Player owner, int
	// price);

	// public abstract void payOrMortage(SellableCard cell, Player player,
	// Player owner);

	public abstract void buyCityOrRail(Player player);
	public abstract void sell(Player player);

	public abstract boolean canMortage(Player player);

	public abstract boolean canUnMortage(Player player);

	public abstract boolean canSell(Player player);

	private static final Logger log = Logger.getLogger(MonopolyManager.class);

	public boolean isMortage() {
		return mortage;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public void mortage(Player player) {
		this.mortage = true;
		log.info("[MORTAGE MESSAGE]: You mortage this object");
		player.setMoney(player.getMoney() + getPrice() / 2);
	}

	public void unMortage(Player player) {
		this.mortage = false;
		log.info("[UNMORTAGE MESSAGE]: You unmortage this object");
		player.setMoney(player.getMoney() - getPrice() / 2);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean canPayRent(Player player, int rent) {
		return (player.checkMoney(rent) && this.getOwner() != player
				&& this.getOwner() != null && this instanceof SellableCard && this
					.getOwner() != null&&!this.isMortage()) ? true : false;
	}

	public boolean canBuy(Player player) {
		return (player.checkMoney(this.getPrice()) && this.getOwner() == null && this instanceof SellableCard) ? true
				: false;
	}

	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}

//	public void sellCityOrRail(Player player) {
//		RailCard rail = null;
//		CityCard city = null;
//		for (SellableCard cards : player.getForSell()) {
//			log.info("[-----------------SELL ACTION------------------------------]: ");
//			if (this instanceof RailCard) {
//				rail = (RailCard) cards;
//				rail.setOwner(null);
//				player.setNumberOfRails(player.getNumberOfRails() - 1);
//				player.setMoney(player.getMoney() + rail.getPrice() / 2);
//			} else if (this instanceof CityCard) {
//				city = (CityCard) cards;
//				if (city.getNumbersOfHouses() > 0) {
//					city.setNumbersOfHouses(city.getNumbersOfHouses() - 1);
//					player.setMoney(player.getMoney() + city.getHousePrice()
//							/ 2);
//					if (city.isHotel()) {
//						city.setHotel(false);
//						player.setMoney(player.getMoney()
//								+ city.getHotelPrice() / 2);
//					}
//				} else {
//					city.setOwner(null);
//					player.setMoney(player.getMoney() + city.getPrice() / 2);
//					player.listRegions(player).remove(city.getRegion());
//					// player.addBuildAvailable();
//				}
//			}
//		}
//	}

	// public void sellCityOrRail(Player seller, Player buyer) {
	// if (this.isMortage()) {
	// log.info("[MESSAGE]: Unmortage this object");
	// } else {
	// seller.setMoney(seller.getMoney() + this.getPrice());
	// buyer.setMoney(buyer.getMoney() - this.getPrice());
	// this.setOwner(buyer);
	// if (this instanceof CityCard) {
	// seller.listRegions(seller).remove(
	// seller.getRegion(this.getPosition()));
	// buyer.addRegionsSellActivity(buyer, (CityCard) this);
	// } else if (this instanceof RailCard) {
	// seller.subNumberOfRAils();
	// buyer.addNumberOfRails();
	// }
	// buyer.addSelledProperty(this);
	// seller.deleteProperty(seller, this);
	// }
	//
	// }
	// public static void main(String[] args) {
	// Player p = new Player("ajsdhc", 12, 1000, PlayerColors.PLAYER1);
	// CityCard c1 = new CityCard(Cities.ATHENS);
	// //RailCard c = new RailCard(Rails.RAIL1);
	// //System.out.println(c.info()+" pos "+c.getPosition());
	// c1.buyCityOrRail(p);
	// // c.buyCityOrRail(p);
	// p.playerProperty().clear();
	// System.out.println(p.playerProperty());
	// for(SellableCard pp:p.playerProperty()){
	//
	// pp.sellCityOrRail(p);
	// }
	//
	// // c.sellCityOrRail(p);
	// }
}
