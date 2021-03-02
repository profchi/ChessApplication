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

import com.example.demo.controller.game.Piece;
import com.example.demo.dao.CurrentGameDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Controller
public class GameLoadController {
	

	@Autowired
	private CurrentGameDao gameDao;
	
	@RequestMapping(value =  "/GameLoad" , method = RequestMethod.POST )
	public void gameReady(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		JSONObject jsonObj = new JSONObject();
		PrintWriter out = response.getWriter();
		
		
		
		if(session == null ){
			out.print(jsonObj.toString());
			return;
		}
		else if ( session.getAttribute("currentgame") == null){
			for (int i = 0 ; i < 60 && session.getAttribute("currentgame") == null  ; ++ i){
				try{
					Thread.sleep(2010);
				}
				catch(Exception e){}
			}
			 if ( session.getAttribute("currentgame") == null){
				 out.print(jsonObj.toString());
				return;
			 }
			
		}
		
		CurrentGame game = (CurrentGame) session.getAttribute("currentgame");
		String turn = (String) session.getAttribute("turn");
		boolean loaded = false;
		
		
		User user = (User)session.getAttribute("username");
		Piece [][] board = game.getGamelogic().getBoard();
		
		if (!game.isGameStarted()){
			boolean accepted = waitForGameToBeAccepted(game);
			if (!accepted){
				session.setAttribute("currentgame", null);
				
				gameDao.deleteGame(game);
				out.print(jsonObj.toString());
				return;
			}
		}
		
		String key;
		
		
		for(int row = 0; row < 8; ++row){
			for(int col = 0; col < 8; ++col){
				key = "" + row + col;
				if(board[row][col] == null)
					jsonObj.put(key, "" );
				else
					jsonObj.put(key, board[row][col].toString());
				
			}
		}
		
		boolean ready = false;
		boolean currentTurn = game.gamelogic.getWhiteturn();
		
		
		loaded = true;
		
		session.setAttribute("whiteplayer", game.getPlayer1().getUsername());
		session.setAttribute("blackplayer", game.getPlayer2().getUsername());
		
		session.setAttribute("waiting", "");
		
		if (game.getPlayer2() == game.getPlayer1())
			ready = true;
		else if(currentTurn  && turn.equals("white"))
			ready = true;
		else if (!currentTurn && turn.equals("black"))
			ready = true;
		else 
			ready = false;
		
		jsonObj.put("ready", ready);
		jsonObj.put("loaded", loaded);
		
		
		
        //out.print(((Object)jsonObj).toString());
		out.print(jsonObj.toString());
	}

	// Wait for a Game to be accepted for two minutes
	private boolean waitForGameToBeAccepted(CurrentGame game) {
		// TODO Auto-generated method stub
		
		for (int i = 0 ; i < 60 ; ++ i){
			try{
				Thread.sleep(2000);
			}
			catch(Exception e){}
			
			if (game.isGameStarted())
				return true;
		}
		return false;
	}
	

}
