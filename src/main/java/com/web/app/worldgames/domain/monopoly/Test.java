package com.web.app.worldgames.domain.monopoly;

import java.io.IOException;

import com.web.app.worldgames.domain.monopoly.card.CityCard;

public class Test {
	public static void main(String[] args) throws IOException {
//		PlayGame start = new PlayGame();
//		start.start();
		//---
		Player p = new Player("asdc", 3, 123, true);
		CityCard c = new CityCard(Cities.ATHENS);
		c.setOwner(p);
		System.out.println(c.getOwner().getName());
	}
}
