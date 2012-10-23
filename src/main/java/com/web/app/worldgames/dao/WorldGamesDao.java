package com.web.app.worldgames.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class WorldGamesDao {
	private static final Logger log = Logger.getLogger(WorldGamesDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void createTables() {
		log.debug("Executing create query");
		jdbcTemplate.execute("create table user (id int(10));");
	}
	
	public void dropTables() {
		log.info("Dropping tables");
		jdbcTemplate.execute("drop table if exists user");
	}

}
