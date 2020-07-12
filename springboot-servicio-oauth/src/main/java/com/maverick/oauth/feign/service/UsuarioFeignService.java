package com.maverick.oauth.feign.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maverick.commons.usuarios.models.entity.Usuario;
import com.maverick.oauth.feign.client.UsuarioFeignClient;

import feign.FeignException;

@Service
public class UsuarioFeignService implements UserDetailsService, UsuarioServiceInterface {

	private Logger log = LoggerFactory.getLogger(UsuarioFeignService.class);

	@Autowired
	private UsuarioFeignClient clientFeing;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			Usuario user = clientFeing.getUsuario(username);

			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

			return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
		} catch (FeignException e) {
			log.error("Error en login, no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en login, no existe el usuario '" + username + "' en el sistema");
		}
	}

	// Metodos de la interface de usuarios
	@Override
	public Usuario findByUsername(String username) {
		return clientFeing.getUsuario(username);
	}

	@Override
	public Usuario update(Usuario username, Long id) {
		return clientFeing.update(username, id);
	}

}
