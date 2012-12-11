package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.FreeStation;

public class TestFreeStation {
		Player player;
		User user;
		FreeStation free;
	@Before
	public void setUp() throws Exception {
		free = new FreeStation();
		user = new User();
		player = new Player(user, 2, 200, true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEffectOnPlayer() {
		free.effectOnPlayer(player);
		assertEquals(200, player.getMoney());
		assertEquals(2, player.getPosition());
	}

}
