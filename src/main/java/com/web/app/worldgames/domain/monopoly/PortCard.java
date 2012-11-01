package com.web.app.worldgames.domain.monopoly;

public class PortCard extends SellableCard {
	private int taxOneCard;
	private int taxTwoCard;
	private int taxThreeCard;
	private int taxFourCard;

	public PortCard(int price, int taxOneCard, int taxTwoCard,
			int taxThreeCard, int taxFourCard) {
		super();
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.taxFourCard = taxFourCard;
	}

	public int getTaxOneCard() {
		return taxOneCard;
	}

	public void setTaxOneCard(int taxOneCard) {
		this.taxOneCard = taxOneCard;
	}

	public int getTaxTwoCard() {
		return taxTwoCard;
	}

	public void setTaxTwoCard(int taxTwoCard) {
		this.taxTwoCard = taxTwoCard;
	}

	public int getTaxThreeCard() {
		return taxThreeCard;
	}

	public void setTaxThreeCard(int taxThreeCard) {
		this.taxThreeCard = taxThreeCard;
	}

	public int getTaxFourCard() {
		return taxFourCard;
	}

	public void setTaxFourCard(int taxFourCard) {
		this.taxFourCard = taxFourCard;
	}

	@Override
	void effectOnPlayer(Player player) {
		Player owner = null;
		Cell cell = StartGame.board().get(player.getPosition());
		if (GameBoard.hasOwner(cell)) {
			owner = GameBoard.owner(cell);
			int numberOfPorts = owner.getNumberOfPorts();
			if (numberOfPorts == 1) {
				player.setMoney(player.getMoney() - getTaxOneCard());
				owner.setMoney(owner.getMoney() + getTaxOneCard());
			} else if (numberOfPorts == 2) {
				player.setMoney(player.getMoney() - getTaxTwoCard());
				owner.setMoney(owner.getMoney() + getTaxTwoCard());
			} else if (numberOfPorts == 3) {
				player.setMoney(player.getMoney() - getTaxThreeCard());
				owner.setMoney(owner.getMoney() + getTaxThreeCard());
			} else if (numberOfPorts == 4) {
				player.setMoney(player.getMoney() - getTaxFourCard());
				owner.setMoney(owner.getMoney() + getTaxFourCard());
			}
		} else {
			player.setMoney(player.getMoney() - getPrice());
			owner = (Player) GameBoard.addOwners(player, cell);
			player.addNumberOfPorts();
		}
	}

}
