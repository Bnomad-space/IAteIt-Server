package com.bnomad.IAteIt.global.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> memberNotFound(BusinessException e) {
        String localizedMessage = e.getLocalizedMessage();
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.of(errorCode), HttpStatusCode.valueOf(errorCode.getStatus()));
    }

}
