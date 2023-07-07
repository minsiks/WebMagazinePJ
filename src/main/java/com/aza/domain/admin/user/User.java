package com.aza.domain.admin.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String userGender;
	private String userPhoneNo;
	private Date userBirth;
	private Date userRegdate;
	private String userEmail;
	private int userType;
	private boolean userUse;
	private Date userDeldate;
	private String userRefreshToken;
	
	// 로그인 시도 횟수 제한
	private boolean blocked;
	private int failCount;
	
	
}
