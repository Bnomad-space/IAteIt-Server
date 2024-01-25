package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.service.LoginService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/join")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    /**
     * oauth2: accessToken을 헤더에 포함해 리턴
     * accessToken을 활용해 -> 닉네임, 프로필이미지 -> 서비스회원가입
     *
     * http body에 nickname, profileImage key 값으로 담아서 던져줌
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> join(@ModelAttribute JoinRequestDto joinRequestDto) {
        MemberProfileDto memberProfileDto = loginService.joinMember(joinRequestDto);
        return ResponseEntity.ok(ResultResponse.of(MEMBER_JOIN_SUCCESS, memberProfileDto));
    }

}
