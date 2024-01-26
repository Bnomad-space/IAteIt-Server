package com.bnomad.IAteIt.domain.comment.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {
    private Long mealId;
    private String content;
}
