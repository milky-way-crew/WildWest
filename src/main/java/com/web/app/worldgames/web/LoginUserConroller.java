package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.worldgames.domain.User;

@Controller
@RequestMapping(value = { "/login" })
public class LoginUserConroller {
	private static final Logger log = Logger.getLogger(LoginUserConroller.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(HttpServletRequest request, Model model) {
		log.info("LoginController GET");
		if (request.getSession().getAttribute("user") != null) {
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
		modelAndView.setViewName("redirect:home");
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return modelAndView;
	}
}
