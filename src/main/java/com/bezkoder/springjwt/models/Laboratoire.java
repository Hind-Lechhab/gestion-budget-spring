package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Laboratoire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 60)
	private String name;
	
	@Size(max = 120)
	private String adress;
	
	@Size(max = 120)
	private String phoneNumber;
	
	private Boolean isResponsable;
	
	@Size(max = 120)
	private String secteurActivite;
	
	@OneToMany(mappedBy = "laboratoire" , cascade = CascadeType.REMOVE) @JsonIgnore
	List<User> listeUsers = new ArrayList<>();
	
	@OneToMany(mappedBy = "laboratoire" , cascade = CascadeType.ALL) @JsonIgnore
	List<Budget> listeBudgets = new ArrayList<>();

	public Laboratoire(String name, String adress, String phoneNumber,String secteurActivite, Boolean isResponsable) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.isResponsable = isResponsable;
		this.secteurActivite = secteurActivite;
	}
	
	

}
