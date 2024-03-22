package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private SecurityContextLogoutHandler securityContextLogoutHandler;
	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/user")
	public String user() {
		return "welcome user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "welcome admin";
	}
	
	 @GetMapping("/logout")
	 public String logout(HttpServletRequest request,HttpServletResponse response) {
		
		
		securityContextLogoutHandler.logout(request, response, null);
	 		return "logged out";
	 	}
}
