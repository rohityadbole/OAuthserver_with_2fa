package com.discriminant.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.discriminant.model.CustomUser;


public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
		if (user.getId() != null)
			info.put("id", user.getId());
		if (user.getName() != null)
			info.put("name", user.getName());
		if (user.getUsername() != null)
			info.put("userName", user.getUsername());
		if (user.getUsername() != null)
			info.put("first_name", user.getFirst_name());
		if (user.getUsername() != null)
			info.put("last_name", user.getLast_name());
		if (user.getUsername() != null)
			info.put("mobile", user.getMobile());
		if (user.getUsername() != null)
			info.put("country", user.getCountry());
		if (user.getUsername() != null)
			info.put("user_type", user.getUser_type());
		if (user.getIs_tfa_enabled() != null)
			info.put("is_tfa_enabled", user.getIs_tfa_enabled());
		if (user.getTfa_default_type() != null)
			info.put("tfa_default_type", user.getTfa_default_type());
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		return super.enhance(customAccessToken, authentication);
	}	
}