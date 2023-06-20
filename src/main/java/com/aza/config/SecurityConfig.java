package com.aza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  @Bean
	  public PasswordEncoder getPasswordEncoder() {
		  return new BCryptPasswordEncoder();
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  http
	         .csrf().disable()
         	.headers().frameOptions().disable();
	   }
	  깃허브 테스트 
}