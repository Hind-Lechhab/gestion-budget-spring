package com.bezkoder.springjwt.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Budget;
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
	
	//Laboratoire malaboratoire=new Laboratoire();
	Long id_Myrespo;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    LaboratoireRepository laboratoireRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
	
	
	
	@PostMapping("/addEmployee")
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
		//Set<Role> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();
		
	    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
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
	
	@GetMapping("/listerRespo")
	public List<Object[]> listerRespo() {
		return userRepository.listRespo();
	}
//	
//	@GetMapping("/listerEmployee")
	@PostMapping(path ="/listerEmployee")
	@ResponseBody	
	public List<Object[]> listerEmployee(@RequestBody User rsp) {
		System.out.println(rsp);
		User us=userRepository.findByUsername(rsp.getUsername()).get();
		Laboratoire l=us.getLaboratoire();
		return userRepository.listEmployee(l.getId());
	}
	
	@DeleteMapping("/user/{id}")
	   public ResponseEntity<User> deleteLab(@PathVariable(value = "id") long id) {
	      // Optional<Laboratoire> lab = labRepositry.findById(id);
	      try {
	    	 User u=userRepository.findById(id).get();
	    	 Laboratoire l=u.getLaboratoire();
	         userRepository.deleteById(id);
	    	 l.setIsResponsable(false);
	    	 laboratoireRepository.save(l);
	         return new ResponseEntity<>(HttpStatus.OK);
	      } catch (Exception e) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	   }
	
	@PostMapping(path ="/recupererRespo")
	@ResponseBody	
	public User recupererRespo(@RequestBody User rsp) {
		
		System.out.println("valeur recupere ==> "+ rsp.getId());
		System.out.println("valeur recupere ==> "+ rsp.getId());
		id_Myrespo=rsp.getId();
//		//User us=userRepository.findById(rsp.getId()).get();
////		System.out.println(us);
////		malaboratoire=laboratoireRepository.findById(us.getLaboratoire().getId()).get();
//		if(userRepository.findById(rsp.getId()).get()!=null) System.out.println(userRepository.findById(rsp.getId()).get());
		return userRepository.findById(rsp.getId()).get();
		
		
	}
	
	@PostMapping(path = "/updateResponsable")
	@ResponseBody	
	public User updateRespo(@RequestBody User user) {
		
		user.setPassword(getEncodedPassword(user.getCin()));
		//user.getRoles().add(new Role(ERole.ROLE_RESPONSABLE));
		Set<Role> roles = new HashSet<>();
		
	    Role userRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    roles.add(userRole);
	    user.setRoles(roles);
		Laboratoire l=laboratoireRepository.findById(user.getLaboratoire().getId()).get();
		l.setIsResponsable(true);
		User userResp=userRepository.findById(id_Myrespo).get();
		Laboratoire malaboratoire=userResp.getLaboratoire();
		if (malaboratoire.getId()!=l.getId())
		{
			malaboratoire.setIsResponsable(false);
			laboratoireRepository.save(malaboratoire);
		}
		laboratoireRepository.save(l);
		return userRepository.save(user);

	}
	
	@PostMapping(path = "/updateEmployee")
	@ResponseBody	
	public User updateEmployee(@RequestBody User user) {
		
		user.setPassword(getEncodedPassword(user.getCin()));
		//user.getRoles().add(new Role(ERole.ROLE_RESPONSABLE));
		Set<Role> roles = new HashSet<>();
		
	    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    roles.add(userRole);
	    user.setRoles(roles);
		Laboratoire l=laboratoireRepository.findById(user.getLaboratoire().getId()).get();
		l.setIsResponsable(true);
		User userResp=userRepository.findById(id_Myrespo).get();
		Laboratoire malaboratoire=userResp.getLaboratoire();
		if (malaboratoire.getId()!=l.getId())
		{
			malaboratoire.setIsResponsable(false);
			laboratoireRepository.save(malaboratoire);
		}
		laboratoireRepository.save(l);
		return userRepository.save(user);

	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}