package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Type;

@Repository
public interface TypeReopository extends JpaRepository<Type, Integer>{
	
}
