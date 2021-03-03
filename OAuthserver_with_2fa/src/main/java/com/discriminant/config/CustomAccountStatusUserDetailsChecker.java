package com.discriminant.config;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;

import com.discriminant.model.CustomUser;
import com.discriminant.model.UserEntity;


public class CustomAccountStatusUserDetailsChecker {
	
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

	public void check(CustomUser user) {
		if (!user.isAccountNonLocked()) {
			throw new LockedException(messages.getMessage(
					"AccountStatusUserDetailsChecker.locked", "User account is locked"));
		}

		if (!user.isEnabled()) {
			throw new DisabledException(messages.getMessage(
					"AccountStatusUserDetailsChecker.disabled", "User is disabled"));
		}

		if (!user.isAccountNonExpired()) {
			throw new AccountExpiredException(
					messages.getMessage("AccountStatusUserDetailsChecker.expired",
							"User account has expired"));
		}

		if (!user.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException(messages.getMessage(
					"AccountStatusUserDetailsChecker.credentialsExpired",
					"User credentials have expired"));
		}
	}

}
