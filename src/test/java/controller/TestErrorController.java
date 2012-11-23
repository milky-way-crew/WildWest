package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.web.app.worldgames.web.HttpErrorController;

public class TestErrorController {
	HttpErrorController cont;

	@Before
	public void setUp() throws Exception {
		cont = new HttpErrorController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOn404() {
		assertEquals("error/404", cont.on404());
	}

	@Test
	public void testOn500() {
		assertEquals("error/500", cont.on500());
	}

}
