package com.web.app.worldgames.dao.interfaces;

import com.web.app.worldgames.domain.User;

public interface IUserDao {
	
	/*** find user by id ***/
	User findUserById(int id);

	/*** find user by login ***/
	User findUserByLogin(final String login);

	/*** find user by nickname ***/
	User findUserByNickname(final String nickname);

	/*** find user by email ***/
	User findUserByEmail(final String email);

	/*** find user by login and password ***/
	User logInUser(final String login, final String password);

	/*** insert user into table ***/
	int insertUser(final User user);

	/*** insert empty user's statistics ***/
	int insertEmptyStatistics();

	boolean changeUserLogin(int userId, String login);

	boolean changeUserPassword(int userId, String password);

	boolean changeUserEmail(int userId, String email);

	boolean changeUserNickname(int userId, String nickname);

	boolean changeUserAvatar(int userId, String avatar);
}
