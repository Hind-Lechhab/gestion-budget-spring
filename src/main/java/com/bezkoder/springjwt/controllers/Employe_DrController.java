package com.bezkoder.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Budget;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Employe_DR;
import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.BudgetRepository;
import com.bezkoder.springjwt.repository.Employe_DRRepository;
import com.bezkoder.springjwt.repository.LaboratoireRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class Employe_DrController {
	@Autowired
	Employe_DRRepository employe_DRRepository;
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	LaboratoireRepository laboratoireRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/addEmployee_Dr")
	@ResponseBody
	public ResponseEntity<?> createEmploye_Dr(@RequestBody Employe_DR employe_DR) {
		
	    User employe =userRepository.findById(employe_DR.getEmploye().getId()).get();
	    
	    Budget bdg=budgetRepository.findById(employe_DR.getBudget_dr().getId()).get();
	    bdg.setSomme_dr(bdg.getSomme_dr()-employe_DR.getPartEmpDR());
	    budgetRepository.save(bdg);
	    
	    employe_DR.setEmploye(employe);
	    employe_DR.setBudget_dr(bdg);
	    employe_DRRepository.save(employe_DR);
	    
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping(path ="/listAffectation")
	@ResponseBody	
	public List<Object[]> budgetByAnneeAndLabo(@RequestBody Budget bdg) {
		System.out.println("valeur recupere ==> "+ bdg.getId());
		Laboratoire lb= laboratoireRepository.findById(bdg.getLaboratoire().getId()).get();
		return employe_DRRepository.listerAffectationBudget(bdg.getAnnee(),lb.getId());
		
	}
	
}
