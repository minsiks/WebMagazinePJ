package com.aza.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    LOGIN_FAIL_EXCEPTION(400,"MEMBER-ERR-400","로그인이 실패하였습니다."),
    ;

    private int status;
    private String errorCode;
    private String message;
}