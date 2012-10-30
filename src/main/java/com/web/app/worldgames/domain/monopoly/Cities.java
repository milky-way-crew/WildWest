package com.web.app.worldgames.domain.monopoly;

public enum Cities {
	VENESIA("Veneto", 50, 70, 90, 90, 0, 100, false,2),
	BARI("Apulia", 50, 70, 90, 100, 0, 110, false,3),
	SICILY("Syracuse", 50, 70, 90, 120, 0, 125, false,4),
	CORSICA("Ajaccio", 70, 100, 150, 120, 0, 125, false,7),
	SARDINIA("Cagliari", 40, 60, 110, 120, 0, 130, false,9),
	GAUL("Mediolane", 50, 89, 150, 160, 0, 140, false,10),
	ILIRIC("Solin", 50, 90, 140, 140, 0, 130, false,12),
	MACEDONIA("Dion", 60, 90, 120, 140, 0, 150, false,13),
	ACHAIA("Kalavryta", 40, 80, 120, 150, 0, 140, false,14),
	BITHYNIA("Nicomedia", 50, 80, 140, 130, 0, 120, false,16),
	ASSYRIA("Assur", 50, 80, 160, 140, 0, 160, false,17),
	BAETIC("Strabo", 30, 50, 110, 130, 0, 140, false,18),
	ITALY("Rome", 50, 100, 150, 160, 0, 150, false,),
	CRETE("Dasiti", 30, 70, 140, 130, 0, 150, false),
	CYPRUS("Nicosia", 40, 70, 130, 150, 0, 140, false),
	NUMIBIA("Tsyrta", 60, 90, 150, 120, 0, 110, false),
	APULIA("Cannes", 40, 70, 100, 110, 0, 100, false);
	
	private final String region;
	private final int taxOneCard;
	private final int taxTwoCard;
	private final int taxThreeCard;
	private final int castlePrice;
	private  int numbersOfCastles;
	private final int fortressPrice;
	private  boolean isFortress;
	private final int position;

	Cities(String region, int taxOneCard, int taxTwoCard, int taxThreeCard,
			int castlePrice, int numbersOfCastles, int fortressPrice,
			boolean isFortress, int positon) {
		this.region = region;
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.castlePrice = castlePrice;
		this.numbersOfCastles = numbersOfCastles;
		this.fortressPrice = fortressPrice;
		this.isFortress = isFortress;
		this.position = positon;
	}

	public int getPosition() {
		return position;
	}

	public void setNumbersOfCastles(int numbersOfCastles) {
		this.numbersOfCastles = numbersOfCastles;
	}

	public void setFortress(boolean isFortress) {
		this.isFortress = isFortress;
	}

	public String getRegion() {
		return region;
	}

	public int getTaxOneCard() {
		return taxOneCard;
	}

	public int getTaxTwoCard() {
		return taxTwoCard;
	}

	public int getTaxThreeCard() {
		return taxThreeCard;
	}

	public int getCastlePrice() {
		return castlePrice;
	}

	public int getNumbersOfCastles() {
		return numbersOfCastles;
	}

	public int getFortressPrice() {
		return fortressPrice;
	}

	public boolean isFortress() {
		return isFortress;
	}

}