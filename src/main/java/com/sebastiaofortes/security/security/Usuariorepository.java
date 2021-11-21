package com.sebastiaofortes.security.security;




import org.springframework.data.repository.CrudRepository;

import com.sebastiaofortes.security.security.Model.Usuarios;

public interface Usuariorepository extends CrudRepository<Usuarios, String>{
	// O Spring Data JPA se encarrega de criar automaticamente os métodos de busca de dados declarados na interface.
	// para isso ele busca os nomes dos atributos da classe de entidade nos nomes dos métodos declarados na interface.
	// portanto o nome do método deve conter o nome do atributo da classe de entidade que se deseja buscar no banco.
	Usuarios findByLogin (String login); 
}
