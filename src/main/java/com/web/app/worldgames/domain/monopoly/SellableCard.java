package com.web.app.worldgames.domain.monopoly;

public abstract class SellableCard extends Cell {
	private int price;
	private boolean mortage;
	private Player owner;

	abstract void info();

	abstract int getRent(SellableCard cell, Player player, Player owner);

	abstract void payRentToOwner(Player player, Player owner, int price);

	abstract void payOrMortage(SellableCard cell, Player player, Player owner);

	abstract void buyCityOrRail(SellableCard cell, Player player);

	public boolean isMortage() {
		return mortage;
	}

	protected void setMortage(boolean mortage) {
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

	public void buyOrMortage(SellableCard cell, Player player) {
		boolean check = true;
		int price = cell.getPrice();
		if (player.checkMoney(player, price)) {
			buyCityOrRail(cell, player);
		} else {
			while (check) {
				if (!player.listPropertyForMortage(player).isEmpty()) {
					player.mortageAction(player);
					if (player.checkMoney(player, price)) {
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
