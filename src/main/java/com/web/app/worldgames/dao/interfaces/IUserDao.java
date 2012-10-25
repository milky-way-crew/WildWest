package com.web.app.worldgames.dao.interfaces;

import com.web.app.worldgames.domain.User;

public interface IUserDao {
	
	/**
	* Find user by its login
	* @param login - user login
	* @return User object
	* */
	User findUserByLogin(final String login);
	
	/**
	* Find user by its nickname
	* @param nickname - user nickname
	* @return User object
	* */
    User findUserByNickname(final String nickname);
    
	/**
	* Find user by its email
	* @param email - user email
	* @return User object
	* */
    User findUserByEmail(final String email);
    
	/**
	* Find user by its login and password
	* @param login - user login
	* @param password - user password
	* @return User object
	* */
    User logInUser(final String login, final String password);

	int insertUser(final User user);
	
}
