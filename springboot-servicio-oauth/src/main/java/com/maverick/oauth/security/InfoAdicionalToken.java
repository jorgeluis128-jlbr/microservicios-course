package com.maverick.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.maverick.commons.usuarios.models.entity.Usuario;
import com.maverick.oauth.feign.service.UsuarioServiceInterface;


@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private UsuarioServiceInterface usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		// Consultamos la informacion del usuario firmado 
		Usuario infoUsuario = usuarioService.findByUsername(authentication.getName());
		info.put("nombre", infoUsuario.getNombre());
		info.put("apellido", infoUsuario.getApellido());
		info.put("correo", infoUsuario.getEmail());
		
	   /* Agregamos informacion del usuario al accessToken, pero la interface es generica por lo que se tiene que,
		* implementar una en concreto que es DefaultOAuth2AccessToken, se hace el cast de accessToken para poner 
		* implementar el metodo setAdditionalInformation
		*/
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
