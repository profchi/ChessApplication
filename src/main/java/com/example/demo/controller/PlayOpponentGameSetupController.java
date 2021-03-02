package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.CurrentGameDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Controller
public class PlayOpponentGameSetupController {
	
	@Autowired
	private CurrentGameDao gameDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value =  "/PlayOpponentGameSetup" , method = RequestMethod.POST)
	public void PlayOpponentGameSetup(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		User player1, player2 = null;
		if(session == null || session.getAttribute("username") == null)
			return;	
		
		player1 = (User)session.getAttribute("username");
		String opponent = request.getParameter("opponent");
		
		if (opponent != null){
			player2 = userDao.getUserByUsername(opponent);
			if(player2 == null)
				return;
		}
		
		CurrentGame currentgame = gameDao.gameSetupDoublePlayer(player1, player2);
		
		session.setAttribute("turn", "white");
		session.setAttribute("gametype", "PlayOpponent");
		
		if (currentgame != null){
			if (session.getAttribute("currentgame") != null)
				session.removeAttribute("currentgame");
			session.setAttribute("currentgame", currentgame);
		}
		
	}
	
	
	@RequestMapping(value =  "/PlayOpponentAcceptGame" , method = RequestMethod.POST)
	public void PlayopponentAcceptGame(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		User player1, player2 = null;
		CurrentGame currentgame = null;
		if(session == null || session.getAttribute("username") == null)
			return;	
		
		player1 = (User)session.getAttribute("username");
		String opponent = request.getParameter("opponent");
		
		session.setAttribute("gametype", "PlayOpponent");
		session.setAttribute("turn", "black");
		
		if (opponent != null){
			player2 = userDao.getUserByUsername(opponent);
			if(player2 == null)
				return;
		}
		
		session.setAttribute("gametype", "PlayOpponent");
		session.setAttribute("turn", "black");
		
		for (int i = 0; i < 60 && currentgame == null; ++i){
			currentgame = gameDao.acceptGameDoublePlayer(player1, player2);
			
			try{
				Thread.sleep(2000);
			}
			catch(Exception e){}
		}
		
		if (currentgame != null){
			if (session.getAttribute("currentgame") != null)
				session.removeAttribute("currentgame");
			session.setAttribute("currentgame", currentgame);
			
		}
		
		
		
	}
	
	
	

}
