package com.sebastiaofortes.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()  						// endereço permitido a todos
		.antMatchers(HttpMethod.GET, "/main/listando").permitAll()			// endereço permitido a todos
		.antMatchers(HttpMethod.GET, "/main/cadastro").permitAll()	
		.antMatchers(HttpMethod.POST, "/main/add").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	  
	  @Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	    .userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // a autenticção será usando uma implemetacão da interface userDetailsService
	   // o método passwordEncoder acima é obrigatório e é usado para defnir a criptografia.
	    
	  }
	  
	/*
	  
	  @Override
	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder
	        .inMemoryAuthentication()
	        .withUser("garrincha").password("{noop}123")
	            .roles("USER")
	        .and()
	        .withUser("zico").password("{noop}123")
	            .roles("USER");
	  }
	  
	  */
	}


	

	
	


