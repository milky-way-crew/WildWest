package com.web.app.worldgames.validator.implement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;


public class UserLogInValidator implements IValidator {
    private static final Logger log = Logger
	    .getLogger(UserRegistrationValidator.class);
    @Autowired
    private IUserServiceManager userServiceManager;

    
    public void validate(Object object, Errors errors) {
	log.info("LogIn validating");
	if (!supports(object.getClass())) {
	    return;
	}
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login",
		"login empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		"password empty");
	if (errors.hasErrors())
	    return;
	User user = (User) object;
	User userLogIn = userServiceManager.logInUser(user.getLogin(),
		user.getPassword());
	if (userLogIn == null) {
	    log.error("User " + user.getLogin() + " with password "
		    + user.getPassword() + " Not found!");
	    errors.rejectValue("password", "user not found");
	}
    }

    
    public boolean supports(Class<?> vclass) {
	return vclass.equals(User.class);
    }
}
