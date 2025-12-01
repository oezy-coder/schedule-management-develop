package com.example.schedulemanagementdevelop.exception;

import com.example.schedulemanagementdevelop.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    // Validation 실패 시 첫 번째 오류 메시지를 추출하여 응답 생성
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage(); // DTO @Valid 메시지

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), // 400 상태 코드
                "VALIDATION_ERROR",             // 공통 Validation 코드
                message                         // 검증 실패 메시지
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
