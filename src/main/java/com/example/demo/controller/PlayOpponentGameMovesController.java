package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.CurrentGameDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Controller
public class PlayOpponentGameMovesController {

	@Autowired
	private CurrentGameDao gameDao;
	
	private String winnerdeclared = "none";
	private boolean castled;
	
	@RequestMapping(value =  "/PlayOpponentMakeMove" , method = RequestMethod.POST)
	public void PlayOpponentMakeMove(HttpServletRequest request ,  HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("currentgame") == null)
			return;
		
		boolean draw;
		JSONObject jsonObj = new JSONObject();
		boolean valid = false;
		boolean gameended = false;
		String declareWinner = "";
		
		castled = false;
		
		String turn = (String)session.getAttribute("turn");
		int playerturn = turn.equals("white")? 1 : 0;
		
		String move = request.getParameter("move");
		
		CurrentGame game = (CurrentGame) session.getAttribute("currentgame");
		
		if(game.getLastPlayed() == playerturn){
			return;
		}
		
		int [] moves = new int [4];
		
		for (int i = 0; i < 4; ++i){
			moves[i] = move.charAt(i) - 48;
		}
		
		if(game.gamelogic.checkIfValid(moves)){
			valid = true;
			castled = game.getGamelogic().adjustboard(moves);
			game.getMoves().add(move);
			game.setLastPlayed(playerturn);
			game.setLastMove(move);
			
			if(game.gamelogic.checkForWinner()){
				game.setCompleted(true);
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
				
				winnerdeclared = declareWinner;
			}
			
		}
		
		jsonObj.put("valid", valid);
		jsonObj.put("gameended", gameended);
		jsonObj.put("declareWinner", declareWinner);
		jsonObj.put("castled", castled);
		
		
		PrintWriter out = response.getWriter();
		out.print(jsonObj.toString());
	}
	
	@RequestMapping(value =  "/PlayOpponentGetMove" , method = RequestMethod.POST)
	public void PlayOpponentGetMove(HttpServletRequest request,  HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("currentgame") == null)
			return;
		
		String turn = (String)session.getAttribute("turn");
		int playerturn = turn.equals("white")? 1 : 0;
		
		PrintWriter out = response.getWriter();
		JSONObject jsonObj = new JSONObject();
		CurrentGame game = (CurrentGame) session.getAttribute("currentgame");
		
		while(game.getLastPlayed() == playerturn){
			try{
				Thread.sleep(2000);
			}
			catch(Exception e){}
			
			if(game.isAbandoned()){
					jsonObj.put("move", "00-00");
					jsonObj.put("gameended", game.isAbandoned());
					jsonObj.put("declareWinner", "abandoned");
					jsonObj.put("castled", castled);
					out.print(jsonObj.toString());
					return;
					
			}
		}
		
		String move = game.getLastMove();
		
		String processedMove = "" + move.charAt(0) + move.charAt(1) + "-" + move.charAt(2) + move.charAt(3); 
		
		jsonObj.put("move", processedMove );
		jsonObj.put("gameended", game.isCompleted());
		jsonObj.put("declareWinner", winnerdeclared);
		jsonObj.put("castled", castled);
		
		
		
		
		out.print(jsonObj.toString());
		
		
		
	}
}
