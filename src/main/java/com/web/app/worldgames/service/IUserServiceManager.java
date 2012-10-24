package com.web.app.worldgames.service;

import com.web.app.worldgames.domain.User;

public interface IUserServiceManager {
    User findUserByLogin(String login);
    User findUserByNickname(String nickname);
    User findUserByEmail(String email);
    User logInUser(String login, String password);
}
