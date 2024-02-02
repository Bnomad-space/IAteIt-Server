package com.bnomad.IAteIt.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> business() {
        return ResponseEntity.ok(ErrorResponse.of(ErrorCode.MEMBER_NOT_FOUND));
    }

}
