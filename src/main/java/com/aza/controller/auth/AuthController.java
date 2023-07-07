package com.aza.controller.auth;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.aza.config.auth.PrincipalDetails;
import com.aza.config.jwt.JwtProperties;
import com.aza.dao.admin.user.UserMapper;
import com.aza.domain.admin.user.User;
import com.aza.service.admin.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
	
	private final UserMapper userMapper;
	
	@PostMapping("/refresh")
	public String refresh(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = request.getHeader("refreshToken").replace(JwtProperties.TOKEN_PREFIX, "");
		
		try {
			JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(refreshToken); // 리프레시토큰 유효확인
		} catch (Exception TokenExpiredException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "The Token has expired";
		}
		User userEntity = userMapper.getUserByRefreshToken(refreshToken);
		PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
		
		String newJwtToken = JWT.create()
				.withSubject("userToken") // 토큰 이름
				.withExpiresAt(new Date(System.currentTimeMillis()+(JwtProperties.EXPIRATION_TIME))) // 토큰 만료시간
				.withClaim("username", principalDetails.getUser().getUserId())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET)); // 내 서버만 아는 고유한 값

		response.setHeader("Refreshtoken", JwtProperties.TOKEN_PREFIX+refreshToken);
		response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+newJwtToken);
		
		return "sucess";
	}
	
	

}
