package com.aza.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    ID_DOES_NOT_EXIST(400,"MEMBER-ERR-400","유효한 아이디가 아닙니다."),
    INCORRECTED_PASSWORD(400,"MEMBER-ERR-400","비밀번호가 일치하지 않습니다."),
    ;

    private int status;
    private String errorCode;
    private String message;
}