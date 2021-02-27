package com.oauthserver2fa.dao;
import com.oauthserver2fa.model.UserEntity;

public interface OAuthDAOService {
	
	public UserEntity getUserDetails(String emailId);
}
