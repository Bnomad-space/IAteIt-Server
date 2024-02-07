package com.bnomad.IAteIt.domain.feed.controller;

import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Feed", description = "최근 Feed 가져오기")
public interface FeedControllerSwagger {

    @Operation(summary = "최근 Feed 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "최근 Feed 가져오기에 성공했습니다.",
                    content = @Content(contentSchema = @Schema(implementation = ResultResponse.class)))
            })
    ResponseEntity<ResultResponse> getRecent(@RequestParam("page") int pageNum);

}
