package com.bnomad.IAteIt.domain.member.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MemberEditRequest {

    private String nickname;
    private MultipartFile profileImage;

}
