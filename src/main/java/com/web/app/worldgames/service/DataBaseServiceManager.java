package com.web.app.worldgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.worldgames.dao.DataBaseDao;
import com.web.app.worldgames.service.interfaces.IDataBaseServiceManager;

@Service
public class DataBaseServiceManager implements IDataBaseServiceManager {
	@Autowired
	private DataBaseDao dataBaseDao;

	public void setDataBaseDao(DataBaseDao dataBaseDao) {
		this.dataBaseDao = dataBaseDao;
	}

	@Override
	public void createUserTable() {
		dataBaseDao.createUserTable();

	}

	@Override
	public void createUserStatisticsTable() {
		dataBaseDao.createUserStatisticsTable();

	}

	@Override
	public void dropTables() {
		dataBaseDao.dropTables();

	}

	@Override
	public void fillStatistics() {
		dataBaseDao.fillStatistics();

	}

	@Override
	public void fillUser() {
		dataBaseDao.fillUser();

	}

}
