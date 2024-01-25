package com.bnomad.IAteIt.global.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class JoinRequestDto {
    private String nickname;
    private MultipartFile profileImage;
}
