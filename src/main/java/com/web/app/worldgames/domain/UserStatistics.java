package com.web.app.worldgames.domain;

public class UserStatistics {
	private int statId;
	private int userAllGames;
	private int userAllWinGames;
	private int userPoints;
	private int userMoneyAmount;
	private String typeGame;
	private int idUser;

	public UserStatistics() {

	}

	public UserStatistics(int statId, int userAllGames, int userAllWinGames,
			int userPoints, int userMoneyAmount, String typeGame, int idUser) {
		super();
		this.statId = statId;
		this.userAllGames = userAllGames;
		this.userAllWinGames = userAllWinGames;
		this.userPoints = userPoints;
		this.userMoneyAmount = userMoneyAmount;
		this.typeGame = typeGame;
		this.idUser = idUser;
	}

	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public int getUserAllGames() {
		return userAllGames;
	}

	public void setUserAllGames(int userAllGames) {
		this.userAllGames = userAllGames;
	}

	public int getUserPoints() {
		return userPoints;
	}

	public void setUserPoints(int userPoints) {
		this.userPoints = userPoints;
	}

	public int getUserMoneyAmount() {
		return userMoneyAmount;
	}

	public void setUserMoneyAmount(int userMoneyAmount) {
		this.userMoneyAmount = userMoneyAmount;
	}

	public int getUserAllWinGames() {
		return userAllWinGames;
	}

	public void setUserAllWinGames(int userAllWinGames) {
		this.userAllWinGames = userAllWinGames;
	}

	public String getTypeGame() {
		return typeGame;
	}

	public void setTypeGame(String typeGame) {
		this.typeGame = typeGame;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "UserStatistics [statId=" + statId + ", userAllGames="
				+ userAllGames + ", userPoints=" + userPoints
				+ ", userMoneyAmount=" + userMoneyAmount + ", userAllWinGames="
				+ userAllWinGames + ", typeGame=" + typeGame + ", idUser="
				+ idUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUser;
		result = prime * result + statId;
		result = prime * result
				+ ((typeGame == null) ? 0 : typeGame.hashCode());
		result = prime * result + userAllGames;
		result = prime * result + userAllWinGames;
		result = prime * result + userMoneyAmount;
		result = prime * result + userPoints;
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
		UserStatistics other = (UserStatistics) obj;
		if (idUser != other.idUser)
			return false;
		if (statId != other.statId)
			return false;
		if (typeGame == null) {
			if (other.typeGame != null)
				return false;
		} else if (!typeGame.equals(other.typeGame))
			return false;
		if (userAllGames != other.userAllGames)
			return false;
		if (userAllWinGames != other.userAllWinGames)
			return false;
		if (userMoneyAmount != other.userMoneyAmount)
			return false;
		if (userPoints != other.userPoints)
			return false;
		return true;
	}

}
