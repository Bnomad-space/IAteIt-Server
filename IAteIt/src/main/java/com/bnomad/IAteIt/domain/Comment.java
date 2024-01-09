package com.bnomad.IAteIt.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;


    private Long fromUser;
    private Long toUser;

    private String content;

    private LocalDateTime uploadDateTime;
    private LocalDateTime modifiedDateTime;

}
