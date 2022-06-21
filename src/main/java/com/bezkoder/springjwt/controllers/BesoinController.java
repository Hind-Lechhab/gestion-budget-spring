package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Besoin;
import com.bezkoder.springjwt.models.Budget;
import com.bezkoder.springjwt.models.Type;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.BesoinRepository;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.BudgetRepository;
import com.bezkoder.springjwt.repository.LaboratoireRepository;
import com.bezkoder.springjwt.repository.TypeReopository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class BesoinController {

	@Autowired
    UserRepository userRepository;
	
	
	@Autowired
    BesoinRepository besoinRepository;
	
	@Autowired
	TypeReopository typeReopository;
	
	@PostMapping(path = "/addBesoin")
	@ResponseBody	
	public ResponseEntity<?> createBudget(@RequestBody Besoin bs) {
		Type tp=typeReopository.findById(bs.getType().getId()).get();
		User empl=userRepository.findByUsername(bs.getEmploye().getUsername()).get();
		bs.setEmploye(empl);
		bs.setType(tp);
		besoinRepository.save(bs);
		return ResponseEntity.ok(new MessageResponse("Besoin registered successfully!"));

	}
}
