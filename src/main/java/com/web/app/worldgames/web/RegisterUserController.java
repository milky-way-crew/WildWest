package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.StatisticsServiceManager;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;

@Controller
@RequestMapping(value = { "/register" })
public class RegisterUserController {
	private static final Logger log = Logger
			.getLogger(RegisterUserController.class);

	@Autowired
	private IUserServiceManager userService;
	@Autowired
	private StatisticsServiceManager statisticsService;
	
	@Autowired
	@Qualifier(value="userRegistrationValidator")
	private IValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(Model model) {
		log.debug("Incoming request to register page");
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute User user,
			BindingResult result) {
		
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			log.warn("Errors in registration form.");
			return "registration";
		} else {
			log.debug("POST: Registering new User.");
			log.info("insering user into database");
			
			int id = userService.insertUser(user);
			statisticsService.createStatisticsField(id);
			log.info("ID of new user: " + id);
			
			recordUserInSession(request, user);
			return "redirect:home";
		}
	}

	private void recordUserInSession(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user", user);
	}
}
