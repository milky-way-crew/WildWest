package com.web.app.worldgames.domain;

public class UserStatistics {
	private int statId;
	private int userAllGames;
	private int userPoints;
	private int userMoneyAmount;
	private int userAllWinGames;

	public UserStatistics() {

	}

	public UserStatistics(int statId, int userAllGames, int userPoints,
			int userMoneyAmount, int userAllWinGames) {
		super();
		this.statId = statId;
		this.userAllGames = userAllGames;
		this.userPoints = userPoints;
		this.userMoneyAmount = userMoneyAmount;
		this.userAllWinGames = userAllWinGames;
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

	@Override
	public String toString() {
		return "UserStatistics [statId=" + statId + ", userAllGames="
				+ userAllGames + ", userPoints=" + userPoints
				+ ", userMoneyAmount=" + userMoneyAmount + ", userAllWinGames="
				+ userAllWinGames + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + statId;
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
		if (statId != other.statId)
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
