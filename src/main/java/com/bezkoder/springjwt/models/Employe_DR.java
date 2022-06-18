package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity  @NoArgsConstructor @ToString
public class Employe_DR {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	private double partEmpDR;

	@ManyToOne
	private User employe;
	
	@ManyToOne
	private Budget budget_dr;
	
	
}
