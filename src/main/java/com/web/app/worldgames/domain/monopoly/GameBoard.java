package com.web.app.worldgames.domain.monopoly;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
	protected static Map<Cell, Player> owners = new HashMap<Cell, Player>();
	public Map<Cell, Player> board() {
		return null;
	}

	public static Map<Cell, Player> addOwners(Player player, Cell cell) {
		owners.put(cell, player);
		return owners;
	}

	public static boolean hasOwner(Cell cell) {
		return owners.containsKey(cell);

	}

	public static Player owner(Cell cell) {
		return owners.get(cell);
	}
}
