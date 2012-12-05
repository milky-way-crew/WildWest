package com.web.app.worldgames.dao;

import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.web.app.worldgames.dao.interfaces.IUserStatisticsDao;
import com.web.app.worldgames.domain.UserStatistics;

@Repository
public class UserStatisticsDao implements IUserStatisticsDao {

	private Logger log = Logger.getLogger(UserStatisticsDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/****************************/
	/** Statistics information **/
	/****************************/
	@Override
	/*** Get count of user's games any type of game ***/
	public int getUserAllGames(int userId, String typeGame) {
		int allGames = 0;
		final String query = "SELECT userAllGames FROM users, userStatistics WHERE userId = ? AND typeGame=? AND userId = idUser";
		allGames = jdbcTemplate.queryForInt(query, new Object[] { userId,
				typeGame });
		return allGames;
	}

	@Override
	/*** Get total count of user's games ***/
	public int getUserTotalAllGames(int userId) {
		int allGames = 0;
		final String query = "SELECT SUM(userAllGames) FROM users, userStatistics WHERE userId = ? AND userId = idUser";
		allGames = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return allGames;
	}

	@Override
	/*** Get count of user's win games any type of game ***/
	public int getUserAllWinGames(int userId, String typeGame) {
		int allWinGames = 0;
		final String query = "SELECT userAllWinGames FROM users, userStatistics WHERE userId = ? AND typeGame=? AND userId = idUser";
		allWinGames = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return allWinGames;
	}

	@Override
	/*** Get total count of user's win games ***/
	public int getUserTotalAllWinGames(int userId) {
		int allWinGames = 0;
		final String query = "SELECT SUM(userAllWinGames) FROM users, userStatistics WHERE userId = ? AND userId = idUser";
		allWinGames = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return allWinGames;
	}

	@Override
	/*** Get count of user's points any type of game ***/
	public int getUserPoints(int userId, String typeGame) {
		int userPoints = 0;
		final String query = "SELECT userPoints FROM users, userStatistics WHERE userId = ? AND typeGame=? AND userId = idUser";
		userPoints = jdbcTemplate.queryForInt(query, new Object[] { userId,
				typeGame });
		return userPoints;
	}

	@Override
	/*** Get total count of user's points ***/
	public int getUserTotalPoints(int userId) {
		int userPoints = 0;
		final String query = "SELECT SUM(userPoints) FROM users, userStatistics WHERE userId = ? AND userId = idUser";
		userPoints = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return userPoints;
	}

	@Override
	/*** Get count of money any type of game ***/
	public int getUserMoneyAmount(int userId, String typeGame) {
		int moneyAmount = 0;
		final String query = "SELECT userMoneyAmount FROM users, userStatistics WHERE userId = ? AND typeGame=? AND userId = idUser";
		moneyAmount = jdbcTemplate.queryForInt(query, new Object[] { userId,
				typeGame });
		return moneyAmount;
	}

	@Override
	/*** Get total count of user's money ***/
	public int getUserTotalMoneyAmount(int userId) {
		int moneyAmount = 0;
		final String query = "SELECT SUM(userMoneyAmount) FROM users, userStatistics WHERE userId = ? AND userId = idUser";
		moneyAmount = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return moneyAmount;
	}

	@Override
	public boolean updateStatistics(int userId, UserStatistics stat) {
		final String query = "UPDATE userStatistics, users SET userAllGames=userAllGames+?, userPoints=userPoints+?, userMoneyAmount=userMoneyAmount+?, userAllWinGames=userAllWinGames+? WHERE userId=? AND userId=idUser";
		int i = 0;
		i = jdbcTemplate.update(query,
				new Object[] { stat.getUserAllGames(), stat.getUserPoints(),
						stat.getUserMoneyAmount(), stat.getUserAllWinGames(),
						userId });

		if (i > 0) {
			log.info("Statistics of user with id=" + userId + " was update!");
			return true;
		} else
			return false;
	}

	//
	// public int updateStatistics(final int userId, final UserStatistics stat)
	// {
	// final String query =
	// "UPDATE userStatistics, users SET userAllGames=?, userPoints=?, userMoneyAmount=?, userAllWinGames=? WHERE userId=? AND userStat=statId";
	// KeyHolder keyHolder = new GeneratedKeyHolder();
	// jdbcTemplate.update(new PreparedStatementCreator() {
	// @Override
	// public PreparedStatement createPreparedStatement(
	// Connection connection) throws SQLException {
	// PreparedStatement ps = connection.prepareStatement(query,
	// Statement.RETURN_GENERATED_KEYS);
	// ps.setInt(1, stat.getUserAllGames());
	// ps.setInt(2, stat.getUserPoints());
	// ps.setInt(3, stat.getUserMoneyAmount());
	// ps.setInt(4, stat.getUserAllWinGames());
	// ps.setInt(5, userId);
	// return ps;
	// }
	// }, keyHolder);
	//
	// return keyHolder.getKey().intValue();
	// }
}
