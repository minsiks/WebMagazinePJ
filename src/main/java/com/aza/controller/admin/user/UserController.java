package com.aza.controller.admin.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aza.domain.admin.user.User;
import com.aza.dto.admin.user.UserDto;
import com.aza.service.admin.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user")
	public List<User> test1() {
		
		return userService.findUsers();
	}
	@GetMapping("/findUserByPNo")
	public User findUserByPNo(@RequestParam("userPhoneNo") String userPhoneNo) {
		
		return userService.findUserByPNo(userPhoneNo);
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
	// Spring Security에서 처리하기 때문에 직접 컨트롤러에서 처리 X 
	// 로그인 관련 처리는 /config/jwt에 구성
//	@PostMapping("/signIn")
//	public ResponseEntity<UserDto> adminLogin(@RequestBody UserDto userDto) {
//		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		UserDto dto = userService.adminLogin(userDto);
//		log.debug("dto ::=>{}"+dto);
//		return ResponseEntity.ok(dto);
//	}
	

}
