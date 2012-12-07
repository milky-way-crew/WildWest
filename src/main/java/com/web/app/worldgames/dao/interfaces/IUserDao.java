package com.web.app.worldgames.dao.interfaces;

import com.web.app.worldgames.domain.User;

public interface IUserDao {

	/*** find user by id ***/
	public User findUserById(int id);

	/*** find user by login ***/
	public User findUserByLogin(final String login);

	/*** find user by nickname ***/
	public User findUserByNickname(final String nickname);

	/*** find user by email ***/
	public User findUserByEmail(final String email);

	/*** find user by login and password ***/
	public User logInUser(final String login, final String password);

	/*** insert user into table ***/
	public int insertUser(final User user);

	/*** change user's login ***/
	public boolean changeUserLogin(int userId, String login);

	/*** change user's password ***/
	public boolean changeUserPassword(int userId, String password);

	/*** change user's email ***/
	public boolean changeUserEmail(int userId, String email);

	/*** change user's nickname ***/
	public boolean changeUserNickname(int userId, String nickname);

	/*** change user's avatar ***/
	public boolean changeUserAvatar(int userId, String avatar);
}
