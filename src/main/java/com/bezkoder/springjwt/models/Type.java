package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Type {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String designation;
	
	@OneToMany(mappedBy = "type",cascade = CascadeType.ALL)
	private List<Besoin> besoins=new ArrayList<>();
	
}
