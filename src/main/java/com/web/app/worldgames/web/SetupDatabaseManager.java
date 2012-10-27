package com.web.app.worldgames.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.web.app.worldgames.service.DataBaseServiceManager;


public class SetupDatabaseManager {
	private Logger log = Logger.getLogger(SetupDatabaseManager.class);
@Autowired
	private DataBaseServiceManager tableManager;

	public void refreshTables() {
		log.info("Deleting tables");
		tableManager.dropTables();
		log.info("Starting creating tables");
		tableManager.createUserStatisticsTable();
		tableManager.createUserTable();
		log.info("Finished creating tables");
		tableManager.fillStatistics();
		tableManager.fillUser();
		
	}

	public void setTableManager(DataBaseServiceManager manager) {
		this.tableManager = manager;
	}
}
