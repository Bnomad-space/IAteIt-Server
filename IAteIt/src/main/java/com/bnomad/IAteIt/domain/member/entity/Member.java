package com.bnomad.IAteIt.domain.member.entity;

import com.bnomad.IAteIt.domain.Meal;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Meal> meals = new ArrayList<>();

    private String nickname;

    public Member() {

    }

    private String profileImage;


    public void nicknameChange(String nickname) {
        this.nickname = nickname;
    }


}
