package com.aza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.aza.config.jwt.JwtAuthenticationFilter;
import com.aza.config.jwt.JwtAuthorizationFilter;
import com.aza.dao.admin.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { // extends WebSecurityConfigurerAdapter 22년 2월 공식문서에 더 이상 사용되지 않는다고 SecurityFilterChain로 대체

	private final CorsConfig corsConfig;
	private final UserMapper userMapper;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		  return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X
				.and()
				.formLogin().disable() // form태그 로그인 안함
				.httpBasic().disable() // 기존 인증 방식 사용 x -> bearer 인증 사용
				.apply(new MyCustomDsl()) // 커스텀 필터 등록
				.and()
				.authorizeRequests()
				.antMatchers("/api/user/**")
				.access("hasRole('1') or hasRole('2')")
				.antMatchers("/api/admin/**")
				.access("hasRole('2')")
				.anyRequest().permitAll()
				.and().build();
	}
	
	public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			http
					.addFilter(corsConfig.corsFilter())
					.addFilter(new JwtAuthenticationFilter(authenticationManager))
					.addFilter(new JwtAuthorizationFilter(authenticationManager, userMapper));
		}
	}
	 
	  
}