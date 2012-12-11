package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.StartCard;

public class TestStartCard {
	Player player;
	StartCard start;
	User user;
	
	@Before
	public void setUp() throws Exception {
	start = new  StartCard();	
	user = new User();
	player = new Player(user, 0, 0,true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEffectOnPlayer() {
		start.effectOnPlayer(player);
		assertEquals(1, player.getPosition());
		assertEquals(1500, player.getMoney());
	}

	@Test
	public void testInfo() {
		assertEquals("You can start: ", start.info());
	}

}
