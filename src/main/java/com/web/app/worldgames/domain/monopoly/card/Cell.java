package com.web.app.worldgames.domain.monopoly.card;

import java.util.Map;

import com.web.app.worldgames.domain.monopoly.Player;

public abstract class Cell {
    private String name;
    private int position;
    public abstract void effectOnPlayer(Player player);
    public abstract Map<String, ? extends Object> action(Player player,String type);
    public abstract String info();
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }

}
