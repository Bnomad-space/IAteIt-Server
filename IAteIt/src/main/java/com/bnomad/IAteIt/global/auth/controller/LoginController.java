package com.bnomad.IAteIt.global.auth.controller;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.Session;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.member.repository.SessionRepository;
import com.bnomad.IAteIt.global.auth.dto.LoginResponseMember;
import com.bnomad.IAteIt.global.result.ResultResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.LOGIN_MEMBER_FIND_SUCCESS;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final HttpSession httpSession;
    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;

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
