package com.bnomad.IAteIt.domain.member.entity.dto;

import com.bnomad.IAteIt.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileDto {

    private Long id;
    private String nickname;
    private String profileImageUrl;

    public MemberProfileDto() {
    }

    public MemberProfileDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImage();
    }
}
