package com.example.myreadinglist.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.myreadinglist.metier.Lecteur;

public class CustomUserDetails implements UserDetails {

	private final Lecteur lecteur;
	
	public CustomUserDetails(Lecteur lecteur) {
		this.lecteur = lecteur;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.lecteur.getPassword();
	}

	@Override
	public String getUsername() {
		return this.lecteur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.lecteur.isEnabled();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.lecteur.getRoles().stream()
				.map(r -> r.getRoleName())
				.map(rolename -> new SimpleGrantedAuthority(rolename))
				.collect(Collectors.toList());
	}

}
