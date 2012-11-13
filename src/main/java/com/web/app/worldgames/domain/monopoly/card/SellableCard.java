package com.web.app.worldgames.domain.monopoly.card;

import com.web.app.worldgames.domain.monopoly.Player;

public abstract class SellableCard extends Cell {
	private int price;
	private boolean mortage;
	private Player owner;

	public abstract int getRent(SellableCard cell, Player player, Player owner);

	public abstract void payRentToOwner(Player player, Player owner, int price);

	public abstract void payOrMortage(SellableCard cell, Player player,
			Player owner);

	public abstract void buyCityOrRail(SellableCard cell, Player player);
	public abstract boolean canMortage(Player player);
	public abstract boolean canUnMortage(Player player);

	public boolean isMortage() {
		return mortage;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public void mortage(Player player) {
		this.mortage = true;
		System.out.println("You mortage this object. ");
		player.setMoney(player.getMoney() + getPrice() / 2);
	}

	public void unMortage(Player player) {
		this.mortage = false;
		System.out.println("You unMortage this object. ");
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
				&& this.getOwner() != null && this instanceof SellableCard) ? true
				: false;
	}

	public boolean canBuy(Player player) {
		return (player.checkMoney(this.price) && this.getOwner() == null && this instanceof SellableCard) ? true
				: false;
	}

	public boolean canRefuse(Player player) {
		return (this.getOwner() == null) ? true : false;
	}
	public void refuse(Player player) {
		player.setMoney(player.getMoney());
		player.setPosition(player.getPosition());
	}

	public void buyOrMortage(SellableCard cell, Player player) {
		boolean check = true;
		int price = cell.getPrice();
		if (player.checkMoney(price)) {
			buyCityOrRail(cell, player);
		} else {
			while (check) {
				player.listPropertyForMortage(player);
				if (!player.getForMortage().isEmpty()) {
					player.mortageAction(player);
					if (player.checkMoney(price)) {
						buyCityOrRail(cell, player);
						check = false;
					} else {
						check = true;
					}
				} else {
					System.out.println("you haven't object");
					player.setLoss(true);
					System.out.println("loss: " + player.isLoss());
					check = false;
				}

			}
		}
	}

}
