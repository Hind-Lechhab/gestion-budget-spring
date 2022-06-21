package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.models.Type;
import com.bezkoder.springjwt.repository.TypeReopository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/test")
public class TypeController {
	@Autowired
	TypeReopository typeReopository;
	
	@GetMapping("/listerType")
	public List<Type> listerType() {
		return typeReopository.findAll();
	}
}
