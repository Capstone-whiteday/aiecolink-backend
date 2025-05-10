package com.whiteday.aiecolink.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode.getStatus().value(), errorCode.getMessage()));
    }

    // 일반 예외 처리 (Optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse(500, "서버 내부 오류가 발생했습니다."));
    }
}
