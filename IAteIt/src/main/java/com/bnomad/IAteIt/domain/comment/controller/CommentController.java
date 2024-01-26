package com.bnomad.IAteIt.domain.comment.controller;

import com.bnomad.IAteIt.domain.comment.entity.dto.CommentCreateDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentEditDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentResponseDto;
import com.bnomad.IAteIt.domain.comment.service.CommentService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Meal Comment 조회
     */
    @GetMapping("")
    public ResponseEntity<ResultResponse> getMealComments(@RequestParam("mealId") Long mealId) {
        List<CommentResponseDto> commentResponseDtos = commentService.findAllByMealId(mealId);
        return ResponseEntity.ok(ResultResponse.of(COMMENT_FIND_BY_MEAL_SUCCESS, commentResponseDtos));
    }

    /**
     * Comment 생성
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> createComment(@RequestBody CommentCreateDto commentCreateDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(commentCreateDto);
        return ResponseEntity.ok(ResultResponse.of(COMMENT_CREATE_SUCCESS, commentResponseDto));
    }

    /**
     * Comment 수정
     */

    @PutMapping("")
    public ResponseEntity<ResultResponse> editComment(@RequestBody CommentEditDto commentEditDto) {
        CommentResponseDto commentResponseDto = commentService.editComment(commentEditDto);
        return ResponseEntity.ok(ResultResponse.of(COMMENT_EDIT_SUCCESS, commentResponseDto));
    }

    /**
     * Comment 삭제
     */
    @DeleteMapping("")
    public ResponseEntity<ResultResponse> deleteComment(@RequestParam("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ResultResponse.of(COMMENT_DELETE_SUCCESS));
    }

}
