package com.discriminant.dao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.discriminant.model.UserEntity;


@Repository
public class OAuthDAOServiceImpl implements OAuthDAOService {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public UserEntity getUserDetails(String emailId) {
		
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		
		List<UserEntity> list = jdbcTemplate.query("SELECT * FROM USER WHERE EMAIL_ID=?", new String[] { emailId },
				(ResultSet rs, int rowNum) -> {
					UserEntity user = new UserEntity();
					user.setEmailId(emailId);
					user.setId(rs.getString("ID"));
					user.setName(rs.getString("EMAIL_ID"));
					user.setPassword(rs.getString("PASSWORD"));
					user.setFirst_name(rs.getString("FIRST_NAME"));
					user.setLast_name(rs.getString("LAST_NAME"));
					user.setMobile(rs.getString("MOBILE"));
					user.setUser_type(rs.getString("USER_TYPE"));
					user.setIs_tfa_enabled(rs.getString("IS_TFA_ENABLED"));
					user.setTfa_default_type(rs.getString("TFA_DEFAULT_TYPE"));
					user.setEnabled(rs.getBoolean("ENABLED"));
					user.setAccountNonExpired(rs.getBoolean("ACCOUNTNONEXPIRED"));
					user.setCredentialsNonExpired(rs.getBoolean("CREDENTIALSNONEXPIRED"));
					user.setAccountNonLocked(rs.getBoolean("ACCOUNTNONLOCKED"));
					return user;
				});

/*		if(!list.isEmpty()) {
			
			UserEntity userEntity = list.get(0);
			
			List<String> permissionList = jdbcTemplate.query("SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSION P \r\n" + 
					"INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID\r\n" + 
					"INNER JOIN ROLE R ON R.ID=P_R.ROLE_ID \r\n" + 
					"INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID\r\n" + 
					"INNER JOIN USER U ON U.ID=U_R.USER_ID\r\n" + 
					"WHERE U.EMAIL_ID=?", new String[] { userEntity.getEmailId() },
					(ResultSet rs, int rowNum) -> {
						return "ROLE_" + rs.getString("PERMISSION_NAME");
					});
			
			if (permissionList != null && !permissionList.isEmpty()) {
				for (String permission : permissionList) {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
					grantedAuthoritiesList.add(grantedAuthority);
				}
				userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
			}
			return userEntity;
		}*/
		
		if (!list.isEmpty()) {
			UserEntity userEntity = list.get(0);

			if (userEntity.getUser_type() != null) {
				/*if (userEntity.getUser_type().trim().equalsIgnoreCase("super_admin")) {
					String permissionQuery = "select distinct p.permission_name from user u inner join assign_user_to_role r_u on u.id=r_u.user_id inner join role r on r_u.role_id=r.id inner join assign_permission_to_role r_p on r_p.role_id=r.id inner join permission p on p.id=r_p.permission_id where u.email_id=?";
					List<String> permissionList = jdbcTemplate.query(permissionQuery.toString(),
							new String[] { emailId }, (ResultSet rs, int rowNum) -> {
								return "ROLE_" + rs.getString("permission_name");
							});
					if (permissionList != null && !permissionList.isEmpty()) {
						for (String permission : permissionList) {
							GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
							grantedAuthoritiesList.add(grantedAuthority);
						}
						list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
					}
					return list.get(0);
				} else {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_administrator");
					grantedAuthoritiesList.add(grantedAuthority);
					list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
					return list.get(0);
				}*/
				
				String permissionQuery = "select distinct p.permission_name from user u inner join assign_user_to_role r_u on u.id=r_u.user_id inner join role r on r_u.role_id=r.id inner join assign_permission_to_role r_p on r_p.role_id=r.id inner join permission p on p.id=r_p.permission_id where u.email_id=?";
				List<String> permissionList = jdbcTemplate.query(permissionQuery.toString(),
						new String[] { emailId }, (ResultSet rs, int rowNum) -> {
							return "ROLE_" + rs.getString("permission_name");
						});
				if (permissionList != null && !permissionList.isEmpty()) {
					for (String permission : permissionList) {
						GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
						grantedAuthoritiesList.add(grantedAuthority);
					}
					list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
				}
				return list.get(0);
				
			} else {
				return null;
			}
		}
		
		return null;
	
		
		
	}

}