package com.bnomad.IAteIt.global.error;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);

    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ErrorCode errorCode) {
        super(ErrorResponse.of(errorCode));
        // super에 errorCode를 날려버리니까 안에서 message 잡아서 메시지 프린트함
    }

}
