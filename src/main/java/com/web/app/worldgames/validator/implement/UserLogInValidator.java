package com.web.app.worldgames.validator.implement;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;

@Service
public class UserLogInValidator implements IValidator {
	private static final Logger log = Logger.getLogger(UserRegistrationValidator.class);
	
	@Autowired
	private IUserServiceManager userService;

	public void validate(Object object, Errors errors) {
		log.info("LogIn validating");
		if (!supports(object.getClass())) {
			return;
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password");

		if (errors.hasErrors()) {
			return;
		} else {
			User user = (User) object;
			User userFromDatabase = userService.findUserByLogin(user.getLogin());
			
			if (userFromDatabase == null) {
				log.info(MessageFormat.format("Cannot find login %s in database.", 
						user.getLogin()));
				errors.rejectValue("login", "wrong.login", "Login not registered.");
			} else {
				if (!user.getPassword().equals(userFromDatabase.getPassword())) {
					log.info(MessageFormat.format("Password dont match. Login: %s.", 
						user.getLogin()));
					errors.rejectValue("password", "wrong.password", "Passwords don't match.");
				}
			}
		}
	}

	public boolean supports(Class<?> vclass) {
		return vclass.equals(User.class);
	}
}
