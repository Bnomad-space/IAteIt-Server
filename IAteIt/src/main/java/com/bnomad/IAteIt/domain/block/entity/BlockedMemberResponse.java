package com.bnomad.IAteIt.domain.block.entity;

import com.bnomad.IAteIt.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class BlockedMemberResponse {

    private String nickname;
    private String profileImageURL;

    public BlockedMemberResponse() {
    }

    public BlockedMemberResponse(Member member) {
        this.nickname = member.getNickname();
        this.profileImageURL = member.getProfileImage();
    }
}
