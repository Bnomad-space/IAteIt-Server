package com.bnomad.IAteIt.domain.comment.service;

import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentCreateDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentEditDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentResponseDto;
import com.bnomad.IAteIt.domain.comment.repository.CommentRepository;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.error.custom.EntityNotFoundException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final JwtUtil jwtUtil;

    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponseDto> findAllByMealId(Long mealId) {
        List<Comment> comments = commentRepository.findAllByMealId(mealId);
        List<CommentResponseDto> responses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto response = new CommentResponseDto(comment);
            responses.add(response);
        }
        return responses;
    }


    public CommentResponseDto createComment(CommentCreateDto commentCreateDto) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        Meal meal = mealRepository.findById(commentCreateDto.getMealId())
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        Comment comment = new Comment(member, meal, commentCreateDto);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    public CommentResponseDto editComment(CommentEditDto commentEditDto) {
        Long commentId = commentEditDto.getCommentId();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));

        comment.edit(commentEditDto);
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND));
        Long currentMemberId = jwtUtil.currentMemberId();
        if (comment.getFromMember().getId() == currentMemberId) {
            commentRepository.delete(comment);
        } else {
            throw new BusinessException(COMMENT_DELETE_NO_AUTH);
        }
    }
}
