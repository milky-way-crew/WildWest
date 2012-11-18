package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.Player;
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
					.getOwner() != null) ? true : false;
	}

	public boolean canBuy(Player player) {
		return (player.checkMoney(this.getPrice()) && this.getOwner() == null && this instanceof SellableCard) ? true
				: false;
	}

//	public void refuse(Player player) {
//		// player.setMoney(player.getMoney());
//		player.setPosition(player.getPosition());
//	}

	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}

	public void sellCityOrRail(Player seller, Player buyer) {
		if (this.isMortage()) {
			log.info("[MESSAGE]: Unmortage this object");
		} else {
			seller.setMoney(seller.getMoney() + this.getPrice());
			buyer.setMoney(buyer.getMoney() - this.getPrice());
			this.setOwner(buyer);
			if (this instanceof CityCard) {
				seller.listRegions(seller).remove(
						seller.getRegion(this.getPosition()));
				buyer.addRegionsSellActivity(buyer, (CityCard) this);
			} else if (this instanceof RailCard) {
				seller.subNumberOfRAils();
				buyer.addNumberOfRails();
			}
			buyer.addSelledProperty(this);
			seller.deleteProperty(seller, this);
		}

	}

//	public void mortageAction(int playerId, int position) {
//		Player player = 
//		SellableCard card = player.cardByIndex(position);
//		card.mortage(player);
//		player.getForMortage().remove(card);
//		player.getForUnMortage().add(card);
//	}
	// public void buyOrMortage(SellableCard cell, Player player) {
	// boolean check = true;
	// int price = cell.getPrice();
	// if (player.checkMoney(price)) {
	// buyCityOrRail(player);
	// } else {
	// while (check) {
	// player.listPropertyForMortage(player);
	// if (!player.getForMortage().isEmpty()) {
	// player.mortageAction(player);
	// if (player.checkMoney(price)) {
	// buyCityOrRail(player);
	// check = false;
	// } else {
	// check = true;
	// }
	// } else {
	// System.out.println("you haven't object");
	// player.setLoss(true);
	// System.out.println("loss: " + player.isLoss());
	// check = false;
	// }
	//
	// }
	// }
	// }

}
