package com.web.app.worldgames.domain.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.web.app.worldgames.domain.User;

public class Player {
	private String name;
	private int position;
	private int money;
	private boolean hasFreeCard;
	private static Random randDice = new Random();
	private List<String> listRegions = new ArrayList<String>();
	private int numberOfPorts = 0;

	public Player(User user, int position, int money, boolean hasFreeCard) {
		this.name = user.getNickname();
		this.position = position;
		this.money = money;
		this.hasFreeCard = hasFreeCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public boolean isHasFreeCard() {
		return hasFreeCard;
	}

	public void setHasFreeCard(boolean hasFreeCard) {
		this.hasFreeCard = hasFreeCard;
	}

	public static int throwDiceOne() {
		return randDice.nextInt(6) + 1;
	}

	public static int throwDiceTwo() {
		return randDice.nextInt(6) + 1;
	}

	public int throwDicesAndMove() {
		position = getPosition() + (throwDiceOne() + throwDiceTwo());
		if (position > 40) {
			position = position - 40;
		}
		setPosition(position);
		return position;

	}

	public static boolean doublePoints() {
		return (throwDiceOne() == throwDiceTwo()) ? true : false;
	}

	// public void action(SellableCard cell, Player player) {
	// cell.setOwner(player);
	// }

	public void addRegions(Player player, int position) {
		for (Cities cities : Cities.values()) {
			if (cities.getPosition() == player.getPosition()) {

				listRegions.add(cities.getRegion());
			}
		}
	}

	public List<String> listRegions(Player player) {
		return listRegions;

	}

	public String getRegion(int position) {
		String region = null;
		for (Cities regions : Cities.values()) {
			if (regions.getPosition() == position) {
				region = regions.getRegion();
			}
		}
		return region;
	}

	public int getNumberOfRegions(Player player, String region) {
		int regionsNumber = 0;
		for (String regions : listRegions(player)) {
			if (regions.equals(region)) {
				regionsNumber++;
			}
		}
		return regionsNumber;
	}

	public void addNumberOfPorts() {
		numberOfPorts++;
	}

	public int getNumberOfPorts() {
		return numberOfPorts;
	}

	public static Player getPlayer(Player player) {
		return player;
	}
}