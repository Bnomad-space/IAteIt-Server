package com.bnomad.IAteIt.domain.comment.entity.dto;

import com.bnomad.IAteIt.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long memberId;
    private String profileImageUrl;
    private String content;

    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public CommentResponseDto() {
    }

    public CommentResponseDto(Comment comment) {
        this.memberId = comment.getFromMember().getId();
        this.profileImageUrl = comment.getFromMember().getProfileImage();
        this.content = comment.getContent();
        this.createdDateTime = comment.getCreatedDateTime();
        this.modifiedDateTime = comment.getModifiedDateTime();
    }
}
