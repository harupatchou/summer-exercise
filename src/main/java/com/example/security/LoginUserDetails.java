package com.example.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.domain.User;

import lombok.Data;
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User{
	private final User user;
	
	public LoginUserDetails(User user){
		super(user.getName(),user.getPassword(),AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}

}
