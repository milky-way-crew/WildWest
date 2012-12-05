package com.web.app.worldgames.dao.interfaces;

public interface IDataBaseDao {

	public void createUserTable();

	public void createUserStatisticsTable();
	
	public void fillStatistics();
	
	public void fillUser();

	public void dropTables();
}
