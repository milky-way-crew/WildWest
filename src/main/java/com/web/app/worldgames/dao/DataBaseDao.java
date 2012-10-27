package com.web.app.worldgames.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.app.worldgames.dao.interfaces.IDataBaseDao;

@Repository
public class DataBaseDao implements IDataBaseDao {
	private static final Logger log = Logger.getLogger(DataBaseDao.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createUserTable() {
		log.debug("Executing create query");
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE users ( ");
		sql.append("userId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE, ");
		sql.append("userLogin VARCHAR(80) NOT NULL UNIQUE, ");
		sql.append("userPassword VARCHAR(80) NOT NULL, ");
		sql.append("userEmail VARCHAR(100) NOT NULL UNIQUE, ");
		sql.append("userNickname VARCHAR(80) NOT NULL UNIQUE, ");
		sql.append("userStat INT(10) UNSIGNED NOT NULL UNIQUE, ");
		sql.append("userImage VARCHAR(100) NOT NULL, ");
		sql.append("userProf VARCHAR(45) NOT NULL DEFAULT 'player', ");
		sql.append("userDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ");
		sql.append("PRIMARY KEY (userId), ");
		sql.append("INDEX (userStat), ");
		sql.append("FOREIGN KEY (userStat) REFERENCES userStatistics(statId)");
		sql.append(") ENGINE=INNODB");
		jdbcTemplate.execute(sql.toString());
	}

	public void createUserStatisticsTable() {
		log.debug("Executing create query");
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE userStatistics ( ");
		sql.append("statId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE, ");
		sql.append("userAllGames INT(10) UNSIGNED DEFAULT NULL, ");
		sql.append("userPoints INT(15) UNSIGNED DEFAULT NULL, ");
		sql.append("userMoneyAmount INT(15) UNSIGNED DEFAULT NULL, ");
		sql.append("userAllWinGames INT(10) UNSIGNED DEFAULT NULL, ");
		sql.append("PRIMARY KEY (statId)");
		sql.append(") ENGINE=INNODB");
		jdbcTemplate.execute(sql.toString());
	}
	
	public void fillStatistics() {
		StringBuilder sql = new StringBuilder("INSERT INTO userStatistics ");
		//sql.append("(userAllGames, userPoints, userMoneyAmount, userAllWinGAmes) ");
		sql.append(" VALUES()");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
			
	}
	public void fillUser() {
		StringBuilder sql = new StringBuilder("INSERT INTO users ");
		sql.append("(userLogin, userPassword, userEmail, userNickname, userStat, userImage) ");
		sql.append(" VALUES('test', 'test', 'test@gmail.com', 'test', 1, '/img')");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO users ");
		sql.append("(userLogin, userPassword, userEmail, userNickname, userStat, userImage) ");
		sql.append(" VALUES('doggi', 'doggi', 'doggi@doggi.doggi', 'doggi', 2, '/img')");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
			
	}

	public void dropTables() {
		log.info("Dropping tables");
		jdbcTemplate.execute("drop table if exists users");
		jdbcTemplate.execute("drop table if exists userStatistics");
	}

}
