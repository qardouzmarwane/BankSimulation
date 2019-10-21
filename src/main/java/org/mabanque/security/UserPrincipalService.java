package org.mabanque.security;

import org.mabanque.dao.ClientRepository;
import org.mabanque.metier.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserPrincipalService implements UserDetailsService {
	@Autowired
	private ClientRepository clientRepository;
	

	public UserPrincipalService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) {
		Client client = this.clientRepository.findByUsername(username);
		
		UserPrincipalDetails u = new UserPrincipalDetails(client);
		return u;
	}

}
