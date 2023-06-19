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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
	
	public List<User> findUsers(){
		return userMapper.getUserList();
	}
	public String adminJoin(UserDto userDto) {
		User user = userDto.toDomain();
		userMapper.insertAdminUser(user);
		return user.getUserId();
	}
	public User findUser(String id) {
		return userMapper.getAdminUser(id);
	}
	public String validateDuplicateAdminUser(UserDto userDto) {
		User findUser = userMapper.getAdminUser(userDto.getUserId());
		
		if(findUser==null) {
			return "통과";
		}else {
			return "중복";
		}
	}
	public UserDto adminLogin(UserDto userDto) {
		User user = new User();
		UserDto dto = new UserDto();
		user = userMapper.getUserByIdAndPwd(userDto.getUserId(),userDto.getUserPwd());
		if(user == null) {
			throw new LoginFailException("login fail", ErrorCode.LOGIN_FAIL_EXCEPTION);
		}
		dto = UserDto.create(user);
		
		
		return dto;
	}
}
