package com.aza.service.admin.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.aza.dao.admin.user.UserMapper;
import com.aza.domain.admin.user.User;
import com.aza.dto.admin.user.UserDto;
import com.aza.exception.ApiExceptionHandler;
import com.aza.exception.ErrorCode;
import com.aza.exception.LoginFailException;
import org.springframework.security.crypto.password.PasswordEncoder;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
	
	public List<User> findUsers(){
		return userMapper.getUserList();
	}
	public String adminJoin(UserDto userDto) {
	    User user = userDto.toDomain(passwordEncoder);
	    // 비밀번호 암호화
	    userMapper.insertAdminUser(user);
	    return user.getUserId();
	}
	public User findUser(String id) {
		return userMapper.getAdminUserById(id);
	}
	/**
	 * 중복 관리자 회원 조회
	 * @param userDto
	 * @return
	 */
	public String validateDuplicateAdminUser(UserDto userDto) {
		User findUser = userMapper.getAdminUserById(userDto.getUserId());
		if(findUser==null) {
			return "통과";
		}else {
			return "중복";
		}
	}
	public UserDto adminLogin(UserDto userDto) {
		User user = new User();
		UserDto dto = new UserDto();
		user = userMapper.getAdminUserById(userDto.getUserId());
		if(user==null) {
			throw new LoginFailException("Id does not exist", ErrorCode.ID_DOES_NOT_EXIST);
		}
		else if(!passwordEncoder.matches(userDto.getUserPwd(), user.getUserPwd())) { // 암호화된 비밀번호 일치 조회
			throw new LoginFailException("Incorrected password", ErrorCode.INCORRECTED_PASSWORD);
		}
		dto = UserDto.create(user);
		
		
		return dto;
	}
}
