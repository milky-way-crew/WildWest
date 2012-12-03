package com.web.app.worldgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;

@Service
public class UserServiceManager implements IUserServiceManager {
	@Autowired
	private IUserDao userDao;

	@Override
	public User findUserByLogin(String login) {
		return userDao.findUserByLogin(login);
	}

	@Override
	public User findUserByNickname(String nickname) {
		return userDao.findUserByNickname(nickname);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public User logInUser(String login, String password) {
		return userDao.logInUser(login, password);
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

}
