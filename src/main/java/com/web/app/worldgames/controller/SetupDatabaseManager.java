package com.web.app.worldgames.controller;

import org.apache.log4j.Logger;

import com.web.app.worldgames.dao.WorldGamesDao;

public class SetupDatabaseManager {
	private Logger log = Logger.getLogger(SetupDatabaseManager.class);

	private WorldGamesDao tableManager;

	public void refreshTables() {
		log.info("Deleting tables");
		tableManager.dropTables();
		log.info("Starting creating tables");
		tableManager.createUserStatistics();
		tableManager.createTables();
		log.info("Finished creating tables");
		
	}

	public void setTableManager(WorldGamesDao wgd) {
		this.tableManager = wgd;
	}
}
