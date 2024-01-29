package com.bnomad.IAteIt.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * Login
     */

    LOGIN_MEMBER_FIND_SUCCESS(200, "LM001", "로그인된 멤버 조회에 성공했습니다"),

    /**
     * Member
     */
    MEMBER_FIND_SUCCESS(200, "MEM001", "멤버 조회에 성공했습니다"),
    MEMBER_EDIT_SUCCESS(200, "MEM002", "멤버 정보 변경에 성공했습니다"),
    MEMBER_JOIN_SUCCESS(200, "MEM003", "멤버 커스텀 회원가입에 성공했습니다"),
    MEMBER_DELETE_SUCCESS(200, "MEM004", "멤버 탈퇴에 성공했습니다"),

    /**
     * Feed
     */
    FEED_REQUEST_SUCCESS(200, "F001", "피드 조회에 성공했습니다"),
    FEED_DONE_SUCCESS(200, "F002", "마지막 페이지입니다."),


    /**
     * Meal
     */

    MEAL_CREATE_SUCCESS(200, "ML001", "Meal 생성에 성공했습니다"),
    MEAL_EDIT_SUCCESS(200, "ML002", "Meal 수정에 성공했습니다"),
    MEAL_DELETE_SUCCESS(200, "ML003", "Meal 삭제에 성공했습니다"),

    /**
     * Plate
     */

    PLATE_ADD_SUCCESS(200, "P001", "Plate 추가에 성공했습니다"),
    PLATE_DELETE_SUCCESS(200, "P002", "Plate 삭제에 성공했습니다"),

    /**
     * Comment
     */
    COMMENT_FIND_BY_MEAL_SUCCESS(200, "C001", "Comment 조회에 성공했습니다"),
    COMMENT_CREATE_SUCCESS(200, "C002", "Comment 생성에 성공했습니다"),
    COMMENT_EDIT_SUCCESS(200, "C003", "Comment 변경에 성공했습니다"),
    COMMENT_DELETE_SUCCESS(200, "C004", "Comment 삭제에 성공했습니다"),

    /**
     * Block
     */

    BLOCK_MEMBER_SUCCESS(200, "B001", "멤버 차단에 성공했습니다"),
    BLOCKED_MEMBER_FIND_SUCCESS(200, "B002", "차단한 멤버 조회에 성공했습니다"),
    UNBLOCK_MEMBER_SUCCESS(200, "B003", "멤버 차단 해제에 성공했습니다"),

    /**
     * Report
     */

    REPORT_CREATE_SUCCESS(200, "R001", "Report 생성에 성공했습니다")
    ;


    private final int status;
    private final String code;
    private final String message;
}
