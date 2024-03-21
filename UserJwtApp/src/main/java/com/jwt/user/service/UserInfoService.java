
package com.jwt.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.user.entity.UserInfo;
import com.jwt.user.entity.UserInfoDetails;
import com.jwt.user.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService{
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> userDetail=userInfoRepository.findByName(username);
		
		return userDetail.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"+username));
	}
	
	public String addUser(UserInfo userInfo)
	{
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "user added successfully";
	}

	public List<UserInfo> getAll() {
		
		return userInfoRepository.findAll();
	}

}
