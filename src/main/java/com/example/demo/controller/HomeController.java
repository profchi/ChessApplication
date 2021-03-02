package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// Send to index.jsp
	@RequestMapping(value =  "/")
	public String showIndex(Model model){
		System.out.println("Homepage");
		return "index";
	}
	
	
	
}

