package com.web.app.worldgames.service.interfaces;

import com.web.app.worldgames.domain.User;

public interface IUserServiceManager {
	
	public User findUserByLogin(String login);

	public User findUserByNickname(String nickname);

	public User findUserByEmail(String email);

	public User logInUser(String login, String password);

	public User findUserById(int id);

	public int insertUser(User user);
	
	public boolean changeUserLogin(int userId, String login);
	
	public boolean changeUserPassword(int userId, String password);
	
	public boolean changeUserEmail(int userId, String email);
	
	public boolean changeUserNickname(int userId, String nickname);
	
	public boolean changeUserAvatar(int userId, String avatar);
}
