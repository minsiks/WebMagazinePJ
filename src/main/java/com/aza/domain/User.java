package com.aza.domain;

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
	
	
	
}
