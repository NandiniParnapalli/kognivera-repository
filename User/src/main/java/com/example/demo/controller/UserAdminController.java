package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class UserAdminController {

	
	@Autowired
	SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome all people";
	}
	
	
	@GetMapping("/user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
		return "welcome user";
	}
	
	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
		return "welcome admin";
	}
	

	 @GetMapping("/logout")
	 public String logout(HttpServletRequest request,HttpServletResponse response) {
		
		
		securityContextLogoutHandler.logout(request, response, null);
	 		return "logged out";
	 	}
}
