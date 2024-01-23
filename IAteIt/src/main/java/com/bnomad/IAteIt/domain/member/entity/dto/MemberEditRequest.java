package com.bnomad.IAteIt.domain.member.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEditRequest {

    private Long memberId;
    private String nickname;
    private String profileImage;
}
