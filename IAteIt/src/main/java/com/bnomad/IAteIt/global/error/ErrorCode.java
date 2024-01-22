package com.bnomad.IAteIt.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    LOGIN_MEMBER_ERROR(400, "LM001", "로그인된 멤버가 없습니다");


    private final int status;
    private final String code;
    private final String message;

}
