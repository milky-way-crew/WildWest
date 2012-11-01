package com.web.app.worldgames.domain.monopoly;

public class CityCard extends SellableCard {
	private String region;
	private int price;
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

	Cities cities = null;

	public CityCard(Cities cities) {
		this.region = cities.getRegion();
		this.price = cities.getPrice();
		this.taxOneCard = cities.getTaxOneCard();
		this.taxTwoCard = cities.getTaxTwoCard();
		this.taxThreeCard = cities.getTaxThreeCard();
		this.castlePrice = cities.getCastlePrice();
		this.numbersOfCastles = cities.getNumbersOfCastles();
		this.fortressPrice = cities.getFortressPrice();
		this.isFortress = cities.isFortress();
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
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Cities getCities() {
		return cities;
	}
	
	public void setCities(Cities cities) {
		this.cities = cities;
	}

	public void buildCastle(Player player) {
		CityCard city = (CityCard) StartGame.board().get(player.getPosition());
		if (getNumbersOfCastles() < 3) {

			if (canBuildCastle(player)) {
				player.setMoney(player.getMoney() - city.getCastlePrice());
				setNumbersOfCastles(numbersOfCastles++);
			}
		}
	}


	public boolean canBuildCastle(Player player) {
		if ((region.equals("Apulia") || region.equals("Cannes"))
				&& player.getNumberOfRegions(player,
						player.getRegion(player.getPosition())) == 2) {
			return true;
		} else if (player.getNumberOfRegions(player,
				player.getRegion(player.getPosition())) == 3) {
			return true;
		} else
			return false;
	}

	public boolean buildFortress(Player player) {
		CityCard city = (CityCard) StartGame.board().get(player.getPosition());
		if (getNumbersOfCastles() == 3){
			player.setMoney(player.getMoney() - city.getFortressPrice());
			setFortress(true);
		}
		return isFortress();
	}


	@Override
	void effectOnPlayer(Player player) {
		Player owner = null;
		Cell cell = StartGame.board().get(player.getPosition());
		if (GameBoard.hasOwner(cell)) {
			owner = GameBoard.owner(cell);
			int numberOfRegions = owner.getNumberOfRegions(owner,
					owner.getRegion(owner.getPosition()));
			if (numberOfRegions == 1) {
				player.setMoney(player.getMoney() - getTaxOneCard());
				owner.setMoney(owner.getMoney() + getTaxOneCard());
			} else if (numberOfRegions == 2) {
				player.setMoney(player.getMoney() - getTaxTwoCard());
				owner.setMoney(owner.getMoney() + getTaxTwoCard());
			} else if (numberOfRegions == 3) {
				player.setMoney(player.getMoney() - getTaxThreeCard());
				owner.setMoney(owner.getMoney() + getTaxThreeCard());
			}
		} else {
			player.setMoney(player.getMoney() - getPrice());
			owner = (Player) GameBoard.addOwners(player, cell);
			player.addRegions(player, player.getPosition());
		}
	}
}
