package com.web.app.worldgames.service.interfaces;

public interface IDataBaseServiceManager {
	public void createUserTable();
	public void createUserStatisticsTable();
	public void fillStatistics();
	public void fillUser();
	
	public void dropTables();
}
