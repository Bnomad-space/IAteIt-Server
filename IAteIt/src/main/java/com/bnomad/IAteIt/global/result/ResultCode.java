package com.bnomad.IAteIt.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * Member
     */
    MEMBER_FIND_SUCCESS(200, "MEM001", "멤버 조회에 성공했습니다"),
    MEMBER_EDIT_SUCCESS(200, "MEM002", "멤버 정보 변경에 성공했습니다"),

    /**
     * Login
     */

    LOGIN_MEMBER_FIND_SUCCESS(200, "LM001", "로그인된 멤버 조회에 성공했습니다"),

    /**
     * Meal
     */

    MEAL_CREATE_SUCCESS(200, "ML001", "Meal 생성에 성공했습니다."),

    /**
     * Block
     */

    BLOCK_MEMBER_SUCCESS(200, "B001", "멤버 차단에 성공했습니다"),
    BLOCKED_MEMBER_FIND_SUCCESS(200, "B002", "차단한 멤버 조회에 성공했습니다.")
    ;


    private final int status;
    private final String code;
    private final String message;
}
