package com.web.app.worldgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.worldgames.dao.UserStatisticsDao;
import com.web.app.worldgames.domain.UserStatistics;

@Service
public class StatisticsServiceManager {
	@Autowired
private UserStatisticsDao statisticsDao;

	public void setStatisticsDao(UserStatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}
	
	public boolean updateStatistics(int userId, UserStatistics stat) {
		return statisticsDao.updateStatistics(userId, stat);
	}
	
	
	
	
	
}
