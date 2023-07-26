package com.aza.dao.admin.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aza.domain.admin.user.User;

@Mapper
@Repository
public interface UserMapper {

	public List<User> getUserList();
	public void insertAdminUser(User user);
	public User getAdminUserById(String id);
	public User getUserById(String id);
	public void updateRefreshToken(@Param("userId")String userId, @Param("userRefreshToken")String userRefreshToken);
	public User getUserByRefreshToken(String userRefreshToken);
	public User getUserByPNo(String pNo);
}
