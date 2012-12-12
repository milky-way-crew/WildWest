package com.web.app.worldgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.service.interfaces.IStatisticsServiceManager;

@Controller
public class PersonalController {
	private final static Logger log = Logger.getLogger(PersonalController.class);
	
	 private final String monopoly = "monopoly";
	 private final String chess = "chess";
	 private final String gibbet = "gibbet";
	 private final String draw = "drow&guess";
	
	@Autowired
	private IStatisticsServiceManager userStatService;

	@RequestMapping(value =  "/personal" )
	public String home( HttpServletRequest request, HttpServletResponse responsce, Model model) {
		User loginedUser = (User) request.getSession().getAttribute("user");
		if (loginedUser == null) {
			return "redirect:login";
		}
		log.info("PersonalController: Passing through...");
		model.addAttribute("user", loginedUser);
		log.info(loginedUser.toString());
		return "personal";
	}
	
	@RequestMapping(value = "/setAvatar", method = RequestMethod.POST)
	public @ResponseBody
	String setAvatar(HttpServletRequest request, @RequestParam("type") String type)   {
		User user = (User) request.getSession().getAttribute("user");
		log.info("Avatar is:  "+type);
		user.setAvatar(type);
		return type;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	String editInfo(HttpServletRequest request, @RequestParam("type") String type,
			@RequestParam("data") String data)   {
		User user = (User) request.getSession().getAttribute("user");
		if(type.toLowerCase().trim().equals("login")){
			user.setLogin(data);
			log.info("New Login is:"+data);
		}
		if(type.toLowerCase().trim().equals("nickname")){
			user.setNickname(data);
			log.info("New Nickname is:"+data);
		}
		if(type.toLowerCase().trim().equals("email")){
			user.setEmail(data);
			log.info("New Email is:"+data);
		}
		if(type.toLowerCase().trim().equals("pass")){
			user.setPassword(data);
			log.info("New Password is:"+data);
		}
		return data;
	}
	
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/table", method = RequestMethod.POST)
	    public @ResponseBody
	    JSONObject table(HttpServletRequest request,
		    @RequestParam("type") String type, @RequestParam("data") String data) {
		 User user = (User) request.getSession().getAttribute("user");
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(data);
		    } catch (ParseException e) {
			e.printStackTrace();
		    }
		json.put("monoG", ""+userStatService.getUserAllGames(user.getId(), monopoly));
		log.info("JSON "+json.get("monoG"));
		json.put("monoW", ""+userStatService.getUserAllWinGames(user.getId(), monopoly));
		json.put("monoP", ""+userStatService.getUserPoints(user.getId(), monopoly));
		json.put("monoM", ""+userStatService.getUserMoneyAmount(user.getId(), monopoly));
		json.put("chess", ""+userStatService.getUserAllGames(user.getId(), chess));
		json.put("chessW", ""+userStatService.getUserAllWinGames(user.getId(), chess));
		json.put("gibbet", ""+userStatService.getUserAllGames(user.getId(), gibbet));
		json.put("gibbetW", ""+userStatService.getUserAllWinGames(user.getId(), gibbet));
		json.put("draw", ""+userStatService.getUserAllGames(user.getId(), draw));
		json.put("drawW", ""+userStatService.getUserAllWinGames(user.getId(), draw));
		return json;
	 }
}
