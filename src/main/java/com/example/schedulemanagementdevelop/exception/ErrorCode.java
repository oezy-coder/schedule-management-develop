package com.example.schedulemanagementdevelop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "유효한 유저명을 입력해주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "유효한 비밀번호를 입력해주세요."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "비밀번호가 일치하지 않습니다."),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "CONFLICT", "이미 가입된 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "존재하지 않는 사용자입니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "존재하지 않는 일정입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
