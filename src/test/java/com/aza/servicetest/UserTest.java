package com.aza.servicetest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.aza.dao.UserMapper;
import com.aza.domain.User;
import com.aza.dto.UserDto;
import com.aza.service.UserService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserTest {

	@Autowired
	UserService userService;

	@Test
	public void join() {
		Calendar cal = Calendar.getInstance();
		cal.set(1994, 02,22);
		UserDto user = new UserDto("admin05", "1234","김씨", "남", "010-1111-1111", new Date(), null);
		String regId =  userService.join(user);
		
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
