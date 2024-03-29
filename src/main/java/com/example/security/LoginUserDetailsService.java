package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
		User user = userRepository.findByMailAddress(mailAddress);
		if (user == null) {
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginUserDetails(user);
	}
}
