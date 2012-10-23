package com.web.app.worldgames.service;

import com.web.app.worldgames.domain.User;

public interface IUserServiceManager {
    User findUserByLogin(String email);
    User findUserByNickname(String nickname);
    User findUserByEmail(String email);
}
