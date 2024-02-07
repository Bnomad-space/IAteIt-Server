package com.bnomad.IAteIt.domain.block.controller;

import com.bnomad.IAteIt.domain.block.entity.dto.BlockingMemberRequest;
import com.bnomad.IAteIt.global.error.ErrorResponse;
import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Block", description = "Block 관련 차단 목록 확인 / 차단 / 차단 해제")
public interface BlockControllerSwagger {

    @Operation(summary = "Block한 멤버 리스트 불러오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Block한 멤버 리스트를 불러왔습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> blockedMemberList();

    @Operation(summary = "Member Block하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "해당 id의 멤버를 Block 했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 멤버가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> blockMember(@RequestBody BlockingMemberRequest blockingMemberRequest);


    @Operation(summary = "Member Block 해제하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "멤버를 Block 해제했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> unblock(@RequestParam("blockedMemberId") Long blockedMemberId);

}
