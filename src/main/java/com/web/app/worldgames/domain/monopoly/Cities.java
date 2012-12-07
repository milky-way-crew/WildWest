package com.web.app.worldgames.domain.monopoly;

public enum Cities {
	GDYNIA("GDYNIA", "brown", 50, 20, 30, 50, 50, 0,20, 60,180, 50, false,430,CellPositions.GDYNIA), 
			TAIPEI("TAIPEI", "brown", 60, 20, 30, 50, 50, 0,30, 80,190, 50, false,450, CellPositions.TAIPEI),
			TOKYO("TOKYO", "azure",	100, 40, 60, 90, 50, 0,30, 90,270, 50, false, 550,CellPositions.TOKYO),
			BARCELONA("BARCELONA", "azure",100, 40, 60, 90, 50,  0,30, 90,270, 50, false,550,CellPositions.BARCELONA), 
			ATHENS("ATHENS", "azure", 120, 50, 70, 90, 70, 0,40, 100,300, 70, false, 600,CellPositions.ATHENS),
			ISTAMBUL("ISTAMBUL", "violet", 140, 50, 100, 150, 100, 0,50, 150,450, 100, false,750,CellPositions.ISTAMBUL),
			KYIV("KYIV", "violet", 140, 50, 100, 150, 100, 0,50, 150,450, 100, false, 750,CellPositions.KYIV), 
			TORONTO("TORONTO","violet",160, 90, 100, 160, 100, 0,60, 180,500, 100, false,900,CellPositions.TORONTO),
			ROME("ROME", "orange",180, 100, 170, 190, 100, 0,70, 200,550, 100, false, 950,CellPositions.ROME),
			SHANGHAI("SHANGHAI","orange",180, 100, 170, 190, 100, 0,70, 200,550, 100, false,950,CellPositions.SHANGHAI),
			VANCOVER("VANCOVER", "orange", 200, 110, 190, 210, 100, 0,80, 240,600, 100, false, 1000,CellPositions.VANCOVER),
			SYDNEY(	"SYDNEY", "red", 220, 150, 170, 200, 150, 0,90, 250,700, 150, false,1050,	CellPositions.SYDNEY),
			NEW_YORK("NEW_YORK", "red", 220, 150, 170, 200, 150, 0,90, 250,700, 150, false, 1050,CellPositions.NEW_YORK),
			LONDON("LONDON","red", 240, 170, 190,210, 150, 0,100, 300,750, 150,false, 1100,CellPositions.LONDON),
			BEIJING("BEIJING", "yellow", 260, 200, 270, 310, 150, 0,110, 330,800, 150,false,1150,CellPositions.BEIJING), 
			HONG_KONG("HONG_KONG", "yellow",260, 200, 270, 310, 150, 0,110, 330,800, 150, false,1150, CellPositions.HONG_KONG),
			JERUSALEM("JERUSALEM", "yellow", 280, 220, 300, 340, 150, 0,120, 360,850, 150, false,1200,CellPositions.JERUSALEM), 
			PARIS("PARIS", "green",300, 260, 300, 350, 200, 0,130, 390,900, 200, false, 1280,CellPositions.PARIS),
			BELGRADE("BELGRADE","green", 300, 260, 300, 350, 200, 0,130, 390,900, 200, false,1280,	CellPositions.BELGRADE),
			CAPE_TOWN("CAPE_TOWN", "green",330, 270, 300, 360, 200, 0,160, 450,1000, 200, false, 1300,CellPositions.CAPE_TOWN), 
			RIGA("RIGA","blue", 350, 290, 310, 400, 200, 0,180,500, 1100,200,  false, 1500,CellPositions.RIGA),
			MONTREAL("MONTREAL", "blue", 400, 300, 330, 410, 200, 0,200, 600,1400, 200, false,2000,CellPositions.MONTREAL);

	private final String cityName;
	private final String region;
	private final int price;
	private final int taxOneCard;
	private final int taxTwoCard;
	private final int taxThreeCard;
	private final int housePrice;
	private int numbersOfHouses;
	private final int taxOneHouse; 
	private final int taxTwoHouse; 
	private final int taxThreeHouse; 
	private final int hotelPrice;
	private boolean isHotel;
	private final int taxHotel; 
	private final int position;

	

	private Cities(String cityName, String region, int price, int taxOneCard,
			int taxTwoCard, int taxThreeCard, int housePrice,
			int numbersOfHouses, int taxOneHouse, int taxTwoHouse,
			int taxThreeHouse, int hotelPrice, boolean isHotel, int taxHotel,
			int position) {
		this.cityName = cityName;
		this.region = region;
		this.price = price;
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.housePrice = housePrice;
		this.numbersOfHouses = numbersOfHouses;
		this.taxOneHouse = taxOneHouse;
		this.taxTwoHouse = taxTwoHouse;
		this.taxThreeHouse = taxThreeHouse;
		this.hotelPrice = hotelPrice;
		this.isHotel = isHotel;
		this.taxHotel = taxHotel;
		this.position = position;
	}

	public String getCityName() {
		return cityName;
	}

	public String getRegion() {
		return region;
	}

	public int getPrice() {
		return price;
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

	public int getHousePrice() {
		return housePrice;
	}

	public int getNumbersOfHouses() {
		return numbersOfHouses;
	}

	public void setNumbersOfHouses(int numbersOfHouses) {
		this.numbersOfHouses = numbersOfHouses;
	}

	public int getTaxOneHouse() {
		return taxOneHouse;
	}

	public int getTaxTwoHouse() {
		return taxTwoHouse;
	}

	public int getTaxThreeHouse() {
		return taxThreeHouse;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public boolean isHotel() {
		return isHotel;
	}

	public void setHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}

	public int getTaxHotel() {
		return taxHotel;
	}

	public int getPosition() {
		return position;
	}

=======
package com.web.app.worldgames.domain.monopoly;

public enum Cities {
	TENT("TENT", "brown", 50, 20, 30, 50, 50, 0,20, 60,180, 50, false,430,CellPositions.TENT), 
	CIRCUS("CIRCUS", "brown", 60, 20, 30, 50, 50, 0,30, 80,190, 50, false,450, CellPositions.CIRCUS),
			POND("POND", "azure",	100, 40, 60, 90, 50, 0,30, 90,270, 50, false, 550,CellPositions.POND),
			FOUNTAIN("FOUNTAIN", "azure",100, 40, 60, 90, 50,  0,30, 90,270, 50, false,550,CellPositions.FOUNTAIN), 
			UNIVERSITY("UNIVERSITY", "azure", 120, 50, 70, 90, 70, 0,40, 100,300, 70, false, 600,CellPositions.UNIVERSITY),
			WINDMILL("WINDMILL ", "violet", 140, 50, 100, 150, 100, 0,50, 150,450, 100, false,750,CellPositions.WINDMILL),
			FISHERY("FISHERY", "violet", 140, 50, 100, 150, 100, 0,50, 150,450, 100, false, 750,CellPositions.FISHERY), 
			LITTLE_FARM("LITTLE_FARM","violet",160, 90, 100, 160, 100, 0,60, 180,500, 100, false,900,CellPositions.LITTLE_FARM),
			CATHEDRAL("CATHEDRAL", "orange",180, 100, 170, 190, 100, 0,70, 200,550, 100, false, 950,CellPositions.CATHEDRAL),
			MONASTERY("MONASTERY","orange",180, 100, 170, 190, 100, 0,70, 200,550, 100, false,950,CellPositions.MONASTERY),
			GRAVEYARD("GRAVEYARD", "orange", 200, 110, 190, 210, 100, 0,80, 240,600, 100, false, 1000,CellPositions.GRAVEYARD),
			FORT(	"FORT", "red", 220, 150, 170, 200, 150, 0,90, 250,700, 150, false,1050,	CellPositions.FORT),
			CITY("CITY", "red", 220, 150, 170, 200, 150, 0,90, 250,700, 150, false, 1050,CellPositions.CITY),
			FORTRESS("FORTRESS","red", 240, 170, 190,210, 150, 0,100, 300,750, 150,false, 1100,CellPositions.FORTRESS),
			OBELISK("OBELISK", "yellow", 260, 200, 270, 310, 150, 0,110, 330,800, 150,false,1150,CellPositions.OBELISK), 
			ARCH("ARCH", "yellow",260, 200, 270, 310, 150, 0,110, 330,800, 150, false,1150, CellPositions.ARCH),
			MAZE("MAZE", "yellow", 280, 220, 300, 340, 150, 0,120, 360,850, 150, false,1200,CellPositions.MAZE), 
			MINE("MINE", "green",300, 260, 300, 350, 200, 0,130, 390,900, 200, false, 1280,CellPositions.MINE),
			HUNTER("HUNTER","green", 300, 260, 300, 350, 200, 0,130, 390,900, 200, false,1280,	CellPositions.HUNTER),
			BLACKSMITH("BLACKSMITH", "green",330, 270, 300, 360, 200, 0,160, 450,1000, 200, false, 1300,CellPositions.BLACKSMITH), 
			SQUARE_TOWER("SQUARE_TOWER","blue", 350, 290, 310, 400, 200, 0,180,500, 1100,200,  false, 1500,CellPositions.SQUARE_TOWER),
			TOWER_ROUND("TOWER_ROUND", "blue", 400, 300, 330, 410, 200, 0,200, 600,1400, 200, false,2000,CellPositions.TOWER_ROUND);
	private final String cityName;
	private final String region;
	private final int price;
	private final int taxOneCard;
	private final int taxTwoCard;
	private final int taxThreeCard;
	private final int housePrice;
	private int numbersOfHouses;
	private final int taxOneHouse; 
	private final int taxTwoHouse; 
	private final int taxThreeHouse; 
	private final int hotelPrice;
	private boolean isHotel;
	private final int taxHotel; 
	private final int position;

	

	private Cities(String cityName, String region, int price, int taxOneCard,
			int taxTwoCard, int taxThreeCard, int housePrice,
			int numbersOfHouses, int taxOneHouse, int taxTwoHouse,
			int taxThreeHouse, int hotelPrice, boolean isHotel, int taxHotel,
			int position) {
		this.cityName = cityName;
		this.region = region;
		this.price = price;
		this.taxOneCard = taxOneCard;
		this.taxTwoCard = taxTwoCard;
		this.taxThreeCard = taxThreeCard;
		this.housePrice = housePrice;
		this.numbersOfHouses = numbersOfHouses;
		this.taxOneHouse = taxOneHouse;
		this.taxTwoHouse = taxTwoHouse;
		this.taxThreeHouse = taxThreeHouse;
		this.hotelPrice = hotelPrice;
		this.isHotel = isHotel;
		this.taxHotel = taxHotel;
		this.position = position;
	}

	public String getCityName() {
		return cityName;
	}

	public String getRegion() {
		return region;
	}

	public int getPrice() {
		return price;
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

	public int getHousePrice() {
		return housePrice;
	}

	public int getNumbersOfHouses() {
		return numbersOfHouses;
	}

	public void setNumbersOfHouses(int numbersOfHouses) {
		this.numbersOfHouses = numbersOfHouses;
	}

	public int getTaxOneHouse() {
		return taxOneHouse;
	}

	public int getTaxTwoHouse() {
		return taxTwoHouse;
	}

	public int getTaxThreeHouse() {
		return taxThreeHouse;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public boolean isHotel() {
		return isHotel;
	}

	public void setHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}

	public int getTaxHotel() {
		return taxHotel;
	}

	public int getPosition() {
		return position;
	}
}
