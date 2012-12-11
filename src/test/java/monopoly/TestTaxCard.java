package monopoly;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.domain.monopoly.Player;
import com.web.app.worldgames.domain.monopoly.card.TaxCard;

public class TestTaxCard {
	
	User user;
	Player player;
	Player player1;
	TaxCard tax = new TaxCard();

	@Before
	public void setUp() throws Exception {
	user = new User();
	player = new Player(user, 0, 500, true);
	player1 = new Player(user, 2, 50, false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEffectOnPlayer() {
		tax.effectOnPlayer(player);
		assertEquals(400, player.getMoney());
	}

	@Test
	public void testCanPayTax() {
		assertTrue(tax.canPayTax(player));
		assertTrue(tax.canPayTax(player1)==false);
	}

}
