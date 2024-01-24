package com.bnomad.IAteIt.domain.member.entity.dto;

import com.bnomad.IAteIt.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileDto {

    private String nickname;
    private String profileImageUrl;

    public MemberProfileDto() {
    }

    public MemberProfileDto(Member member) {
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImage();
    }
}
