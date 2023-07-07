package com.aza.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.aza.config.auth.PrincipalDetails;
import com.aza.dao.admin.user.UserMapper;
import com.aza.domain.admin.user.User;


// 시큐리티가 filter가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있음
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	private UserMapper userMapper;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
		super(authenticationManager);
		this.userMapper = userMapper;
	}

	
	// 권한이나 인증이 필요한 특정 주소를 요청했을때 위 필터를 무조건 타게 되어 있음
	// 만약 권한, 인증이 필요한 주소가 아니라면 이 필터를 타지 않음
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("인증이나 권한이 필요한 주소 요청이 됨");
		String requestURI = request.getRequestURI();
		String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
		String refreshHeader = request.getHeader("refreshToken");
		
		System.out.println("jwtHeader : " + jwtHeader);
		System.out.println("refreshToken : " + refreshHeader);
		if (requestURI.startsWith("/api/auth/")) {
	        chain.doFilter(request, response);
	        return;
	    }
		// header가 있는지 확인
		if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		String refreshToken = request.getHeader("refreshToken").replace(JwtProperties.TOKEN_PREFIX, "");
		
		String userId =JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();
		User userEntity = userMapper.getUserById(userId);
			
			
		// 서명이 정상적으로 됨
		PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
		
		response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		response.setHeader("Refreshtoken", JwtProperties.TOKEN_PREFIX+refreshToken);
		// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만듬
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
		
		// 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
	}
}
