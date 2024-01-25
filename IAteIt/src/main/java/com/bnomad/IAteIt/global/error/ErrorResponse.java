package com.bnomad.IAteIt.global.error;

import lombok.Getter;

@Getter
public class ErrorResponse extends Throwable {

    private int status;
    private String code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

}
