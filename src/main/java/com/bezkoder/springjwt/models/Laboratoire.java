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
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Laboratoire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String name;
	
	@NotBlank
	@Size(max = 120)
	private String adress;
	
	@NotBlank
	@Size(max = 120)
	private String phoneNumber;
	
	private Boolean isResponsable;
	
	@NotBlank
	@Size(max = 120)
	private String secteurActivite;
	
	@ManyToMany
	List<User> listeUsers = new ArrayList<>();

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
