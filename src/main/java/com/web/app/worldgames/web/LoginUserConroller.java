package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IUserServiceManager;
import com.web.app.worldgames.validator.IValidator;

@Controller
@RequestMapping(value = { "/login" })
public class LoginUserConroller {
	private static final Logger log = Logger
			.getLogger(LoginUserConroller.class);

	@Autowired
	private IUserServiceManager userService;

	@Autowired
	@Qualifier(value = "userLogInValidator")
	private IValidator validator;

	@RequestMapping(method = RequestMethod.GET)
	public String showPage(HttpServletRequest request, Model model) {
		log.debug("LoginController passing through");
		if (request.getSession().getAttribute("user") != null) {
			log.info("User already logined redirecting to home.");
			return "redirect:home";
		}

		model.addAttribute("user", new User());
		return "loginform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute User user,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();

		validator.validate(user, result);

		if (result.hasErrors()) {
			log.warn("Filled form has some errors.");
			modelAndView.setViewName("loginform");
			return modelAndView;
		} else {
			log.info("User logined succesfully, redirecting to home page.");
			modelAndView.setViewName("redirect:home");
			recordUserInSession(request, restoreUserFromDatabase(user));
			return modelAndView;
		}

	}

	private User restoreUserFromDatabase(User user) {
		return userService.findUserByLogin(user.getLogin());
	}

	private void recordUserInSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}
}
