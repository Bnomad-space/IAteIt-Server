package com.bnomad.IAteIt.domain;

import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
public class Meal {

    @Id
    @GeneratedValue
    @Column(name = "meal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "meal")
    private List<Plate> plates = new ArrayList<>();

    @OneToMany(mappedBy = "meal")
    private List<Comment> comments = new ArrayList<>();


    private String caption;

    private String location;


}
