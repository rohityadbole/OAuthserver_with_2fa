package com.discriminant.dao;
import com.discriminant.model.UserEntity;

public interface OAuthDAOService {
	
	public UserEntity getUserDetails(String emailId);
}
