package com.bezkoder.springjwt.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.LaboratoireRepository;
import com.bezkoder.springjwt.security.services.LaboratoireService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class LaboratoireController {
	@Autowired
	LaboratoireService laboratoireService;
	
	@Autowired
    LaboratoireRepository laboratoireRepository;
	
	
	@PostMapping(path = "/addLaboratoire")
	@ResponseBody	
	public Laboratoire createLaboratoire(@RequestBody Laboratoire lab) {
			Laboratoire labo = new Laboratoire();
			labo.setName(lab.getName());
			labo.setAdress(lab.getAdress());
			labo.setIsResponsable(false);
			labo.setPhoneNumber(lab.getPhoneNumber());
			labo.setSecteurActivite(lab.getSecteurActivite());
			return laboratoireService.addNewLabo(labo);

	}
	
	@PostMapping(path = "/updateLaboratoire")
	@ResponseBody	
	public Laboratoire updateLaboratoire(@RequestBody Laboratoire lab) {
			Laboratoire labo = laboratoireRepository.findById(lab.getId()).get();
			labo.setName(lab.getName());
			labo.setAdress(lab.getAdress());
			labo.setIsResponsable(false);
			labo.setPhoneNumber(lab.getPhoneNumber());
			labo.setSecteurActivite(lab.getSecteurActivite());
			return laboratoireRepository.save(labo);

	}
	
	@PostMapping(path ="/recupererLab")
	@ResponseBody	
	public Laboratoire recupererLab(@RequestBody Laboratoire lab) {
		System.out.println("valeur recupere ==> "+ lab.getId());
		return laboratoireRepository.findById(lab.getId()).get();
		
	}
	
	@GetMapping("/listerLabo")
	public List<Laboratoire> listerLabo() {
		return laboratoireRepository.findByIsResponsable(false);
	}
	
	@GetMapping("/listerTousLabo")
	public List<Laboratoire> listerTousLabo() {
		return laboratoireRepository.findAll();
		
	}
	
	@GetMapping("/listerTousLabsParRespo")
	public List<Object[]> listerTousLabs2() {
		return laboratoireRepository.listertous2();
		
	}
	
	@DeleteMapping("/{id}")
	   public ResponseEntity<Laboratoire> deleteLab(@PathVariable(value = "id") long id) {
	      // Optional<Laboratoire> lab = labRepositry.findById(id);
	      try {
	         laboratoireRepository.deleteById(id);
	         return new ResponseEntity<>(HttpStatus.OK);
	      } catch (Exception e) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	   }
	
//	@GetMapping("/suprimerLabo")
//	@PostMapping(path = "/suprimerLabo")
//	@ResponseBody
//	public void supprimerLab(@RequestBody Laboratoire lab) {
//		laboratoireRepository.deleteById(lab.getId());
//		
//	}
	
//	public Laboratoire addNewLaboratoire(@RequestBody Laboratoire lab) {
//		Laboratoire labo = new Laboratoire();
//		labo.setName(lab.getName());
//		labo.setAdress(lab.getAdress());
//		labo.setIsResponsable(false);
//		labo.setPhoneNumber(lab.getPhoneNumber());
//		labo.setSecteurActivite(lab.getSecteurActivite());
//		return laboratoireService.addNewLabo(labo);
//	}
	
}
