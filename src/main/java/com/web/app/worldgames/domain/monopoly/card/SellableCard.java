package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.game.MonopolyManager;

public abstract class SellableCard extends Cell {
	private int price;
	private boolean mortage;
	private Player owner;

	public abstract int getRent(Player player, Player owner);

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

	// public boolean canPayRent(Player player, int rent) {
	// return (player.checkMoney(rent) && this.getOwner() != player
	// && this.getOwner() != null && this instanceof SellableCard && this
	// .getOwner() != null&&!this.isMortage()) ? true : false;
	// }

	public boolean canBuy(Player player) {
		return (player.checkMoney(this.getPrice()) && this.getOwner() == null && this instanceof SellableCard) ? true
				: false;
	}

	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}
}
