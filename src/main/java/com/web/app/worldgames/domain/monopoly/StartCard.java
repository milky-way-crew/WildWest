package com.web.app.worldgames.domain.monopoly;

public class StartCard extends Cell {
	private int startMoney;

	public StartCard(int startMoney) {
		this.startMoney = startMoney;
	}

	public int getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(int startMoney) {
		this.startMoney = startMoney;
	}

	@Override
	public void effectOnPlayer(Player player) {
		player.setMoney(getStartMoney());
	}

}
