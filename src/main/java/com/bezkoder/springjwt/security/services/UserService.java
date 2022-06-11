package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
  public User createNewResponsable(User user){
	  return userRepository.save(user);
  }


}
