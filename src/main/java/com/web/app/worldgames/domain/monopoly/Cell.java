package com.web.app.worldgames.domain.monopoly;

public abstract class Cell {
    private String name;
    private int position;
    abstract void effectOnPlayer(Player player);
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }

//	public void effectOnPlayer(Player player, int addMoney) {
//		//player.setMoney(addMoney);
//		
//	}
}
