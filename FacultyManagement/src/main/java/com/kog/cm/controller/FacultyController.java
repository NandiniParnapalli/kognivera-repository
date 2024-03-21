package com.kog.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kog.cm.entity.Faculty;
import com.kog.cm.service.FacultyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/authfac")
public class FacultyController {

	@Autowired
	FacultyService facultyService;
//	public FacultyService getFacultyService() {
//		return facultyService;
//	}
//   @Autowired
//	public void setFacultyService(FacultyService facultyService) {
//		this.facultyService = facultyService;
//	}
	@Autowired
	SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@GetMapping("/home")
	public String home() {
		return "welcome home page";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String admin() {
		return "welcome admin";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String user() {
		return "welcome user";
	}
	
	@PostMapping("/admin/insert")
	public Faculty saveFaculty(@RequestBody Faculty faculty)
	{
		log.info("admin save the faculty data");
		return facultyService.saveFaculty(faculty);
	}
	
	@GetMapping("/user/get")
	public List<Faculty> getFaculties() {
	      
		log.info("both user and admin can see all faculties");
		return facultyService.getFaculties();
	}
	
	@GetMapping("/user/get/{facultyId}")
	public Faculty getFacultyById(@PathVariable Integer facultyId)
	{
		log.info("get the faculty data by id");
		return facultyService.getFacultyById(facultyId);
	}
	
	@DeleteMapping("/admin/delete/{facultyId}")
	public String  deleteFaculty(@PathVariable Integer facultyId)
	{
		log.info("deleting the data");
		return facultyService.deleteFaculty(facultyId);
	}
	
	@GetMapping("/user/{facultyId}")
	public Faculty getById(@PathVariable Long facultyId)
	{
		return facultyService.getById(facultyId);
	}
	
	
	@PutMapping("/admin/update")
	public String updateFaculty(@RequestParam Integer facultyId,@RequestBody Faculty faculty)
	{
		log.info("updating the faculty data");
		return facultyService.updateFaculty(facultyId, faculty);
	}
	
	@GetMapping("/user/pagesort/{offset}/{size}/{field}")
	public Page<Faculty> getPageSort(@PathVariable int offset,@PathVariable int size,@PathVariable String field)
	 {
		log.info("pagination and sorting");
		return facultyService.getPageSort(offset, size, field);
	 }

	 @GetMapping("/admin/logout")
	 public String adminLogout(HttpServletRequest request,HttpServletResponse response)
	 {
		 securityContextLogoutHandler.logout(request, response, null);
		 return "admin logged out";
	 }
	 @GetMapping("/user/logout")
	 public String userLogout(HttpServletRequest request,HttpServletResponse response)
	 {
		 securityContextLogoutHandler.logout(request, response, null);
		 return "user logged out";
	 }
	
}
