package com.example.myreadinglist.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myreadinglist.metier.Lecteur;
import com.example.myreadinglist.metier.Livre;
import com.example.myreadinglist.metier.Role;
import com.example.myreadinglist.repositories.LecteurRepository;
import com.example.myreadinglist.repositories.LivreRepository;
import com.example.myreadinglist.repositories.RoleRepository;

@Service
public class DatabaseInitialiser implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LecteurRepository lecteurRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private LivreRepository livreRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(lecteurRepository.count() == 0 
				&& roleRepository.count() ==0 
				&& livreRepository.count()==0) {
			

			Role r1 = new Role(0, "ROLE_ADMIN", null);
			Role r2 = new Role(0, "ROLE_USER", null);
			r1 = roleRepository.save(r1);
			r2 = roleRepository.save(r2);


			// creation utilisateur admin avec roles admin et user
			Lecteur l1 = new Lecteur(0, "admin", 
					passwordEncoder.encode("admin"), true,
					new HashSet<>(), new HashSet<>());
			// creation utilisateur admin avec roles admin et user
			Lecteur l2 = new Lecteur(0, "mouss", 
					passwordEncoder.encode("azerty"), true,
					new HashSet<>(), new HashSet<>());

			l1.getRoles().add(r1);
			l1.getRoles().add(r2);
			l2.getRoles().add(r2);
			lecteurRepository.save(l1);
			lecteurRepository.save(l2);
			
			Set<Lecteur> listLecteur = null;
			Set<Lecteur> listLecteur2 = null;

			listLecteur.add(l1);
			listLecteur2.add(l2);
			Livre book1 = new Livre(0, "dragonball", "5672439847", listLecteur);
			Livre book2 = new Livre(0, "les miserables", "97653032766", listLecteur2);
			
		
//			Livre book1 = new Livre(0, "dragonball", "5672439847", null);
//			Livre book2 = new Livre(0, "les miserables", "97653032766", null);
//			book1.getLecteurs().add(l1);
//			book2.getLecteurs().add(l2);
			
			
			livreRepository.save(book1);
			livreRepository.save(book2);
			
			
		}
	}

}
