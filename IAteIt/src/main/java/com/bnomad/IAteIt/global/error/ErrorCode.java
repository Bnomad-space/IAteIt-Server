package com.bnomad.IAteIt.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * MEMBER
     */
    MEMBER_NOT_FOUND(400, "MEM001", "해당 멤버를 조회할 수 없습니다"),
    MEMBER_NICKNAME_DUPLICATED(400, "MEM002", "멤버 닉네임이 동일합니다"),

    /**
     * MEAL
     */
    MEAL_NOT_FOUND(400, "MEAL001", "해당 Meal을 조회할 수 없습니다"),

    /**
     * PLATE
     */
    PLATE_NOT_FOUND(400, "P001", "해당 Plate를 조회할 수 없습니다"),

    /**
     * BLOCK
     */
    BLOCKED_ALREADY(400, "B001", "이미 차단되어 있는 대상입니다")

    ;


    private final int status;
    private final String code;
    private final String message;

}
