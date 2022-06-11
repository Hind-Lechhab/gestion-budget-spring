package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	UserService userService;
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	@PostMapping("/addResponsable")
	@ResponseBody
//	public ResponseEntity<?> createResponsable(@RequestBody User user){
//		try {
//			User us = new User();
//			us.setUsername(user.getUsername());
//			us.setFirstName(user.getFirstName());
//			us.setLastName(user.getLastName());
//			us.setBirthDate(user.getBirthDate());
//			us.setCin(user.getCin());
//			us.setEmail(user.getEmail());
//			us.setPhoneNumber(user.getPhoneNumber());
//			us.setAddress(user.getAddress());
//			us.setPassword(passwordEncoder.encode(user.getCin()));
//			return new ResponseEntity<>(us, HttpStatus.CREATED);
//		}catch(Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	public User createResponsable(@RequestBody User user) {
		User us = new User();
		us.setUsername(user.getUsername());
		us.setFirstName(user.getFirstName());
		us.setLastName(user.getLastName());
		us.setBirthDate(user.getBirthDate());
		us.setCin(user.getCin());
		us.setEmail(user.getEmail());
		us.setPhoneNumber(user.getPhoneNumber());
		us.setAddress(user.getAddress());
		us.setPassword(getEncodedPassword(user.getCin()));
		return userService.createNewResponsable(us);
	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
