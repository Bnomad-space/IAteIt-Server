package com.bnomad.IAteIt.domain.plate.controller;

import com.bnomad.IAteIt.domain.plate.entity.dto.PlateAddDto;
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
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "Plate", description = "Plate 추가 / 삭제")
public interface PlateControllerSwagger {

    @Operation(summary = "Plate 추가하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Plate 추가에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 Plate가 추가될 Meal의 id가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> addPlate(
            @RequestBody(
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(implementation = PlateAddDto.class))
            )
            @ModelAttribute PlateAddDto plateAddDto);

    @Operation(summary = "Plate 삭제하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Plate 삭제에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Plate가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Plate id에 해당하는 Meal이 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> deletePlate(@RequestParam("plateId") Long plateId);
}
