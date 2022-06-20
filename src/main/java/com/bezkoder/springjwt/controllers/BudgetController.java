package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.*;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class BudgetController {
	@Autowired
    BudgetRepository budgetRepository;
	
	@Autowired
    LaboratoireRepository laboratoireRepository;
	
	@PostMapping(path = "/addBudget")
	@ResponseBody	
	public ResponseEntity<?> createBudget(@RequestBody Budget bdg) {
		if (budgetRepository.findByAnneeAndLaboratoire(bdg.getAnnee(),bdg.getLaboratoire())!=null) {
		      return ResponseEntity.badRequest().body(new MessageResponse("Error: Budget is already taken for this labratory in this year!"));
	    }
		
		budgetRepository.save(bdg);
		return ResponseEntity.ok(new MessageResponse("Budget registered successfully!"));

	}
	
	@GetMapping("/listerBudget")
	public List<Object[]> listerLabo() {
		return budgetRepository.listerBudget();
	}
	
	@DeleteMapping("/Budget/{id}")
	   public ResponseEntity<Budget> deleteLab(@PathVariable(value = "id") int id) {
	      // Optional<Laboratoire> lab = labRepositry.findById(id);
	      try {
	         budgetRepository.deleteById(id);
	         return new ResponseEntity<>(HttpStatus.OK);
	      } catch (Exception e) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	   }
	
	@PostMapping(path ="/recupererBudget")
	@ResponseBody	
	public Budget recupererLab(@RequestBody Budget bdg) {
		System.out.println("valeur recupere ==> "+ bdg.getId());
		return budgetRepository.findById(bdg.getId()).get();
		
	}
	
	@PostMapping(path ="/budgetByAnneeAndLabo")
	@ResponseBody	
	public Budget budgetByAnneeAndLabo(@RequestBody Budget bdg) {
		System.out.println("valeur recupere ==> "+ bdg.getId());
		Laboratoire lb= laboratoireRepository.findById(bdg.getLaboratoire().getId()).get();
		return budgetRepository.findByAnneeAndLaboratoire(bdg.getAnnee(),lb);
		
	}
	
	@PostMapping(path = "/updateBudget")
	@ResponseBody	
	public Budget updateLaboratoire(@RequestBody Budget bdg) {
			return budgetRepository.save(bdg);

	}
}
