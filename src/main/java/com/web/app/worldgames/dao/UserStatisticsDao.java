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
	/*** Update all user's statistics ***/
	public boolean updateStatistics(int userId, UserStatistics stat) {
		final String query = "UPDATE userStatistics, users SET userAllGames=userAllGames+?, userPoints=userPoints+?, userMoneyAmount=userMoneyAmount+?, userAllWinGames=userAllWinGames+? WHERE userId=? AND userId=idUser";
		int i = 0;
		i = jdbcTemplate.update(query,
				new Object[] { stat.getUserAllGames(), stat.getUserPoints(),
						stat.getUserMoneyAmount(), stat.getUserAllWinGames(),
						userId });

		if (i > 0) {
			log.info("Statistics of user[" + userId + "] was update!");
			return true;
		} else
			return false;
	}

	@Override
	/*** Increment count of user's total games ***/
	public boolean incrementUserAllGames(int userId, String gameType) {
		final String query = "UPDATE userStatistics, users SET userAllGames=userAllGames+1 WHERE userId=? AND gameType=? AND userId=idUser";
		int i = jdbcTemplate.update(query, new Object[] { userId, gameType});
		if (i > 0) {
			log.info("'AllGames' of user[" + userId + "] in game [" + gameType
					+ "] was incremented");
			return true;
		} else {
			return false;
		}
	}

	@Override
	/*** Increment count of user's total win games ***/
	public boolean incrementUserAllWinGames(int userId, String gameType) {
		final String query = "UPDATE userStatistics, users SET userAllWinGames=userAllWinGames+1 WHERE userId=? AND gameType=? AND userId=idUser";
		int i = jdbcTemplate.update(query, new Object[] { userId, gameType });
		if (i > 0) {
			log.info("'AllWinGames' of user[" + userId + "] in game ["
					+ gameType + "] was incremented");
			return true;
		} else {
			return false;
		}
	}

	@Override
	/*** Change amount money of user ***/
	public boolean changeUserMoneyAmount(int userId, int money, String gameType) {
		final String query = "UPDATE userStatistics, users SET userMoneyAmount=userMoneyAmount+? WHERE userId=? AND gameType=? AND userId=idUser";
		int i = jdbcTemplate.update(query, new Object[] { money, userId,
				gameType });
		if (i > 0) {
			log.info("'MoneyAmount' of user[" + userId + "] in game ["
					+ gameType + "] was change");
			return true;
		} else {
			return false;
		}
	}

	@Override
	/*** Change points of user ***/
	public boolean changeUserPoints(int userId, int points, String gameType) {
		final String query = "UPDATE userStatistics, users SET userPoints=userPoints+? WHERE userId=? AND gameType=? AND userId=idUser";
		int i = jdbcTemplate.update(query, new Object[] { points, userId,
				gameType });
		if (i > 0) {
			log.info("'Points' of user[" + userId + "] in game [" + gameType
					+ "] was change");
			return true;
		} else {
			return false;
		}
	}


}
