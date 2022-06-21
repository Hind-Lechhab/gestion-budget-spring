package com.bezkoder.springjwt.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Besoin {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private double fraix;
	
	
	@ManyToOne
	private User employe;
	
	@ManyToOne
	private Type type;
	
	
	
}
