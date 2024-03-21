package com.spring.secure.service;



import java.util.List;

import com.spring.secure.dto.UserDto;
import com.spring.secure.model.User;

public interface UserService {
	
	User save (UserDto userDto);
	User getUser(Long id);
	User deleteUser(Long id);
	List<User> getalluser();
}