package com.maverick.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.maverick.commons.usuarios.models.entity.Usuario;
import com.maverick.oauth.feign.service.UsuarioServiceInterface;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenyicationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private UsuarioServiceInterface usuarioService;

	@Autowired
	private Environment env;

	@Autowired
	private Tracer tracer;

	private Logger log = LoggerFactory.getLogger(AuthenyicationSuccessErrorHandler.class);

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if (authentication.getName().equalsIgnoreCase(env.getProperty("config.security.oauth.client.id"))) {
			return;
		}
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.info("Succes Login: authentication.getName() " + authentication.getName());
		log.info("Succes Login: user.getUsername() " + user.getUsername());

		// Reinicio de intentos
		Usuario usuarioDTO = usuarioService.findByUsername(user.getUsername());
		if (usuarioDTO.getIntentos() != null && usuarioDTO.getIntentos() > 0) {
			usuarioDTO.setIntentos(0);
			usuarioService.update(usuarioDTO, usuarioDTO.getId());
		}

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);
		// Para increementar intent, cada que hay un error al loguearse
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(mensaje);
			Usuario usuarioDTO = usuarioService.findByUsername(authentication.getName());
			if (usuarioDTO.getIntentos() == null) {
				usuarioDTO.setIntentos(0);
			}

			usuarioDTO.setIntentos(usuarioDTO.getIntentos() + 1);
			sb.append(" - Intentos del login: " + usuarioDTO.getIntentos());

			if (usuarioDTO.getIntentos() >= 3) {
				String error = String.format("El usuario %s des-habilitado por maximo de intentos",
						authentication.getName());
				log.error(error);
				usuarioDTO.setEnabled(false);
				sb.append(" - " + error);
			}

			usuarioService.update(usuarioDTO, usuarioDTO.getId());
			tracer.currentSpan().tag("error.mensaje", sb.toString());

		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}

}
