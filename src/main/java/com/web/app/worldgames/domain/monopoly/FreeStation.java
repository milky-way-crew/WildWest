package com.web.app.worldgames.domain.monopoly;

public class FreeStation extends Cell{

    @Override
    void effectOnPlayer(Player player) {
	// TODO Auto-generated method stub
	player.setMoney(player.getMoney());
	player.setPosition(player.getPosition());
    }

}
