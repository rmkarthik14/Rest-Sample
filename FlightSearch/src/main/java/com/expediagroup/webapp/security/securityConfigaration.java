package com.expediagroup.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class securityConfigaration extends WebSecurityConfigurerAdapter{
	
	protected void configure(AuthenticationManagerBuilder authenticate) throws Exception {
		
		authenticate.inMemoryAuthentication()
					.withUser("user")
					.password("password")
					.roles("USER")
					.and()
					.withUser("foo")
					.password("foo")
					.roles("ADMIN");
					
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/flight/**").hasRole("USER")
			.antMatchers("/flight").hasRole("ADMIN")
			.antMatchers("/count").permitAll()
			.and().formLogin();
	}
}
