package com.example.schedulemanagementdevelop.exception;

import com.example.schedulemanagementdevelop.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 발생 시 ErrorCode 기반으로 응답 생성
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> processCustomException(CustomException e) {

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getHttpStatus().value(), // 상태 코드
                errorCode.getCode(),               // 에러 코드
                errorCode.getMessage()             // 에러 메시지
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }
}
