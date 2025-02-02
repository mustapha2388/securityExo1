package com.example.myreadinglist.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Lecteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, unique = true, nullable = false)
	private String username;
	@JsonIgnore
	@Column(length = 100, nullable = false)
	private String password;
	
	private boolean enabled;
	
	@ManyToMany
	private Set<Role> roles;
	@ManyToMany
	private Set<Livre> livres;

}
