package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.service.LoginService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/public/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    // TODO: return 해줘야 하는 것이 accessToken과 refreshToken이다.
    @PostMapping("")
    public ResponseEntity<ResultResponse> join(@RequestBody JoinRequestDto request) {
        loginService.joinMember(request);
        return ResponseEntity.ok(
                ResultResponse.of(
                        MEMBER_JOIN_SUCCESS
                )
        );
    }

}
