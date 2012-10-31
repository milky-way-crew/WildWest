package com.web.app.worldgames.domain.monopoly;

public class PortCard extends SellableCard{
private int taxOneCard;
private int taxTwoCard;
private int taxThreeCard;
private int taxFourCard;

    private PortCard(int price, int taxOneCard, int taxTwoCard, int taxThreeCard,
		int taxFourCard) {
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
	//player.getMoney()
	
    }

	

}
