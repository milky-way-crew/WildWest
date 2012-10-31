package com.web.app.worldgames.domain.monopoly;

public enum Cities {
	VENESIA("Veneto", 50, 70, 90, 90, 0, 100, false,CellPositions.VENESIA),
	BARI("Apulia", 50, 70, 90, 100, 0, 110, false,CellPositions.BARI),
	SICILY("Syracuse", 50, 70, 90, 120, 0, 125, false,CellPositions.SICILY),
	CORSICA("Ajaccio", 70, 100, 150, 120, 0, 125, false,CellPositions.CORSICA),
	SARDINIA("Cagliari", 40, 60, 110, 120, 0, 130, false,CellPositions.SARDINIA),
	GAUL("Mediolane", 50, 89, 150, 160, 0, 140, false,CellPositions.GAUL),
	ILIRIC("Solin", 50, 90, 140, 140, 0, 130, false,CellPositions.ILIRIC),
	MACEDONIA("Dion", 60, 90, 120, 140, 0, 150, false,CellPositions.MACEDONIA),
	ACHAIA("Kalavryta", 40, 80, 120, 150, 0, 140, false,CellPositions.ACHAIA),
	BITHYNIA("Nicomedia", 50, 80, 140, 130, 0, 120, false,CellPositions.BITHYNIA),
	ASSYRIA("Assur", 50, 80, 160, 140, 0, 160, false,CellPositions.ASSYRIA),
	BAETIC("Strabo", 30, 50, 110, 130, 0, 140, false,CellPositions.BAETIC);

	
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