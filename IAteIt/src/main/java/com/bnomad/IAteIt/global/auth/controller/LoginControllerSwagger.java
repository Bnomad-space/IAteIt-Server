package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
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

@Tag(name = "Login", description = "최초 로그인 / OAuth2 로그인")
public interface LoginControllerSwagger {

    @Operation(summary = "최초 로그인 / OAuth2 로그인",
    description = "최초 로그인 혹은 재로그인으로 토큰 발급시에만 사용해주세요.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Member 로그인에 성공했습니다.",
                        content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "중복된 nickname입니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

            }
    )
    ResponseEntity<ResultResponse> join(@ModelAttribute JoinRequestDto joinRequestDto);

}
