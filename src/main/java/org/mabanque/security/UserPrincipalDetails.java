package org.mabanque.security;

import java.util.Collection;

import org.mabanque.metier.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipalDetails implements UserDetails{

	Client client;
	
	
	
	
	public UserPrincipalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPrincipalDetails(Client client) {
		super();
		this.client = client;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.client.getPassword();
	}

	@Override
	public String getUsername() {
		return this.client.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
