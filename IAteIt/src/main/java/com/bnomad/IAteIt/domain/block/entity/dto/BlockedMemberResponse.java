package com.bnomad.IAteIt.domain.block.entity.dto;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlockedMemberResponse {

    private String nickname;
    private String profileImageURL;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public BlockedMemberResponse() {
    }

    public BlockedMemberResponse(Block block) {
        this.nickname = block.getBlockedMember().getNickname();
        this.profileImageURL = block.getBlockedMember().getProfileImage();
        this.createdDateTime = block.getCreatedDateTime();
        this.modifiedDateTime = block.getModifiedDateTime();
    }
}
