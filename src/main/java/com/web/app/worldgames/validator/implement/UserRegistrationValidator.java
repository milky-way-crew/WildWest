package com.web.app.worldgames.validator.implement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;

@Service
public class UserRegistrationValidator implements IValidator {
    private static final Logger log = Logger
	    .getLogger(UserRegistrationValidator.class);
    @Autowired
    private IUserServiceManager userServiceManager;

    public void validate(Object object, Errors errors) {
	log.info("Register validating");
	if (!supports(object.getClass())) {
	    return;
	}
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login",
		"empty.login");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		"empty.password");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
		"empty.email");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname",
		"empty.nickname");
	User user = (User) object;
	User userByEmail = userServiceManager.findUserByEmail(user.getEmail());
	User userByLogin = userServiceManager.findUserByLogin(user.getLogin());
	User userByNickname = userServiceManager.findUserByNickname(user
		.getNickname());
	if (userByEmail != null) {
	    log.error("Email " + user.getEmail() + " are busy!");
	    errors.rejectValue("email", "busy.email", "Email already used");
	}
	if (userByLogin != null) {
	    log.error("Login " + user.getLogin() + " are busy!");
	    errors.rejectValue("login", "busy.login", "Login already exists");
	}
	if (userByNickname != null) {
	    log.error("Nickname " + user.getNickname() + " are busy!");
	    errors.rejectValue("nickname", "busy.nickname", "Nickname already exists");
	}

    }

    public boolean supports(Class<?> vclass) {
	return vclass.equals(User.class);
    }

}
