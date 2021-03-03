package com.discriminant.model;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String first_name;
	private String last_name;
	private String mobile;
	private String country;
	private String user_type;
	private String is_tfa_enabled;
	private String tfa_default_type;
	private boolean enabled;//true if the user is enabled, false otherwise
    private boolean accountNonExpired;//true if the user's account is valid (ie non-expired), false if no longer valid (ie expired)
    private boolean credentialsNonExpired;//true if the user's credentials are valid (ie non-expired), false if no longer valid (ie expired)
    private boolean accountNonLocked;//table, if “accountNonLocked” = 0 or false, means this user is in locked status.true if the user is not locked, false otherwise
	

	public CustomUser(UserEntity user) {
		super(user.getEmailId(), user.getPassword(), user.getGrantedAuthoritiesList());
		this.id = user.getId();
		this.first_name = user.getFirst_name();
		this.last_name = user.getLast_name();
		this.mobile = user.getMobile();
		this.country = user.getCountry();
		this.user_type = user.getUser_type();
		this.is_tfa_enabled = user.getIs_tfa_enabled();
		this.tfa_default_type = user.getTfa_default_type();
		this.enabled = user.isEnabled();
		this.accountNonExpired = user.isAccountNonExpired();
		this.credentialsNonExpired = user.isCredentialsNonExpired();
		this.accountNonLocked = user.isAccountNonExpired();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public String getIs_tfa_enabled() {
		return is_tfa_enabled;
	}

	public void setIs_tfa_enabled(String is_tfa_enabled) {
		this.is_tfa_enabled = is_tfa_enabled;
	}

	public String getTfa_default_type() {
		return tfa_default_type;
	}

	public void setTfa_default_type(String tfa_default_type) {
		this.tfa_default_type = tfa_default_type;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	
}
