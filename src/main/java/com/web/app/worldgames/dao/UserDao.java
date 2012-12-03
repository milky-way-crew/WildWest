package com.web.app.worldgames.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;

@Repository
public class UserDao implements IUserDao {

	private Logger log = Logger.getLogger(UserDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final class UserMapping implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getInt("userId"), rs.getString("userLogin"),
					rs.getString("userPassword"), rs.getString("userEmail"),
					rs.getString("userNickname"), rs.getString("userImage"),
					rs.getTimestamp("userDate"));
		}
	}

	/*** find user by id ***/
	@Override
	public User findUserById(int id) {

		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userDate from users WHERE userId=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userId=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { id });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query, new Object[] { id },
					new UserMapping());
			return user;
		} else {
			return null;
		}
	}

	/*** find user by login ***/
	@Override
	public User findUserByLogin(final String login) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname,  userImage,  userDate from users WHERE userLogin=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userLogin=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { login });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { login }, new UserMapping());
			return user;
		} else {
			return null;
		}
	}

	/*** find user by nickname ***/
	@Override
	public User findUserByNickname(final String nickname) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userDate from users WHERE userNickname=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userNickname=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY,
				new Object[] { nickname });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { nickname }, new UserMapping());
			return user;
		} else {
			return null;
		}
	}

	/*** find user by email ***/
	@Override
	public User findUserByEmail(final String email) {

		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userDate from users WHERE userEmail=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userEmail=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { email });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { email }, new UserMapping());
			return user;
		} else {
			return null;
		}
	}

	/*** find user by login and password ***/
	@Override
	public User logInUser(final String login, final String password) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userDate from users WHERE userLogin=? AND userPassword=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userLogin=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { login,
				password });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query, new Object[] {
					login, password }, new UserMapping());
			return user;
		} else {
			return null;
		}
	}

	/*** insert user into table ***/
	@Override
	public int insertUser(final User user) {

		final String query = "INSERT INTO users (userLogin, userPassword, userEmail, userNickname, userImage) VALUES(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getNickname());
				ps.setString(5, user.getAvatar());
				return ps;
			}
		}, keyHolder);
		int i = keyHolder.getKey().intValue();
		return i;

	}

	// TODO
	@Override
	public int insertEmptyStatistics() {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String query = "INSERT INTO userStatistics VALUES()";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				//
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/*** change user's login ***/
	@Override
	public boolean changeUserLogin(int userId, String login) {
		final String query = "UPDATE users SET userLogin=? WHERE userId = ?";
		int i = jdbcTemplate.update(query, new Object[] { login, userId });
		if (i == 0)
			return false;
		else
			return true;
	}

	/*** change user's password ***/
	@Override
	public boolean changeUserPassword(int userId, String password) {
		final String query = "UPDATE users SET userPassword=? WHERE userId = ?";
		int i = jdbcTemplate.update(query, new Object[] { password, userId });
		if (i == 0)
			return false;
		else
			return true;
	}

	/*** change user's email ***/
	@Override
	public boolean changeUserEmail(int userId, String email) {
		final String query = "UPDATE users SET userEmail=? WHERE userId = ?";
		int i = jdbcTemplate.update(query, new Object[] { email, userId });
		if (i == 0)
			return false;
		else
			return true;
	}

	/*** change user's nickname ***/
	@Override
	public boolean changeUserNickname(int userId, String nickname) {
		final String query = "UPDATE users SET userNickname=? WHERE userId = ?";
		int i = jdbcTemplate.update(query, new Object[] { nickname, userId });
		if (i == 0)
			return false;
		else
			return true;
	}

	/*** change user's avatar ***/
	@Override
	public boolean changeUserAvatar(int userId, String avatar) {
		final String query = "UPDATE users SET userAavatar=? WHERE userId = ?";
		int i = jdbcTemplate.update(query, new Object[] { avatar, userId });
		if (i == 0)
			return false;
		else
			return true;
	}

}
