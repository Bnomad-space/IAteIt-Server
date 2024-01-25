package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.service.LoginService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/join")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    /**
     * oauth2: accessToken을 헤더에 포함해 리턴
     * accessToken을 활용해 -> 닉네임, 프로필이미지 -> 서비스회원가입
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> join(@RequestBody JoinRequestDto request) {
        loginService.joinMember(request);
        return ResponseEntity.ok(ResultResponse.of(MEMBER_JOIN_SUCCESS));
    }

}
