package com.aza.controller.admin.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aza.domain.admin.user.User;
import com.aza.dto.admin.user.UserDto;
import com.aza.exception.ApiExceptionHandler;
import com.aza.service.admin.user.UserService;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user")
	public List<User> test1() {
		
		return userService.findUsers();
	}
	
	@PostMapping("/signUp")
	public String adminJoin(@Valid @RequestBody UserDto userDto) {
		
		String duplicate = userService.validateDuplicateAdminUser(userDto);
		if(duplicate.equals("중복")) {
			return duplicate;
		}else {
			return userService.adminJoin(userDto);
		}		
	}
	@PostMapping("/signIn")
	public Object adminLogin(@RequestBody UserDto userDto) {
		UserDto dto = userService.adminLogin(userDto);
		log.debug("dto ::=>{}"+dto);
		return dto;
	}
	

}
