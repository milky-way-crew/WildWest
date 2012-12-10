package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.GoToJailCard;

public class TestGoToJailCard {

	User user;
	Player player;
	GoToJailCard go;
	@Before
	public void setUp() throws Exception {
		user = new User();
		player = new Player(user, 0, 0, true);
		go = new GoToJailCard();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEffectOnPlayer() {
		go.effectOnPlayer(player);
		assertEquals(11, player.getPosition());
		assertTrue(player.isHasFreeCard()==false);
	}

}
