package com.oauthserver2fa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuthserverWith2faApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthserverWith2faApplication.class, args);
	}

}
