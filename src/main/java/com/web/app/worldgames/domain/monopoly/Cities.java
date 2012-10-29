package com.web.app.worldgames.domain.monopoly;

public enum Cities {
	VENESIA("Veneto", 50, 100, 150, 150, 0, 100, false),
	BARI("Apulia", 40, 80, 150, 150, 0, 160, false),
	SICILY("Syracuse", 30, 70, 120, 120, 0, 140, false),
	CORSICA("Ajaccio", 70, 100, 150, 120, 0, 160, false),
	SARDINIA("Cagliari", 40, 60, 110, 120, 0, 130, false),
	GAUL("Mediolane", 50, 89, 150, 160, 0, 140, false),
	ILIRIC("Solin", 50, 90, 140, 140, 0, 130, false),
	MACEDONIA("Dion", 60, 90, 120, 140, 0, 150, false),
	ACHAIA("Kalavryta", 40, 80, 120, 150, 0, 140, false),
	BITHYNIA("Nicomedia", 50, 80, 140, 130, 0, 120, false),
	ASSYRIA("Assur", 50, 80, 160, 140, 0, 160, false),
	BAETIC("Strabo", 30, 50, 110, 130, 0, 140, false),
	ITALY("Rome", 50, 100, 150, 160, 0, 150, false),
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

	Cities(String region, int taxOneCard, int taxTwoCard, int taxThreeCard,
			int castlePrice, int numbersOfCastles, int fortressPrice,
			boolean isFortress) {
		this.region = region;
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.castlePrice = castlePrice;
		this.numbersOfCastles = numbersOfCastles;
		this.fortressPrice = fortressPrice;
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