package com.spring.secure.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.secure.dto.UserDto;
import com.spring.secure.model.User;
import com.spring.secure.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
		User save = userRepository.save(user);
		return save;
	}

	public User getUser(Long id)
	{
		Optional<User> byId = userRepository.findById(id);
		return byId.get();
	}
	
	public User deleteUser(Long id)
	{
		User user = userRepository.findById(id).get();
		userRepository.deleteById(id);
		return user;
	}
	
	public List<User> getalluser(){
		List<User> all = userRepository.findAll();
		return all;
	}
}