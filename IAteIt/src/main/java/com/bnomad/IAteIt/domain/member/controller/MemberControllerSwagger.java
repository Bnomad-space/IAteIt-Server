package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.global.error.ErrorResponse;
import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

@Tag(name = "Member", description = "Member 관련 API - Member 정보 불러오기 / 정보 변경 / 삭제")
public interface MemberControllerSwagger {

    @Operation(summary = "Member 정보 불러오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "멤버 정보를 불러왔습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 멤버가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> memberProfile();

    @Operation(summary = "Member 정보 변경하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "멤버 정보 변경에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 멤버가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> memberNicknameEdit(
            @RequestBody(
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(implementation = MemberEditRequest.class))
            ) @ModelAttribute MemberEditRequest memberEditRequest);

    @Operation(summary = "Member 삭제")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "멤버를 삭제합니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 멤버가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> deleteMember();

}
