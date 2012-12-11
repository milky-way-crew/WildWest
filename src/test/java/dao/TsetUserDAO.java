package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.web.app.worldgames.dao.UserDao;
import com.web.app.worldgames.domain.User;

public class TsetUserDAO {
	UserDao userdao;
	User user;
	JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void setUp() throws Exception {
		userdao = new UserDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetDataSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByNickname() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogInUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertEmptyStatistics() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserById() {
		user = userdao.findUserById(1);
		assertEquals("test",user.getLogin());
	}

}
