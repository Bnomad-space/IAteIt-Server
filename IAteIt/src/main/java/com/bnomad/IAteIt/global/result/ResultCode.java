package com.bnomad.IAteIt.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    MEMBER_FIND_SUCCESS(200, "M001", "멤버 조회에 성공했습니다");


    private final int status;
    private final String code;
    private final String message;
}
