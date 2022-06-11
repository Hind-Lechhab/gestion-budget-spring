package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Laboratoire;

@Repository
public interface LaboratoireRepository extends JpaRepository<Laboratoire, Long> {
	public Laboratoire findByName(String name);
	public Laboratoire findById(Laboratoire lab);
	public boolean existsByName(String name);

}
