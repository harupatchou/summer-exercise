package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/loginUser","/user/form","/user/create").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/search","/event","/event/edit").hasAnyRole("ROLE_USER");
		
		
		http.formLogin().loginProcessingUrl("/loginUser/login").loginPage("/loginUser").failureUrl("/loginUser/error")
				.defaultSuccessUrl("/events/goToSearch", true).usernameParameter("mailAddress").passwordParameter("password").and();
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout/sessionInvalidate")).logoutSuccessUrl("/loginUser");

	}

	@Configuration
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService); // .passwordEncoder(passwordEncoder());
		}

	}
}
