package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString

public  class Budget {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String annee;
	private double somme;
	private double somme_dr;
	private double somme_db;
	
	@ManyToOne
	private Laboratoire laboratoire;
	
	 
	  @OneToMany(mappedBy = "budget_dr")
	  private List<Employe_DR> emlpoye_dr =new ArrayList<>();
	
	
	
	
	
}
