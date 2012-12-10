package com.web.app.worldgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.worldgames.dao.UserStatisticsDao;
import com.web.app.worldgames.domain.UserStatistics;
import com.web.app.worldgames.service.interfaces.IStatisticsServiceManager;

@Service
public class StatisticsServiceManager implements IStatisticsServiceManager {
	@Autowired
	private UserStatisticsDao statisticsDao;

	public void setStatisticsDao(UserStatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public boolean updateStatistics(int userId, UserStatistics stat) {
		return statisticsDao.updateStatistics(userId, stat);
	}

	@Override
	public int getUserAllGames(int userId, String typeGame) {
		return statisticsDao.getUserAllGames(userId, typeGame);
	}

	@Override
	public int getUserTotalAllGames(int userId) {
		return statisticsDao.getUserTotalAllGames(userId);
	}

	@Override
	public int getUserAllWinGames(int userId, String typeGame) {
		return statisticsDao.getUserAllWinGames(userId, typeGame);
	}

	@Override
	public int getUserTotalAllWinGames(int userId) {
		return statisticsDao.getUserTotalAllWinGames(userId);
	}

	@Override
	public int getUserPoints(int userId, String typeGame) {
		return statisticsDao.getUserPoints(userId, typeGame);
	}

	@Override
	public int getUserTotalPoints(int userId) {
		return statisticsDao.getUserTotalPoints(userId);
	}

	@Override
	public int getUserMoneyAmount(int userId, String typeGame) {
		return statisticsDao.getUserMoneyAmount(userId, typeGame);
	}

	@Override
	public int getUserTotalMoneyAmount(int userId) {
		return statisticsDao.getUserTotalMoneyAmount(userId);
	}

	@Override
	public boolean incrementUserAllGames(int userId, String gameType) {
		return statisticsDao.incrementUserAllGames(userId, gameType);
	}

	@Override
	public boolean incrementUserAllWinGames(int userId, String gameType) {
		return statisticsDao.incrementUserAllWinGames(userId, gameType);
	}

	@Override
	public boolean changeUserMoneyAmount(int userId, int money, String gameType) {
		return statisticsDao.changeUserMoneyAmount(userId, money, gameType);
	}

	@Override
	public boolean changeUserPoints(int userId, int points, String gameType) {
		return statisticsDao.changeUserPoints(userId, points, gameType);
	}

}
