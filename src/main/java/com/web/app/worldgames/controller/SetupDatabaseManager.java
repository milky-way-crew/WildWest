package com.web.app.worldgames.controller;

import org.apache.log4j.Logger;
import com.web.app.worldgames.dao.DataBaseDao;


public class SetupDatabaseManager {
	private Logger log = Logger.getLogger(SetupDatabaseManager.class);

	private DataBaseDao tableManager;

	public void refreshTables() {
		log.info("Deleting tables");
		tableManager.dropTables();
		log.info("Starting creating tables");
		tableManager.createUserStatistics();
		tableManager.createUserTable();
		log.info("Finished creating tables");
		
	}

	public void setTableManager(DataBaseDao DBdao) {
		this.tableManager = DBdao;
	}
}
