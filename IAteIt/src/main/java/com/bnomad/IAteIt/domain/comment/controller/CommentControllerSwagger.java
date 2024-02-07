package com.bnomad.IAteIt.domain.comment.controller;

import com.bnomad.IAteIt.domain.comment.entity.dto.CommentCreateDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentEditDto;
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

@Tag(name = "Comment", description = "Meal Comment 조회 / Comment 생성, 수정, 삭제")
public interface CommentControllerSwagger {

    @Operation(summary = "Meal Comment 리스트 불러오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "해당 Meal의 Comment 리스트를 불러왔습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> getMealComments(@RequestParam("mealId") Long mealId);

    @Operation(summary = "Comment 생성하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comment를 생성했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 멤버가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> createComment(@RequestBody CommentCreateDto commentCreateDto);

    @Operation(summary = "Comment 수정하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comment 수정에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Comment가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> editComment(@RequestBody CommentEditDto commentEditDto);

    @Operation(summary = "Comment 삭제하기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Comment를 삭제했습니다.",
                            content = @Content(schema = @Schema(implementation = ResultResponse.class))),
                    @ApiResponse(responseCode = "400", description = "해당 id의 Comment가 없습니다.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    ResponseEntity<ResultResponse> deleteComment(@RequestParam("commentId") Long commentId);
}
