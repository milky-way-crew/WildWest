package com.web.app.worldgames.web;



import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.worldgames.dao.interfaces.IUserDao;
import com.web.app.worldgames.domain.User;
import com.web.app.worldgames.gibbet.Gibbet;
import com.web.app.worldgames.gibbet.GibbetGameManager;
import com.web.app.worldgames.gibbet.GibbetGameService;
import com.web.app.worldgames.gibbet.GibbetPlayer;
import com.web.app.worldgames.gibbet.ReadWord;


@Controller
public class MultiGibbetController  {
	private final static Logger log = Logger.getLogger(MultiGibbetController.class);

	@Autowired
	private IUserDao userDao;

	@Autowired
	private GibbetGameService gibbetService;

	@RequestMapping(value =  "/multigibbet", method = RequestMethod.GET )
	public String home(HttpServletRequest request, HttpServletResponse responsce, Model model) {
		Object loginedUser = request.getSession().getAttribute("user");
		if (loginedUser == null) {
			return "redirect:login";
		}
		log.info("MultiGibbetController: Passing through...");
		model.addAttribute("user", loginedUser);
		log.info(loginedUser.toString());
		return "multigibbet";
	}

	@RequestMapping(value = "/mgibbet") 
	public String showGibbetServers(Model model) {
		model.addAttribute("test", "GibbetContoller");
		model.addAttribute("gibbet", gibbetService.getAllGames());
		log.info("Showing all gibbet games to user");
		return "gibbetlist";
	}

	@RequestMapping(value = "/gibbet/create")
	public String createServer(HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting test user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("test"));
		}

		log.info("Starting creating gibbet-game");
		User userHost = (User) session.getAttribute("user");
		int gameId = gibbetService.createGame(userHost);
		//		int gameId = chessService.createGame(userHost);
		session.setAttribute("idGibbetGame", gameId);
		log.info("Finished creating gibbet-game with id: " + gameId);

		return "redirect:/multigibbet";
	}

	@RequestMapping(value = "/gibbet/connect")
	public String onConnectToServer(@RequestParam int idServer, HttpSession session) {
		if (session.getAttribute("user") == null) {
			log.info("**************** Setting doggi user to session ***************");
			session.setAttribute("user", userDao.findUserByLogin("doggi"));
		}

		User client = (User) session.getAttribute("user");
		GibbetGameManager gibbetGame = gibbetService.getGameById(idServer);

		if (gibbetGame == null || gibbetGame.getHost() == null) {
			return "redirect:/404";
		} else if (gibbetGame.getHost().getId() == client.getId()) {
			return "/multigibbet";
		}
		// TODO:
		// connect to self
		// connect if other games are running
		// connect if

		if (!gibbetGame.isFull()) {
			log.info("Connecting client to server with id" + idServer);
			session.setAttribute("idGibbetGame", idServer);
			gibbetGame.setClient(new GibbetPlayer(client));
			return "redirect:/multigibbet";
		} else {
			log.info("Server is full");
			return "redirect:/home";
		}
	}


	private Gibbet gibbet = new Gibbet();
	private ReadWord read = new ReadWord();
	private String wordtrue;
	@RequestMapping(value = "/ajaxmulti", method = RequestMethod.POST)
	public @ResponseBody
	String onMessage(HttpServletRequest request,
			@RequestParam("type") String type, @RequestParam("data") String data,
			@RequestParam("word") String word) throws FileNotFoundException  {

		User user = (User) request.getSession().getAttribute("user");
		int idGame = (Integer) request.getSession().getAttribute("idGibbetGame");
		GibbetGameManager gibbetGame = gibbetService.getGameById(idGame);

		if (type.toLowerCase().trim().equals("message")) {
			String temp = word;
			log.info("buttom message is put");
			char a = data.charAt(0);
			word=gibbet.checking(wordtrue, word, a);
			if(gibbetGame.getHost().getId()==user.getId()){
				gibbetGame.getHost().setMyWord(word);
				String opword = gibbetGame.getClient().getOpponent();
				opword=gibbet.checkingOpponent(word, opword);
				gibbetGame.getClient().setOpponent(opword);
				log.info("Add to opponent word  "+gibbetGame.getHost().getMyWord()+"  "+
						gibbetGame.getClient().getOpponent());
			}
			if(gibbetGame.getClient().getId()==user.getId()){
				gibbetGame.getClient().setMyWord(word);
				String opword = gibbetGame.getHost().getOpponent();
				opword=gibbet.checkingOpponent(word, opword);
				gibbetGame.getHost().setOpponent(opword);
				log.info("Add to oppanent word  "+gibbetGame.getClient().getMyWord()+"  "+
						gibbetGame.getHost().getOpponent());
			}
			//			    log.debug("Message request from user: "+ data+"   "+a);
			if(temp.equals(word)==true){
				return "";
			}
			if(word.equals(wordtrue)){
				if(gibbetGame.getHost().getId()==user.getId()){
					log.info("SET HOST");
					gibbetGame.getHost().setWin("win");
					gibbetGame.getClient().setWin("lose");
				}
				if(gibbetGame.getClient().getId()==user.getId()){
					log.info("SET Client");
					gibbetGame.getHost().setWin("lose");
					gibbetGame.getClient().setWin("win");
				}
				return "You win "+word;
			}
			return word;
		}


		if(type.toLowerCase().trim().equals("something")){
			if(gibbetGame.getClient().getId()==user.getId() && gibbetGame.getClient().getMyWord()!=null){
				return gibbetGame.getClient().getMyWord();
			}
			if(gibbetGame.getClient().getId()==user.getId() && gibbetGame.getClient().getMyWord()==null){
				return "not word";
			}
			else
				{
				log.info("button OK is put  "+data);
				word = read.readFromFile(data);
				wordtrue = word;
				word = gibbet.replacement(word);
				log.info("genereta random word  "+ wordtrue+"  "+word);
				gibbetGame.getHost().setMyWord(word);
				gibbetGame.getClient().setMyWord(word);
				gibbetGame.getHost().setOpponent(word);
				gibbetGame.getClient().setOpponent(word);
				return word;
			}

		}
		log.info("return on message"+word);
		return word;
	}

	@RequestMapping(value = "/ready", method = RequestMethod.POST)
	public @ResponseBody
	String ready(HttpServletRequest request,
			@RequestParam("type") String type, @RequestParam("data") String data,
			@RequestParam("word") String word)   {

		User user = (User) request.getSession().getAttribute("user");
		int idGame = (Integer) request.getSession().getAttribute("idGibbetGame");
		GibbetGameManager gibbetGame = gibbetService.getGameById(idGame);
		if(type.toLowerCase().trim().equals("ready")){
			if(gibbetGame.getHost().getId()== user.getId()){
				gibbetGame.getHost().setReady(true);
				if(gibbetGame.getClient().isReady()){
					return "game";
				}
				return "wait";
			}
			if(gibbetGame.getClient().getId()== user.getId()){
				gibbetGame.getClient().setReady(true);
				return "a";
			}
		}
		if(type.toLowerCase().trim().equals("update")){
			if(gibbetGame.getHost().isReady() && gibbetGame.getClient().isReady()){
				return "game";
			}
			if(gibbetGame.getHost().isReady()==false && gibbetGame.getClient().isReady()){
				return "wait fo host";
			}
			if((gibbetGame.getHost().isReady() && gibbetGame.getClient().isReady()==false) ||
					(gibbetGame.getHost().isReady() && gibbetGame.getClient()==null) ||
					gibbetGame.getHost().isReady()==false && gibbetGame.getClient()==null){
				return "wait fo client";
			}
		}

		return "b";
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(HttpServletRequest request,
			@RequestParam("type") String type, @RequestParam("data") String data,
			@RequestParam("word") String word) throws FileNotFoundException  {

//		log.info("update is go =)");
		User user = (User) request.getSession().getAttribute("user");
		int idGame = (Integer) request.getSession().getAttribute("idGibbetGame");
		GibbetGameManager gibbetGame = gibbetService.getGameById(idGame);

		if(type.toLowerCase().trim().equals("up")){
			if(gibbetGame.getHost().getId()==user.getId() && gibbetGame.getHost().getOpponent()!=null){
				return gibbetGame.getHost().getOpponent();
			}
			if(gibbetGame.getClient().getId()==user.getId() && gibbetGame.getClient().getOpponent()!=null){
				return gibbetGame.getClient().getOpponent();
			}
		}
		if(type.toLowerCase().trim().equals("key") && gibbetGame.getClient().getId()==user.getId()){
			if(gibbetGame.getHost().getMyWord()!=null){
				return gibbetGame.getClient().getMyWord();
			}
		}
		return "lolo";
	}
	
	@RequestMapping(value = "/win", method = RequestMethod.POST)
	public @ResponseBody
	String win(HttpServletRequest request,
			@RequestParam("type") String type, @RequestParam("data") String data)   {
		User user = (User) request.getSession().getAttribute("user");
		int idGame = (Integer) request.getSession().getAttribute("idGibbetGame");
		GibbetGameManager gibbetGame = gibbetService.getGameById(idGame);
			if(gibbetGame.getHost().getId()==user.getId()){
				if(type.toLowerCase().trim().equals("0")){
				gibbetGame.getHost().setWin("lose");
				gibbetGame.getClient().setWin("win");}
				log.info("Host "+gibbetGame.getClient().getWin());
				return gibbetGame.getHost().getWin();
			}
			if(gibbetGame.getClient().getId()==user.getId()){
				if(type.toLowerCase().trim().equals("0")){
				gibbetGame.getHost().setWin("win");
				gibbetGame.getClient().setWin("lose");}
				log.info("Klient "+gibbetGame.getClient().getWin());
				return gibbetGame.getClient().getWin();
			}
		return "";
	}
	
	@RequestMapping(value = "/exitgibbet") 
	public String leaveGame(HttpSession session) {
		log.info("Exit requested");
		Integer id = (Integer) session.getAttribute("idGibbetGame");

		session.removeAttribute("idGibbetGame");
		gibbetService.removeGameById(id);

		return "redirect:/home";
	}

}
