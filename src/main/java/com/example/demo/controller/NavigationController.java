package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;

@Controller
public class NavigationController {
	
	
	@RequestMapping(value =  "/login")
	public String gotoLoginPage(Model Model){
		System.out.println("LoginPage");
		return "login";
	}
	
	@RequestMapping(value =  "/index")
	public String gotoHomePage(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate();
		return "index";
	}
	
	@RequestMapping(value =  "/menu")
	public String gotoMenuPage(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null)
			return "forward:/index";
		return "menu";
	}
	
	@RequestMapping(value =  "/gamemenu")
	public String gotoGameMenu(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null)
			return "forward:/index";
		else
			return "gamemenu";
	}
	
	@RequestMapping(value =  "/PlayGame")
	public String gotoGame(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		try {
			Thread.sleep(3000);
		}
		catch( Exception e){
			
		}
		
		
		if(session == null || session.getAttribute("username") == null)
			return "forward:/index";
		else
			return "PlayGame";
	}
	
	@RequestMapping(value =  "/About")
	public String gotoAbout(Model Model){
		return "About";
	}
	
	@RequestMapping(value =  "/ContactUs")
	public String gotoContactUs(Model Model){
		return "ContactUs";
	}
	
	@RequestMapping(value =  "/savedgame")
	public String gotoSavedgame(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null)
			return "forward:/index";
		return "savedgame";
	}
	
	
	
}
