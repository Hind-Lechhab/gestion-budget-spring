package com.bezkoder.springjwt.models;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 20)
  private String username;

  @Size(max = 50)
  @Email
  private String email;

  @Size(max = 120)
  private String password;
  
  @Size(max = 50)
  private String firstName;
  
  @Size(max = 50)
  private String lastName;
  
  @Size(max = 50)
  private String phoneNumber;
  
  @Size(max = 50)
  private String address;
  
  @Size(max = 50)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private String birthDate;
  
  @Size(max = 50)
  private String cin;
  

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  
  @ManyToOne
  private Laboratoire laboratoire;
  
  @OneToMany(mappedBy = "employe")
  private List<Employe_DR> emlpoye_dr =new ArrayList<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
