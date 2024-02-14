package com.bnomad.IAteIt.domain.meal.controller;

import com.bnomad.IAteIt.domain.meal.entity.dto.MealCreateDto;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealEditDto;
import com.bnomad.IAteIt.global.error.ErrorResponse;
import com.bnomad.IAteIt.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Meal", description = "Meal 생성 / 수정 / 삭제")
public interface MealControllerSwagger {

    @Operation(summary = "Meal 생성하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Meal 생성에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Meal 이미지가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 멤버의 id가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    ResponseEntity<ResultResponse> createMeal(
            @RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(implementation = MealCreateDto.class))
            )
            @ModelAttribute MealCreateDto mealCreateDto);

    @Operation(summary = "Meal 수정하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Meal 수정에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Meal이 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> editMeal(@RequestBody MealEditDto mealEditDto);

    @Operation(summary = "Meal 삭제하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Meal 삭제에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Meal이 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> deleteMeal(@RequestParam("mealId") Long mealId);
}
