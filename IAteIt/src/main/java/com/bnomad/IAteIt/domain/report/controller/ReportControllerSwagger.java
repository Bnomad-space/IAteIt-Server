package com.bnomad.IAteIt.domain.report.controller;

import com.bnomad.IAteIt.domain.report.entity.dto.ReportRequestDto;
import com.bnomad.IAteIt.global.error.ErrorResponse;
import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Report", description = "Report 보내기")
public interface ReportControllerSwagger {

    @Operation(summary = "Report 보내기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Report를 보냅니다.",
                        content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Report 전송에 실패했습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Meal이 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> report(@RequestBody ReportRequestDto reportRequestDto);
}
