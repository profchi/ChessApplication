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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.game.Piece;
import com.example.demo.dao.CurrentGameDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Controller
//@SessionAttribute(value = {""}, types = {User.class})
public class AuthenticationController {
	 
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User(username, password, email);
		
		User foundUser = userDao.addUser(user);
		
		if(foundUser != null)	
			request.setAttribute("existinguser", "Account Created");
		else
			request.setAttribute("existinguser", " User already exist");
		
		return "login";
	}
	
	@RequestMapping(value="/processLogin", method = RequestMethod.POST)
	public String processLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		
		User foundUser = userDao.loginUser(user);
		
		
		if (foundUser == null){
			request.setAttribute("name", "Username or Password is not valid");
			return "login";
		}
		else{
			HttpSession session = request.getSession();
			session.setAttribute("username", foundUser);
			return "menu";
		}
		
		
	}
	

	

}
