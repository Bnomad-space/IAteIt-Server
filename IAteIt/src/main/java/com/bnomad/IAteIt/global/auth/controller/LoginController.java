package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.Session;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.member.repository.SessionRepository;
import com.bnomad.IAteIt.global.auth.dto.AccessTokenResponse;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.dto.LoginResponseMember;
import com.bnomad.IAteIt.global.auth.service.LoginService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.LOGIN_MEMBER_FIND_SUCCESS;
import static com.bnomad.IAteIt.global.result.ResultCode.MEMBER_FIND_SUCCESS;

@RestController
@RequestMapping("/api/public/login")
@RequiredArgsConstructor
public class LoginController {

    private final SessionRepository sessionRepository;
    private final LoginService loginService;
    private final MemberRepository memberRepository;


    // TODO: return 해줘야 하는 것이 accessToken과 refreshToken이다.
    @PostMapping("")
    public ResponseEntity<ResultResponse> join(@RequestBody JoinRequestDto request) {
        String accessToken = loginService.joinMember(request);
        return ResponseEntity.ok(
                ResultResponse.of(
                        MEMBER_FIND_SUCCESS,
                        AccessTokenResponse.builder()
                                .accessToken(accessToken)
                                .build()
                )
        );
    }

    @GetMapping("")
    public ResponseEntity<ResultResponse> loginSuccessPage(@RequestParam("sessionId") Long sessionId) {
        Session findSession = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("세션이 DB에 없습니다"));
        System.out.println("findSession = " + findSession.getMember().getNickname());
        if (findSession != null) {
            Member byId = memberRepository.findById(findSession.getMember().getId())
                    .orElseThrow();
            LoginResponseMember res = new LoginResponseMember(byId);
            return ResponseEntity.ok(ResultResponse.of(LOGIN_MEMBER_FIND_SUCCESS, res));
        } else {
            System.out.println("find Session NO!!!!!!!!");
            return null;
        }
    }

}
