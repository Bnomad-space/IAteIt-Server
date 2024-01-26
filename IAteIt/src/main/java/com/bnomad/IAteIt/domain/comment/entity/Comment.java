package com.bnomad.IAteIt.domain.comment.entity;

import com.bnomad.IAteIt.domain.comment.entity.dto.CommentCreateDto;
import com.bnomad.IAteIt.domain.comment.entity.dto.CommentEditDto;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    private String content;

    public Comment() {
    }

    public Comment(Member member, Meal meal, CommentCreateDto commentCreateDto) {
        this.fromMember = member;
        this.meal = meal;
        this.content = commentCreateDto.getContent();
    }

    public void edit(CommentEditDto commentEditDto) {
        this.content = commentEditDto.getContent();

    }
}
