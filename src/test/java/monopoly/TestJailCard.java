package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.JailCard;

public class TestJailCard {
	User user;
	Player player;
	JailCard jail;

	@Before
	public void setUp() throws Exception {
		user = new User();
		player = new Player(user, 0, 1000, true);
		jail = new JailCard();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEffectOnPlayer() {
		jail.effectOnPlayer(player);
		assertTrue(player.isHasFreeCard()==false);
	}

//	@Test
//	public void testEntranceFromJailAndMove() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testCanPayRansom() {
		assertTrue("Player can pay",jail.canPayRansom(player));
		player.setMoney(5);
		assertTrue("Playet can not pay", jail.canPayRansom(player)==false);
		
		
	}

	@Test
	public void testPayRansom() {
		jail.payRansom(player);
		assertEquals(950, player.getMoney());
	}



}
