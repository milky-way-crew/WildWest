package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.Player;

public abstract class SellableCard extends Cell {
	/**
	 * @param player
	 *            who pay rent
	 * @param owner
	 *            player who get rent
	 * @return rent
	 */
	public abstract int getRent(Player player, Player owner);

	/**
	 * The execute method buy city; add this city to players property list; add
	 * to mortage list; add to sell list
	 * 
	 * @param player
	 *            who buy city
	 */
	public abstract void buyCityOrRail(Player player);

	/**
	 * The execute method holds an auction
	 * 
	 * @param player
	 *            winner of auction
	 * @param price
	 *            of purchase
	 */
	public abstract void auctionCityOrRail(Player player, Player auctionCreator, int price);

	/**
	 * The execute method sell city, houses and hotel
	 * 
	 * @param player
	 *            who sell this property
	 */
	public abstract void sell(Player player);

	public abstract boolean canMortage(Player player);

	public abstract boolean canUnMortage(Player player);

	public abstract boolean canSell(Player player);

	
	private int price;
	private boolean mortage;
	private Player owner;
	private boolean auctionStarted;
	private boolean auctionEnded;

	public boolean isMortage() {
		return mortage;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public void mortage(Player player) {
		this.mortage = true;
		player.setMoney(player.getMoney() + getPrice() / 2);
	}

	public void unMortage(Player player) {
		this.mortage = false;
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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + price;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SellableCard other = (SellableCard) obj;
//		if (price != other.price)
//			return false;
//		return true;
//	}

	public boolean isAuctionStarted() {
		return auctionStarted;
	}

	public void setAuctionStarted(boolean auctionStarted) {
		this.auctionStarted = auctionStarted;
	}

	public boolean isAuctionEnded() {
		return auctionEnded;
	}

	public void setAuctionEnded(boolean auctionEnded) {
		this.auctionEnded = auctionEnded;
	}

	public boolean canBuy(Player player) {
		return player.checkMoney(this.getPrice()) && this.getOwner() == null
				&& this instanceof SellableCard;
		// return (player.checkMoney(this.getPrice()) && this.getOwner() == null
		// && this instanceof SellableCard) ? true
		// : false;
	}

	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}
}
