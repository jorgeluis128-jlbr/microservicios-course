package com.maverick.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * Notacion opciona porque el declarar en el pom, la dependeica 
 * de client se auto configura como cliente de aeureka 
*/
@EnableEurekaClient 
@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioOauthApplication {
	
	
	// @Autowired
	// private BCryptPasswordEncoder passwordEncode;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOauthApplication.class, args);
	}

	
	/*@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = passwordEncode.encode(password);
			System.out.println(passwordBCrypt);
		}
	}*/

}
