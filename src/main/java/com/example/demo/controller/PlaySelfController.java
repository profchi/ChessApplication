package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.CurrentGameDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Controller
public class PlaySelfController {
	
	@Autowired
	private CurrentGameDao gamedao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value =  "/PlaySelfGameSetup" , method = RequestMethod.POST)
	public void PlaySelfGameSetup(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		User user;
		if(session == null || session.getAttribute("username") == null)
			return;
		
		user = (User) session.getAttribute("username");
		
		CurrentGame game = gamedao.gameSetupSinglePlayer(user);
		game.setGameStarted(true);
		
		session.setAttribute("gametype", "PlaySelf");
		session.setAttribute("currentgame", game);
		session.setAttribute("turn", "both");
		
	}
	
	
	@RequestMapping(value =  "/PlaySelfMakeMove" , method = RequestMethod.POST)
	public void PlaySelfMakeMove(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null)
			return;
		
		//int returnVal = 0;
		boolean draw;
		CurrentGame game =  (CurrentGame)session.getAttribute("currentgame");
		String move = request.getParameter("move");
		boolean valid = false;
		boolean gameended = false;
		String declareWinner = "";
		
		boolean castled = false;
		
		JSONObject jsonObj = new JSONObject();
		
		if (move == null || move.length() != 4)
			return;
		
		int [] moves = new int [4];
		
		for (int i = 0; i < 4; ++i){
			moves[i] = move.charAt(i) - 48;
		}
		
		if(game.gamelogic.checkIfValid(moves)){
			valid = true;
			game.getGamelogic().adjustboard(moves);
			game.getMoves().add(move);
			if(game.gamelogic.checkForWinner()){
				gameended = true;
				if(game.gamelogic.getWhiteturn())
					draw = !game.gamelogic.InCheck('W');
				else
					draw = !game.gamelogic.InCheck('B');
				
				if(draw)
					declareWinner = "draw";
				else if (game.gamelogic.getWhiteturn())
					declareWinner = "black";
				else 
					declareWinner = "white";
			}
			
			
		}
		
		jsonObj.put("valid", valid);
		jsonObj.put("gameended", gameended);
		jsonObj.put("declareWinner", declareWinner);
		jsonObj.put("castled", castled);
		
		
		PrintWriter out = response.getWriter();
		out.print(jsonObj.toString());
		
		
		
	}
	

}
