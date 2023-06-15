package com.aza.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.aza.domain.User;

@Mapper
@Repository
public interface UserMapper {

	public List<User> getUserList();
	public void insertAdminUser(User user);
	public User getUser(String id);
}
