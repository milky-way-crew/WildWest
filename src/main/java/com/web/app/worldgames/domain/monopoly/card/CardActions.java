package com.web.app.worldgames.domain.monopoly.card;

public class CardActions {
	public static boolean isSallebleCard(Cell cell) {
		if (cell instanceof SellableCard) {
			return true;
		} else
			return false;
	}
}
