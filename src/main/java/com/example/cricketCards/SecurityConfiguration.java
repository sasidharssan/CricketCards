package com.example.cricketCards;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.cricketCards.services.LoginUserDetailsService;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private LoginUserDetailsService userdetailsService;

	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Bean	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("/register").permitAll()
					.anyRequest().authenticated())
			.formLogin(formLogin ->
					formLogin.loginPage("/login")
					.permitAll()
					.successForwardUrl("/loginProcess"))
			.logout(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(userdetailsService);
		return provider;
	}
}

