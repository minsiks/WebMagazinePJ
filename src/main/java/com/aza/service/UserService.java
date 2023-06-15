package com.aza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aza.dao.UserMapper;
import com.aza.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
	
	public List<User> findUsers(){
		return userMapper.getUserList();
	}
	public String join(User user) {
		userMapper.insertAdminUser(user);
		return user.getUserId();
	}
	public User findUser(String id) {
		return userMapper.getUser(id);
	}
	public String validateDuplicateUser(User user) {
		User findUser = userMapper.getUser(user.getUserId());
		if(findUser==null) {
			return "통과";
		}else {
			return "중복";
		}
	}
}
