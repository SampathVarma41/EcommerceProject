package com.niit.project.external.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.project.internal.model.User;

@Controller
public class PageController 
{
	@RequestMapping("/Login")
	public String showLogin() 
	{
	 return "Login";
	}
		
	@RequestMapping("/about")
	public String showAbout()
	{
		return"About";
	}
	@RequestMapping("/contact")
	public String showContact()
	{
		return"Contact";
	} 

	@RequestMapping(value="/signup")
	public String val4(Model model) {
		model.addAttribute("user", new User());
		return "signup";	
	}
}
