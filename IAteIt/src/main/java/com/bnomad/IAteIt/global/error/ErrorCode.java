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
     * COMMENT
     */
    COMMENT_NOT_FOUND(400, "C001", "해당 Comment를 찾을 수 없습니다"),
    COMMENT_DELETE_NO_AUTH(400, "C002", "해당 Comment의 소유자가 아닙니다"),


    /**
     * BLOCK
     */
    BLOCKED_ALREADY(400, "B001", "이미 차단되어 있는 대상입니다"),

    /**
     * UTIL
     */
    IMAGE_NOT_INCLUDE(400, "U001", "이미지가 포함되어 있지 않습니다"),
    FILE_CONVERT_ERROR(400, "U002", "파일 전환에 실패했습니다"),
    FILE_FORMAT_ERROR(400, "U003", "파일 포멧이 맞지 않습니다")

    ;


    private final int status;
    private final String code;
    private final String message;

}
