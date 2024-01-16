package com.bnomad.IAteIt.domain.member.entity;

import com.bnomad.IAteIt.domain.Meal;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Meal> meals = new ArrayList<>();

    private String nickname;
    private String profileImage;




}
