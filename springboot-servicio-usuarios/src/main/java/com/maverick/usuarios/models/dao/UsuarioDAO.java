package com.maverick.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.maverick.commons.usuarios.models.entity.Usuario;



@RepositoryRestResource(path = "usuarios")
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {
	
	@RestResource(path="buscar-user")
	public Usuario findByUsername(@Param("username") String username);
	
	@Query(value="select u from Usuario u where u.username=?1", nativeQuery = false)
	public Usuario getUSerByUsername(String username);

}
