package org.mabanque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	private UserPrincipalService userPrincipalService;
	
	
	public SecurityConfiguration(UserPrincipalService userPrincipalService) {
		super();
		this.userPrincipalService = userPrincipalService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth){
	auth    .authenticationProvider(authentificationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http      .authorizeRequests()
				  .anyRequest().authenticated()
				  .and()
				  .httpBasic();
	}
	@Bean
	DaoAuthenticationProvider authentificationProvider()
	{
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder());
		dao.setUserDetailsService(this.userPrincipalService);
		
		return dao;
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
