package com.web.app.worldgames.domain.monopoly.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.Rails;
import com.web.app.worldgames.domain.monopoly.StartGame;

/**
 * 
 * @author Inna
 * 
 */
public class RailCard extends SellableCard {
	private String name;
	private int price;
	private int position;
	Rails rails = null;

	private final static Logger log = Logger.getLogger(RailCard.class);

	public RailCard(Rails rails) {
		this.name = rails.getName();
		this.price = rails.getPrice();
		this.position = rails.getPosition();
	}

	public RailCard(Rails rails, Player player) {
		this(rails);
		setOwner(player);
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rails == null) ? 0 : rails.hashCode());
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
		RailCard other = (RailCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rails != other.rails)
			return false;
		return true;
	}

	@Override
	public void effectOnPlayer(Player player) {
		if (this.getOwner() != null && !player.equals(this.getOwner())) {
			this.payRentToOwner(player, this.getOwner(),
					this.getRent(player, this.getOwner()));
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
	public int getRent(Player player, Player owner) {
		if (isMortage()) {
			player.setMoney(player.getMoney());
			player.setPosition(player.getPosition());
			return 0;
		} else {
			log.info("Owner of this rail is: " + this.getOwner().getName());
			int numberOfRails = owner.getNumberOfRails();
			log.info("Number of rails are: " + numberOfRails);
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
	public void buyCityOrRail(Player player) {
		this.setOwner(player);
		player.addProperty(this);
		player.listPropertyForMortage();
		player.listPropertyForSell();
		player.setMoney(player.getMoney() - getPrice());
		player.addNumberOfRails();
	}

	@Override
	public void sell(Player player) {
		log.info("OWNER BEFORE SELL RAIL " + this.getOwner());
		SellableCard sell_city = StartGame.boardRails.get(this.getPosition());
		sell_city.setOwner(null);
		log.info("OWNER AFTER SELL RAIL " + this.getOwner());
		player.setNumberOfRails(player.getNumberOfRails() - 1);
		player.setMoney(player.getMoney() + this.getPrice() / 2);
	}

	@Override
	public void auctionCityOrRail(Player player, Player auctionCreator,
			int price) {
		if (this.getOwner() == null) {
			this.setOwner(player);
			player.addProperty(this);
			player.listPropertyForMortage();
			player.listPropertyForSell();
			if (player.equals(auctionCreator)) {
				player.setMoney(player.getMoney() - price);
			} else {
				player.setMoney(player.getMoney() - price);
				auctionCreator.setMoney(auctionCreator.getMoney() + price);
			}
			player.addNumberOfRails();
			this.setAuctionStarted(false);
		}
	}

	public Map<String, Object> currentRailState() {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("position", this.getPosition());
		temp.put("owner", this.getOwner().getColor());
		temp.put("mortage", this.isMortage());
		return temp;
	}

	@Override
	public boolean canMortage(Player player) {
		return this.getOwner().equals(player);
	}

	@Override
	public boolean canUnMortage(Player player) {
		return this.getOwner().equals(player) && this.isMortage();
	}

	@Override
	public boolean canSell(Player player) {
		return !this.isMortage() && this.getOwner().equals(player);
	}
}
