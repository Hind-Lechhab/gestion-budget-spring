package com.bezkoder.springjwt.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.LaboratoireRepository;
import com.bezkoder.springjwt.security.services.LaboratoireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")

public class LaboratoireController {
	@Autowired
	LaboratoireService laboratoireService;
	
	
	@PostMapping(path = "/addLaboratoire")
	@ResponseBody
	public ResponseEntity<Laboratoire> createLaboratoire(@RequestBody Laboratoire lab){
		try {
			Laboratoire labo = new Laboratoire();
			labo.setName(lab.getName());
			labo.setAdress(lab.getAdress());
			labo.setIsResponsable(false);
			labo.setPhoneNumber(lab.getPhoneNumber());
			labo.setSecteurActivite(lab.getSecteurActivite());
			laboratoireService.addNewLabo(lab);
			return new ResponseEntity<>(labo, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
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
