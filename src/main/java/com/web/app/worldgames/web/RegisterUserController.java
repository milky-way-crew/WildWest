package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.worldgames.domain.User;

@Controller
@RequestMapping(value = { "/register" })
public class RegisterUserController {
    private static final Logger log = Logger
	    .getLogger(RegisterUserController.class);
 
    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model) {
	log.debug("Register page showin'");
	model.addAttribute("user", new User());
	return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(HttpServletRequest request,
	    HttpServletResponse response, @ModelAttribute User user,
	    BindingResult result) {
	log.info("POST: Registering new User.");
	log.info(user.toString());
	request.getSession().setAttribute("user", user);
	return "redirect:home";
    }
}
