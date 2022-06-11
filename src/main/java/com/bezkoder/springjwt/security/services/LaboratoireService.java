package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Laboratoire;
import com.bezkoder.springjwt.repository.LaboratoireRepository;

@Service
public class LaboratoireService {
	@Autowired
	LaboratoireRepository laboratoireRepository;

	public Laboratoire addNewLabo(Laboratoire lab) {
		return laboratoireRepository.save(lab);
	}
}
