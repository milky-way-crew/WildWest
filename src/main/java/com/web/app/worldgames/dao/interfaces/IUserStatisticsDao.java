package com.web.app.worldgames.dao.interfaces;

import com.web.app.worldgames.domain.UserStatistics;

public interface IUserStatisticsDao {

	public int getUserAllGames(int userId, String typeGame);

	public int getUserTotalAllGames(int userId);

	public int getUserAllWinGames(int userId, String typeGame);

	public int getUserTotalAllWinGames(int userId);

	public int getUserPoints(int userId, String typeGame);

	public int getUserTotalPoints(int userId);

	public int getUserMoneyAmount(int userId, String typeGame);

	public int getUserTotalMoneyAmount(int userId);

	public boolean updateStatistics(int userId, UserStatistics stat);

}
