package com.web.app.worldgames.validator.implement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;

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
		"login empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		"password empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
		"email empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname",
		"nickname empty");
	User user = (User) object;
	User userByEmail = userServiceManager.findUserByEmail(user.getEmail());
	User userByLogin = userServiceManager.findUserByLogin(user.getLogin());
	User userByNickname = userServiceManager.findUserByNickname(user
		.getNickname());
	if (userByEmail != null) {
	    log.error("Email " + user.getEmail() + " are busy!");
	    errors.rejectValue("email", "email busy");
	}
	if (userByLogin != null) {
	    log.error("Login " + user.getLogin() + " are busy!");
	    errors.rejectValue("login", "login busy");
	}
	if (userByNickname != null) {
	    log.error("Nickname " + user.getNickname() + " are busy!");
	    errors.rejectValue("nickname", "nickname busy");
	}

    }

    public boolean supports(Class<?> vclass) {
	return vclass.equals(User.class);
    }

}
