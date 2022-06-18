package com.bezkoder.springjwt.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.*;
import com.bezkoder.springjwt.security.services.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	UserService userService;
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    LaboratoireRepository laboratoireRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
	
	@PostMapping("/addResponsable")
	@ResponseBody
	public ResponseEntity<?> createResponsable(@RequestBody User user) {

		if (userRepository.existsByUsername(user.getUsername())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(user.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
		user.setPassword(getEncodedPassword(user.getCin()));
		//user.getRoles().add(new Role(ERole.ROLE_RESPONSABLE));
		Set<Role> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();
		
	    Role userRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    roles.add(userRole);
	    
		Laboratoire l=laboratoireRepository.findById(user.getLaboratoire().getId()).get();
		l.setIsResponsable(true);
		laboratoireRepository.save(l);
		
		user.setRoles(roles);
		userService.createNewResponsable(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}	
//	public User createResponsable(@RequestBody User user) {
//		user.setPassword(getEncodedPassword(user.getCin()));
//		Laboratoire l=laboratoireRepository.findById(user.getLaboratoire().getId()).get();
//		l.setIsResponsable(true);
//		laboratoireRepository.save(l);
//		return userService.createNewResponsable(user);
//		
//	}
	
	
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}