package com.aza.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aza.domain.admin.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 계정의 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Integer.toString(user.getUserType())));
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return user.getUserPwd();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	// 계정의 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정의 잠김 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정의 잠김 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정의 활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
