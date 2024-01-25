package com.bnomad.IAteIt.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * MEMBER
     */
    MEMBER_NOT_FOUND(400, "MEM001", "해당 멤버를 조회할 수 없습니다");



    private final int status;
    private final String code;
    private final String message;

}
