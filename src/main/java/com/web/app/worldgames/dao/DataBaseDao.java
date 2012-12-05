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

	@Override
	public void createUserTable() {
		log.debug("Executing create query");
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE users ( ");
		sql.append("userId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE, ");
		sql.append("userLogin VARCHAR(40) NOT NULL UNIQUE, ");
		sql.append("userPassword VARCHAR(40) NOT NULL, ");
		sql.append("userEmail VARCHAR(40) NOT NULL UNIQUE, ");
		sql.append("userNickname VARCHAR(40) NOT NULL UNIQUE, ");
		sql.append("userImage VARCHAR(100) NOT NULL, ");
		sql.append("userDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ");
		sql.append("PRIMARY KEY (userId)");
/*		sql.append("INDEX (userId), ");
		sql.append("FOREIGN KEY (userId) REFERENCES userStatistics(idUser)");*/
		sql.append(") ENGINE=INNODB");
		jdbcTemplate.execute(sql.toString());
	}
	@Override
	public void createUserStatisticsTable() {
		log.debug("Executing create query");
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE userStatistics ( ");
		sql.append("statId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE, ");
		sql.append("userAllGames INT(10) UNSIGNED DEFAULT 0, ");
		sql.append("userPoints INT(15) UNSIGNED DEFAULT 0, ");
		sql.append("userMoneyAmount INT(15) UNSIGNED DEFAULT 0, ");
		sql.append("userAllWinGames INT(10) UNSIGNED DEFAULT 0, ");
		sql.append("gameType VARCHAR(40) NOT NULL, ");
		sql.append("idUser INT(10) UNSIGNED NOT NULL, ");
		sql.append("PRIMARY KEY (statId), ");
		sql.append("INDEX (idUser), ");
		sql.append("FOREIGN KEY (idUser) REFERENCES users(userId)");
		sql.append(") ENGINE=INNODB");
		jdbcTemplate.execute(sql.toString());
	}
	@Override
	public void fillStatistics() {
		StringBuilder sql = new StringBuilder("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('monopoly', 1)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('chess', 1)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('gibbet', 1)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('drow&guess', 1)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('monopoly', 2)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());

		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('chess', 2)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('gibbet', 2)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
		
		sql.append("INSERT INTO userStatistics ");
		sql.append("(gameType, idUser) ");
		sql.append(" VALUES('drow&guess', 2)");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());
	}
	@Override
	public void fillUser() {
		StringBuilder sql = new StringBuilder("INSERT INTO users ");
		sql.append("(userLogin, userPassword, userEmail, userNickname, userImage) ");
		sql.append(" VALUES('test', 'test', 'test@gmail.com', 'test', '/img')");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());

		sql.append("INSERT INTO users ");
		sql.append("(userLogin, userPassword, userEmail, userNickname, userImage) ");
		sql.append(" VALUES('doggi', 'doggi', 'doggi@doggi.doggi', 'doggi', '/img')");
		jdbcTemplate.update(sql.toString());
		sql.delete(0, sql.length());

	}
	
	@Override
	public void dropTables() {
		log.info("Dropping tables");
		jdbcTemplate.execute("drop table if exists userStatistics");
		jdbcTemplate.execute("drop table if exists users");
		
	}

}
