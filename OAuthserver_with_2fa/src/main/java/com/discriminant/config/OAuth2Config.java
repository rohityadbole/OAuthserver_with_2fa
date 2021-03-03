package com.discriminant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import java.security.KeyPair;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	/*@Value("${config.oauth2.clientid}")
	private String clientid;

	@Value("${config.oauth2.clientSecret}")
	private String clientSecret;

	@Value("${config.oauth2.privateKey}")
	private String privateKey;

	@Value("${config.oauth2.publicKey}")
	private String publicKey;*/

	/*@Autowired
	PasswordEncoder passwordEncoder;*/

	private final SecurityProperties securityProperties;
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private final DataSource dataSource;
	private final UserDetailsService userDetailsService;
	private TokenStore tokenStore;
	private final PasswordEncoder passwordEncoder;


/*	@Autowired
	@Qualifier("authenticationManagerBean")*/
	//private AuthenticationManager authenticationManager;
	private final AuthenticationManager authenticationManager;

	public OAuth2Config(final DataSource dataSource,final SecurityProperties securityProperties,final UserDetailsService userDetailsService,final PasswordEncoder passwordEncoder,
            final AuthenticationManager authenticationManager) {

		this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
		this.securityProperties = securityProperties;
        this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(this.dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenEnhancer());*/
		endpoints.authenticationManager(this.authenticationManager)
        .accessTokenConverter(tokenEnhancer())
        .userDetailsService(this.userDetailsService)
        .tokenStore(tokenStore());
	}

	@Bean
	public TokenStore tokenStore() {
		
		 if (tokenStore == null) {
	            tokenStore = new JwtTokenStore(tokenEnhancer());
	        }
	        return tokenStore;
	}
	
	@Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore,
                                              final ClientDetailsService clientDetailsService) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(this.authenticationManager);
        return tokenServices;
    }

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		/*
		 * JwtAccessTokenConverter converter = new CustomTokenEnhancer();
		 * converter.setSigningKey(privateKey); converter.setVerifierKey(publicKey);
		 * return converter;
		 */
		 if (jwtAccessTokenConverter != null) {
	            return jwtAccessTokenConverter;
	        }
		 SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
	        KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));

	        jwtAccessTokenConverter = new CustomTokenEnhancer();
	        jwtAccessTokenConverter.setKeyPair(keyPair);
	        return jwtAccessTokenConverter;
	}
	
	 private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
	        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
	    }

	    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
	        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
	    }

}
