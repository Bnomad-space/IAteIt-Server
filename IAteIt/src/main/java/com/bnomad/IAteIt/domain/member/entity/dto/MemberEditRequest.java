package com.bnomad.IAteIt.domain.member.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberEditRequest {

    private Long memberId;
    private String nickname;
    private String profileImage;
}
