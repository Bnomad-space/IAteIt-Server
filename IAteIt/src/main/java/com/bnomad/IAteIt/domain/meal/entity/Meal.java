package com.bnomad.IAteIt.domain.meal.entity;

import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
public class Meal extends BaseEntity {

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

    /**
     *
     */

    public void captionChange(String newCaption) {
        this.caption = newCaption;
    }

    public void locationChange(String newLocation) {
        this.location = newLocation;
    }


}
