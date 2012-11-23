package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.SellableCard;

public class TestSellableCard {
	User user;
	SellableCard cell;
	Player player;
	@Before
	public void setUp() throws Exception {
		user = new User();
		player = new Player(user, 0, 1000, true);
		cell = new SellableCard() {
			
			@Override
			public String info() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void effectOnPlayer(Player player) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void refuse(SellableCard cell, Player player) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void payRentToOwner(Player player, Player owner, int price) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void payOrMortage(SellableCard cell, Player player, Player owner) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getRent(SellableCard cell, Player player, Player owner) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void buyCityOrRail(SellableCard cell, Player player) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMortage() {
		cell.setName("Root");
		cell.setPrice(200);
		cell.setOwner(player);
		cell.mortage(player);
		assertEquals(1100, player.getMoney());
	}

	@Test
	public void testUnMortage() {
		cell.setName("Root");
		cell.setPrice(200);
		cell.setOwner(player);
		cell.unMortage(player);
		assertEquals(900, player.getMoney());
	}



}
