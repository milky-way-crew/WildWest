package com.web.app.worldgames.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public int getUserAllGames(int userId) {
		int allGames = 0;
		final String query = "SELECT userAllGames FROM users, userStatistics WHERE userId = ? AND userStat = statId";
		allGames = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return allGames;
	}

	public int getUserPoints(int userId) {
		int userPoints = 0;
		final String query = "SELECT userPoints FROM users, userStatistics WHERE userId = ? AND userStat = statId";
		userPoints = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return userPoints;
	}

	public int getUserMoneyAmount(int userId) {
		int moneyAmount = 0;
		final String query = "SELECT userMoneyAmount FROM users, userStatistics WHERE userId = ? AND userStat = statId";
		moneyAmount = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return moneyAmount;
	}

	public int getUserAllWinGames(int userId) {
		int allWinGames = 0;
		final String query = "SELECT userAllWinGames FROM users, userStatistics WHERE userId = ? AND userStat = statId";
		allWinGames = jdbcTemplate.queryForInt(query, new Object[] { userId });
		return allWinGames;
	}
	
	

	 public boolean updateStatistics(int userId, UserStatistics stat) {
	 final String query =
	 "UPDATE userStatistics, users SET userAllGames=userAllGames+?, userPoints=userpoints+?, userMoneyAmount=userAmount+?, userAllWinGames=userAllWinGames+? WHERE userId=? AND userStat=statId";
	 int i = 0;
	 i=jdbcTemplate.update(query, new Object[]{stat.getUserAllGames(),
	 stat.getUserPoints(), stat.getUserMoneyAmount(),
	 stat.getUserAllWinGames(), userId});
	
	 if (i>0){
	 log.info("Statistics of user with id="+userId+" was update!");
	 return true;
	 }else
	 return false;
	 }
	 
	 
//
//	public int updateStatistics(final int userId, final UserStatistics stat) {
//		final String query = "UPDATE userStatistics, users SET userAllGames=?, userPoints=?, userMoneyAmount=?, userAllWinGames=? WHERE userId=? AND userStat=statId";
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			@Override
//			public PreparedStatement createPreparedStatement(
//					Connection connection) throws SQLException {
//				PreparedStatement ps = connection.prepareStatement(query,
//						Statement.RETURN_GENERATED_KEYS);
//				ps.setInt(1, stat.getUserAllGames());
//				ps.setInt(2, stat.getUserPoints());
//				ps.setInt(3, stat.getUserMoneyAmount());
//				ps.setInt(4, stat.getUserAllWinGames());
//				ps.setInt(5, userId);
//				return ps;
//			}
//		}, keyHolder);
//
//		return keyHolder.getKey().intValue();
//	}
}
