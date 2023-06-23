package com.aza.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aza.dao.admin.user.UserMapper;
import com.aza.domain.admin.user.User;

import lombok.RequiredArgsConstructor;

// http://localhost:8081/login => 여기서 동작을 안한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService의 loadUserByUsername()");
		User userEntity = userMapper.getUserById(userId);
		System.out.println(userEntity);
		return new PrincipalDetails(userEntity);
	}

}
