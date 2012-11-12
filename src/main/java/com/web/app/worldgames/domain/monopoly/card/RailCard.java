package com.web.app.worldgames.domain.monopoly.card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.Rails;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class RailCard extends SellableCard {
	private String name;
	private int price;
	private final static Logger LOG = Logger.getLogger(RailCard.class);
	Rails rails = null;

	public RailCard(Rails rails) {
		this.name = rails.getName();
		this.price = rails.getPrice();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void effectOnPlayer(Player player) {
		Player owner = null;
		SellableCard cell = StartGame.boardRails().get(player.getPosition());
		System.out.println(info());
		String action = null;
		// if rail hasn't owner player may buy this rail or not
		if (cell.getOwner() == null) {
			System.out.println("You can buy it. Press 1 if you agree");
			action = player.playerAction();
			if (action.equals("1")) {
				cell.buyOrMortage(cell, player);
			} else {
				System.out.println("You refuse.");
				player.setPosition(player.getPosition());
				player.setMoney(player.getMoney());
			}
		} else {
			owner = cell.getOwner();
			// if owner isn't player
			if (!owner.equals(player)) {
				// if this rail isn't mortage player pay rent
				payOrMortage(cell, player, owner);
			} else if (owner.equals(player)) {
				// if player is owner of thia rail he can mortage it
				System.out.println("You are owner of this rail.");
				player.setMoney(player.getMoney());
				player.setPosition(player.getPosition());
			}
		}
	}

	@Override
	public String info() {
		return "\n\t price " + getPrice() + "\n\t owner: " + getOwner()
				+ " tax1: " + CardPrices.TAX_ONE_RAIL_CARD + " tax2: "
				+ CardPrices.TAX_TWO_RAIL_CARD + " tax3: "
				+ CardPrices.TAX_THREE_RAIL_CARD + " tax4: "
				+ CardPrices.TAX_FOUR_RAIL_CARD;
	}

	@Override
	public void payRentToOwner(Player player, Player owner, int price) {
		player.setMoney(player.getMoney() - price);
		owner.setMoney(owner.getMoney() + price);
	}

	@Override
	public int getRent(SellableCard cell, Player player, Player owner) {
		if (isMortage()) {
			player.setMoney(player.getMoney());
			player.setPosition(player.getPosition());
			return 0;
		} else {
			System.out.println("Owner of this rail is: "
					+ cell.getOwner().getName());
			int numberOfRails = owner.getNumberOfRails();
			System.out.println("Number of rails are: " + numberOfRails);
			if (numberOfRails == 1) {
				return CardPrices.TAX_ONE_RAIL_CARD;

			} else if (numberOfRails == 2) {
				return CardPrices.TAX_TWO_RAIL_CARD;
			} else if (numberOfRails == 3) {
				return CardPrices.TAX_THREE_RAIL_CARD;
			} else if (numberOfRails == 4) {
				return CardPrices.TAX_FOUR_RAIL_CARD;
			}
		}
		return 0;
	}

	@Override
	public void payOrMortage(SellableCard cell, Player player, Player owner) {
		boolean check = true;
		int price = getRent(cell, player, owner);
		if (player.checkMoney(player, price)) {
			payRentToOwner(player, owner, price);
		} else {
			while (check) {
				player.mortageAction(player);
				if (player.checkMoney(player, price)) {
					payRentToOwner(player, owner, price);
					check = false;
				} else {
					check = true;
				}

			}
		}
	}

	@Override
	public void buyCityOrRail(SellableCard cell, Player player) {
		cell.setOwner(player);
		player.addProperty(player);
		System.out.println("You are owner now");
		player.setMoney(player.getMoney() - getPrice());
		System.out.println("Your money: " + player.getMoney());
		player.addNumberOfRails();
		System.out.println("Your have ports number: "
				+ player.getNumberOfRails());
	}

	@Override
	public void refuse(SellableCard cell, Player player) {
		player.setPosition(player.getPosition());
		player.setMoney(player.getMoney());
	}

}
