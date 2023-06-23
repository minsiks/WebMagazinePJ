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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.aza.config.auth.PrincipalDetails;
import com.aza.domain.admin.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
// login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작을 함

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/admin/login");
	}
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter : 로그인 시도중");
		
		// 1. username, password 받아서
		try {
//			BufferedReader br = request.getReader();
//			
//			String input = null;
//			while ((input=br.readLine())!=null) {
//				System.out.println(input);
//			}
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);
			
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPwd());
			
			System.out.println("====================================================");
			// PrincipalDetailsService의 loadUserByUsername() 함수가 실행
			Authentication authentication =
					authenticationManager.authenticate(authenticationToken);
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("로그인 완료됨" + principalDetails.getUser().getUserId()); // 로그인 완료
			
			// authentication 객체가 session영역에 저장을 행햐하고 그 방법이 return 해주면 됨
			// 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고
			// 굳이 JWT 토큰을 사용하면서 세션을 만들 이유 없음. 단지 권한 처리 때문에 session 넣어줌
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	// attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수 실행
	// JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 response
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication 실행됨 : 인증이 완료됨");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		// RSA방식 X Hash암호 방식
		String jwtToken = JWT.create()
				.withSubject("userToken") // 토큰 이름
				.withExpiresAt(new Date(System.currentTimeMillis()+(JwtProperties.EXPIRATION_TIME))) // 토큰 만료시간
				.withClaim("username", principalDetails.getUser().getUserId())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET)); // 내 서버만 아는 고유한 값
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
	}
	
}
