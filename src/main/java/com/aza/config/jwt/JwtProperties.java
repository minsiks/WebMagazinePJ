package com.aza.config.jwt;

/**
 * JWT 관련된 설정을 상수로 사용
 * 
 * @author 김민식
 * 
 */
public interface JwtProperties {

	String SECRET = "wMGZ42"; // 우리 서버만 알고 있는 비밀 값
	int EXPIRATION_TIME = 60000*30; // 30분
	int REFRESH_EXPIRATION_TIME = 604800000;   //7일
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
