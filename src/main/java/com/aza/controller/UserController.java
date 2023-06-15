package com.aza.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aza.domain.User;
import com.aza.service.UserService;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user")
	public List<User> test1() {
		
		return userService.findUsers();
	}
	
	@PostMapping("/user")
	public String adminJoin(@RequestBody User user) {
		
		String duplicate = userService.validateDuplicateUser(user);
		if(duplicate.equals("중복")) {
			return duplicate;
		}else {
			return userService.join(user);
		}		
	}
	

}
