package com.aza.admin.user;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.aza.dao.admin.user.UserMapper;
import com.aza.domain.admin.user.User;
import com.aza.dto.admin.user.UserDto;
import com.aza.service.admin.user.UserService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserTest {

	@Autowired
	UserService userService;

	@Test
	@Transactional
	public void join() {
		Calendar cal = Calendar.getInstance();
		cal.set(1994, 02,22);
		UserDto user = new UserDto("admin08", "1234","김씨", "남", "010-1111-1111", new Date(), null);
		String regId =  userService.adminJoin(user);
		
		log.debug("::::::"+regId);
	}
	@Test
	public void getUserList() {

		List<User> userList =  userService.findUsers();
		userList.stream().forEach(System.out::println);
		userList.stream().forEach(user->System.out.println(user.getUserId()));
	}
	@Test
	public void getUser() {
		User user = userService.findUser("admin01");
		log.debug("::::::"+ user);
	}
	
}
