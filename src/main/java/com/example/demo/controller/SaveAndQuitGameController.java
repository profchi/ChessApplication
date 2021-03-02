package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.game.GameLogic;
import com.example.demo.dao.CurrentGameDao;
import com.example.demo.dao.SavedGameDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.SavedGame;
import com.example.demo.model.User;

@Controller
public class SaveAndQuitGameController {
	
	@Autowired
	private SavedGameDao gameDao;

	@RequestMapping(value =  "/SaveGame" , method = RequestMethod.POST)
	public void SaveGame(HttpServletRequest request ,  HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("currentgame") == null)
			return;
		
		CurrentGame game = (CurrentGame) session.getAttribute("currentgame");
		
		Stack<String> moves = game.getMoves();
		
		String playedAs = (String)session.getAttribute("turn");
		
		User player1 = game.getPlayer1();
		
		User player2 = game.getPlayer2();
		
		StringBuilder appendMoves = new StringBuilder();
		
		for (String move : moves){
			appendMoves.append("-");
			appendMoves.append(move);
		}
		
		String finalMoves = appendMoves.toString();
		
		SavedGame savedgame  = new SavedGame(player1, player2, finalMoves, playedAs);
		
		gameDao.save(savedgame);
		
	}
	
	
	@RequestMapping(value =  "/QuitGame" , method = RequestMethod.POST)
	public void QuitGame(HttpServletRequest request ){
		HttpSession session = request.getSession(false);
		
		CurrentGame game = (CurrentGame) session.getAttribute("currentgame");
		
		game.setAbandoned(true);
		
		session.setAttribute("currentgame", null);
		
	}
	
	@RequestMapping(value =  "/LoadGame")
	public String LoadGame(HttpServletRequest request ){
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("username") == null)
			return "index";
		
		User user = (User)session.getAttribute("username");
		
		List<SavedGame> savedgames  = gameDao.loadgames(user);
		
		session.setAttribute("savedgames", savedgames);
		
		return "LoadGame";
	}
	
	
	@RequestMapping(value =  "/LoadSpecificGame" , method = RequestMethod.POST)
	public void LoadSpecificGame(HttpServletRequest request ){
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("username") == null)
			return;
		
		String id = request.getParameter("gameid");
		int gameid = Integer.parseInt(id);
		
		SavedGame actualGame = null;
		
		List<SavedGame> savedgames = (List<SavedGame>) session.getAttribute("savedgames");
		
		for (SavedGame savedgame : savedgames){
			if (savedgame.getId() == gameid){
				actualGame = savedgame;
				break;
			}
		}
		
		if (actualGame == null)
			return;
		
		session.setAttribute("savedgame", actualGame);
		
		GameLogic game = new GameLogic();
		game.init();
		
		session.setAttribute("game", game);
		
		
	}
	
	@RequestMapping(value =  "/LoadMoves" , method = RequestMethod.POST)
	public void LoadMoves(HttpServletRequest request,  HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession(false);
		
		int index = Integer.parseInt(request.getParameter("index"));
		
		PrintWriter out = response.getWriter();
		JSONObject jsonObj = new JSONObject();
		
		if(session == null || session.getAttribute("username") == null)
			return;
		
		String movesCompressed = ((SavedGame)session.getAttribute("savedgame")).getMoves();
		
		String [] moves = movesCompressed.split("-");
		
		GameLogic game = (GameLogic)session.getAttribute("game");
		
		int [] move = new int[4];
		if (index  == 0 ){
			game = new GameLogic();
			game.init();
			session.setAttribute("game", game);
		}
		else if (index > 0 && index < moves.length){
			for (int i = 0; i < 4; ++i)
				move[i] = moves[index].charAt(i) - 48;
			
			
			
			game.adjustboard(move);
		}
		String key;
		
		
		for(int row = 0; row < 8; ++row){
			for(int col = 0; col < 8; ++col){
				key = "" + row + col;
				if(game.getBoard()[row][col] == null)
					jsonObj.put(key, "" );
				else
					jsonObj.put(key, game.getBoard()[row][col].toString());
				
			}
		}
		
		
		out.print(jsonObj.toString());
		
		
	}
	
		
}
