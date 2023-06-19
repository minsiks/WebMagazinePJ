package com.aza.exception;

import lombok.Getter;

@Getter
public class LoginFailException extends RuntimeException{

	private ErrorCode errorCode;
	
	public LoginFailException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
