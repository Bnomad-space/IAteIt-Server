package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

@Tag(name = "Member", description = "Member 관련 API - Member 정보 불러오기/ 정보 변경 / 삭제")
public interface MemberControllerSwagger {

    @Operation(summary = "Member 정보 불러오기")
    @ApiResponse()
    ResponseEntity<ResultResponse> memberProfile();

    @Operation(summary = "Member 정보 변경하기")
    @ApiResponse()
    ResponseEntity<ResultResponse> memberNicknameEdit(@ModelAttribute MemberEditRequest memberEditRequest);

}
