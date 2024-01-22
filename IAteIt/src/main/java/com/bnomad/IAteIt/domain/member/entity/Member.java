package com.bnomad.IAteIt.domain.member.entity;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    public Member() {
    }

    @OneToMany(mappedBy = "member")
    private List<Meal> meals = new ArrayList<>();

    private String profileImage;
    private String nickname;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @OneToMany(mappedBy = "blockingMember")
    private List<Block> blockList = new ArrayList<>();

    public void editProfile(MemberEditRequest request) {
        this.nickname = request.getNickname();
        this.profileImage = request.getProfileImage();
    }

    public void block_Member(Member blockedMember) {
        Block block = Block.builder()
                .blockingMember(this)
                .blockedMember(blockedMember)
                .build();
        this.blockList.add(block);
    }


}
