package com.example.myreadinglist.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myreadinglist.metier.Lecteur;
import com.example.myreadinglist.repositories.LecteurRepository;
import com.example.myreadinglist.repositories.LivreRepository;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class LecteurController {
	
	@Autowired
	private LecteurRepository lecteurRepository;
	@Autowired
	private LivreRepository livreRepository;
	

	@GetMapping
	public Map<String, String> hello(Principal p) {
		return Collections.singletonMap("message",
											"bonjour " + p.getName()
											+ " , le " + LocalDateTime.now());
	}
	
	@GetMapping("/my-list-book")
	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Lecteur> findList(@PathVariable("username") String username) {
		return this.lecteurRepository
					.findWithRoleByUsername(username)
					.map(lecteur -> 
								new ResponseEntity<>(lecteur, HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		

}
