package com.maverick.oauth.feign.service;

import com.maverick.commons.usuarios.models.entity.Usuario;

public interface UsuarioServiceInterface {
	
	
	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario username, Long id);

}
