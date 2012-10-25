package com.web.app.worldgames.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
			return new User(rs.getInt("id"), rs.getString("login"),
					rs.getString("password"), rs.getString("nickName"),
					rs.getString("email"), rs.getInt("userStat"),
					rs.getString("avatar"), rs.getString("role"),
					rs.getTimestamp("userDate"));
		}
	}

	public User findUserByLogin(final String login) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userProf, userDate from users WHERE userLogin=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userLogin=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { login });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { login }, new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new User(rs.getInt("id"), login, rs
									.getString("nickname"), rs
									.getString("password"), rs
									.getString("email"), rs.getInt("userStat"),
									rs.getString("avatar"), rs
											.getString("role"), rs
											.getTimestamp("userDate"));
						}
					});
			return user;
		} else {
			return null;
		}
	}

	public User findUserByNickname(final String nickname) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userProf, userDate from users WHERE userNickname=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userNickname=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY,
				new Object[] { nickname });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { nickname }, new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new User(rs.getInt("id"), rs
									.getString("login"), rs
									.getString("password"), nickname, rs
									.getString("email"), rs.getInt("userStat"),
									rs.getString("avatar"), rs
											.getString("role"), rs
											.getTimestamp("userDate"));
						}
					});
			return user;
		} else {
			return null;
		}
	}

	public User findUserByEmail(final String email) {

		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userProf, userDate from users WHERE userEmail=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userEmail=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { email });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query,
					new Object[] { email }, new RowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new User(rs.getInt("id"), rs
									.getString("login"), rs
									.getString("password"), rs
									.getString("nickName"), email, rs
									.getInt("userStat"),
									rs.getString("avatar"), rs
											.getString("role"), rs
											.getTimestamp("userDate"));
						}
					});
			return user;
		} else {
			return null;
		}
	}

	public User logInUser(final String login, final String password) {
		final String query = "SELECT userId, userLogin, userPassword, userEmail, userNickname, userImage, userProf, userDate from users WHERE userLogin=? AND userPassword=?";
		final String CHECK_QUERY = "SELECT COUNT(*) FROM users WHERE userLogin=?";
		int num = jdbcTemplate.queryForInt(CHECK_QUERY, new Object[] { login,
				password });
		if (num > 0) {

			User user = jdbcTemplate.queryForObject(query, new Object[] {
					login, password }, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					return new User(rs.getInt("id"), login, rs
							.getString("nickname"), password, rs
							.getString("email"), rs.getInt("userStat"), rs
							.getString("avatar"), rs.getString("role"), rs
							.getTimestamp("userDate"));
				}
			});
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User insertUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
