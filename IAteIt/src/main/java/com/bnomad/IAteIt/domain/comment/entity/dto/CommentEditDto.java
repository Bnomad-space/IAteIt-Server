package com.bnomad.IAteIt.domain.comment.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentEditDto {
    private Long commentId;
    private String content;
}
