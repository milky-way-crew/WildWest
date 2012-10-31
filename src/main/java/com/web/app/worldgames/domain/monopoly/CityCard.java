package com.web.app.worldgames.domain.monopoly;

public class CityCard extends SellableCard {
	private String region;
	private int taxOneCard;
	private int taxTwoCard;
	private int taxThreeCard;
	private int castlePrice;
	private int numbersOfCastles;
	private int fortressPrice;
	private boolean isFortress;

	public CityCard(String region, int taxOneCard, int taxTwoCard,
			int taxThreeCard, int castlePrice, int numberOfCastles,
			int fortressPrice, boolean isFortress) {
		super();
		this.region = region;
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.castlePrice = castlePrice;
		this.numbersOfCastles = numberOfCastles;
		this.fortressPrice = fortressPrice;
		this.isFortress = isFortress;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public int getCastlePrice() {
		return castlePrice;
	}

	public int getNumbersOfCastles() {
		return numbersOfCastles;
	}

	public void setCastlePrice(int castlePrice) {
		this.castlePrice = castlePrice;
	}

	public int getFortressPrice() {
		return fortressPrice;
	}

	public void setFortressPrice(int fortressPrice) {
		this.fortressPrice = fortressPrice;
	}

	public boolean isFortress() {
		return isFortress;
	}

	public void setFortress(boolean isFortress) {
		this.isFortress = isFortress;
	}

	public void setNumbersOfCastles(int numbersOfCastles) {
		this.numbersOfCastles = numbersOfCastles;
	}

	

	public boolean buildCastle(Player player) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean buildFortress(Player player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	void effectOnPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
}
