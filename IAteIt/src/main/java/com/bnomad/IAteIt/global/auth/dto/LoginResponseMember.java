package com.bnomad.IAteIt.global.auth.dto;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseMember {

    private Long id;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private List<Block> blockList;
    private List<Meal> meals;

    public LoginResponseMember() {
    }

    public LoginResponseMember(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.profileImageUrl = member.getProfileImage();
        this.blockList = member.getBlockList();
        this.meals = member.getMeals();

    }
}
