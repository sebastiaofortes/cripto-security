package com.sebastiaofortes.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.sebastiaofortes.security.security.Model.Usuarios;

@Repository
public class ImplementsUserDetailService implements UserDetailsService{

	@Autowired
	private Usuariorepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuarios usuario = ur.findByLogin(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado !");
		}
		
		return usuario; // usuario implementa o tipo UserDetails por isso pode ser retorndo nessa função
	}

}
