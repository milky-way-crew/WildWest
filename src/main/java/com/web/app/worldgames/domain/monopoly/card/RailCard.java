package com.web.app.worldgames.domain.monopoly.card;

import org.apache.log4j.Logger;

import com.web.app.worldgames.domain.monopoly.CardPrices;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.Rails;
import com.web.app.worldgames.domain.monopoly.StartGame;

public class RailCard extends SellableCard {
	private String name;
	private int price;
	private int position;
	private final static Logger log = Logger.getLogger(RailCard.class);
	Rails rails = null;

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
		if (this.getOwner() != null&&player!=this.getOwner()) {
			//if (this.canPayRent(player, this.getRent(player, this.getOwner()))) {
				log.info("[OWNER]: " + this.getOwner().getColor());
				this.payRentToOwner(player, this.getOwner(),
						this.getRent(player, this.getOwner()));
				log.info("[OWNER]:  money" + this.getOwner().getMoney());
				log.info("[PLAYER]: money" + player.getMoney());
			//}
		} else if (player == this.getOwner()) {
			log.info("[OWNER]: You are owner");
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
			System.out.println("Owner of this rail is: "
					+ this.getOwner().getName());
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
	public void buyCityOrRail(Player player) {
		this.setOwner(player);
		player.addProperty(player);
		player.listPropertyForMortage();
		player.listPropertyForSell();
		System.out.println("You are owner now");
		player.setMoney(player.getMoney() - getPrice());
		System.out.println("Your money: " + player.getMoney());
		player.addNumberOfRails();
		System.out.println("Your have ports number: "
				+ player.getNumberOfRails());
	}

	@Override
	public boolean canMortage(Player player) {
		return (this.getOwner() == player) ? true : false;
	}

	@Override
	public boolean canUnMortage(Player player) {
		return (this.getOwner() == player && this.isMortage());
	}

	@Override
	public boolean canSell(Player player) {
		return (!this.isMortage() && this.getOwner() == player) ? true : false;
	}

	@Override
	public void sell(Player player) {
		log.info("OWNER BEFORE SELL RAIL "+this.getOwner());
		//this.setOwner(null);
		SellableCard sell_city = StartGame.boardRails.get(this.getPosition());
		log.info("POSIOTION OF SELLABLE CARD" + this.getPosition()
				+ " sell_city: " + sell_city);
		sell_city.setOwner(null);
		log.info("NOW OWNER OF THIS CITY: "+sell_city.getOwner());
		log.info("OWNER AFTER SELL RAIL "+this.getOwner());
		player.setNumberOfRails(player.getNumberOfRails() - 1);
		player.setMoney(player.getMoney() + this.getPrice() / 2);
	}

	@Override
	public void auctionCityOrRail(Player player, int price) {
		if (this.getOwner() == null) {
		this.setOwner(player);
		player.addProperty(player);
		player.listPropertyForMortage();
		player.listPropertyForSell();
		System.out.println("You are owner now");
		player.setMoney(player.getMoney() - price);
		System.out.println("Your money: " + player.getMoney());
		player.addNumberOfRails();
		System.out.println("Your have ports number: "
				+ player.getNumberOfRails());
		} else {
			log.info("is owner");
		}
	}

}
