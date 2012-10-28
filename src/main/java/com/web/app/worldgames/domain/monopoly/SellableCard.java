package com.web.app.worldgames.domain.monopoly;

public abstract class SellableCard extends Cell{
    private int price;
    private boolean mortage;

    public boolean isMortage() {
        return mortage;
    }

    public void setMortage(boolean mortage) {
        this.mortage = mortage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
