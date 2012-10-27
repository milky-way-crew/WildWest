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
					rs.getString("userNickname"), rs.getInt("userStat"),
					rs.getString("userImage"), rs.getString("userProf"),
					rs.getTimestamp("userDate"));
		}
	}

	@Override
	public User findUserByLogin(final String login) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userStat, userImage, userProf, userDate from users WHERE userLogin=?";
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

	@Override
	public User findUserByNickname(final String nickname) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userStat, userImage, userProf, userDate from users WHERE userNickname=?";
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

	@Override
	public User findUserByEmail(final String email) {

		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userStat, userImage, userProf, userDate from users WHERE userEmail=?";
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

	@Override
	public User logInUser(final String login, final String password) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userStat, userImage, userProf, userDate from users WHERE userLogin=? AND userPassword=?";
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

	@Override
	public int insertUser(final User user) {

		final String query = "INSERT INTO users (userLogin, userPassword, userEmail, userNickname, userStat, userImage) VALUES(?,?,?,?,?,?)";
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
				ps.setInt(5, insertEmptyStatistics());
				ps.setString(6, user.getAvatar());
				return ps;
			}
		}, keyHolder);
		int i = keyHolder.getKey().intValue();
		return i;

	}

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

	@Override
	public User findUserById(int id) {

		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userStat, userImage, userProf, userDate from users WHERE userId=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userEmail=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { id });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query, new Object[] { id },
					new UserMapping());
			return user;
		} else {
			return null;
		}
	}
}
