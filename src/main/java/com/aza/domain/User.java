package com.aza.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
	public User(String userId, String userPwd,String userName, String userGender, String userPhoneNo, Date userBirth, Date userRegdate,
			String userEmail, int userType) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userGender = userGender;
		this.userPhoneNo = userPhoneNo;
		this.userBirth = userBirth;
		this.userRegdate = userRegdate;
		this.userEmail = userEmail;
		this.userType = userType;
	}
	
	
}
