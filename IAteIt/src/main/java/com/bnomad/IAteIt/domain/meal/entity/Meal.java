package com.bnomad.IAteIt.domain.meal.entity;

import com.bnomad.IAteIt.domain.meal.entity.dto.MealCreateDto;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealEditDto;
import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Meal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Meal(MealCreateDto mealCreateDto, Member member) {
        this.caption = mealCreateDto.getCaption();
        this.location = mealCreateDto.getLocation();
        this.member = member;
    }

    public void edit(MealEditDto mealEditDto) {
        this.caption = mealEditDto.getCaption();
        this.location = mealEditDto.getLocation();
    }


}
