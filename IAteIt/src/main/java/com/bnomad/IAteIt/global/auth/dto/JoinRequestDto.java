package com.bnomad.IAteIt.global.auth.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class JoinRequestDto {

    private Long id;
    private String nickname;
    private MultipartFile image;
}
