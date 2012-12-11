package com.web.app.worldgames.web;


import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.gibbet.Gibbet;
import com.web.app.worldgames.gibbet.ReadWord;


@Controller
public class GibbetController  {
	private final static Logger log = Logger.getLogger(GibbetController.class);
	

	
	@RequestMapping(value =  "/gibbet", method = RequestMethod.GET )
	public String home(HttpServletRequest request, HttpServletResponse responsce, Model model) {
		Object loginedUser = request.getSession().getAttribute("user");
		if (loginedUser == null) {
			return "redirect:login";
		}
		log.info("GibbetController: Passing through...");
		model.addAttribute("user", loginedUser);
		log.info(loginedUser.toString());
		return "gibbet";
	}
	
	Gibbet gibbet = new Gibbet();
	ReadWord read = new ReadWord();
	String wordtrue;
	String words;
	 @RequestMapping(value = "/ajaxgibbet", method = RequestMethod.POST)
	    public @ResponseBody
	    String onMessage(HttpServletRequest request,
		    @RequestParam("type") String type, @RequestParam("data") String data,
		    @RequestParam("word") String word) throws FileNotFoundException  {

		if (type.toLowerCase().trim().equals("message")) {
			String temp = word;
			log.info("buttom message is put");
			char a = data.charAt(0);
			word=gibbet.checking(wordtrue, word, a);
		    log.debug("Message request from user: "+ data+"   "+a);
		    if(temp.equals(word)==true){
		    	return "";
		    }
		    if(word.equals(wordtrue)){
		    	return "You win "+word;
		    }
		    return word;
		}
		
		if(type.toLowerCase().trim().equals("something")){
			
			log.info("button OK is put  "+data);
			word = read.readFromFile(data);
			wordtrue = word;
			word = gibbet.replacement(word);
			log.info("genereta random word  "+ wordtrue+"  "+word);
			return word;
		}
		log.info("return on message"+word);
		return word;
	    }

	
}
