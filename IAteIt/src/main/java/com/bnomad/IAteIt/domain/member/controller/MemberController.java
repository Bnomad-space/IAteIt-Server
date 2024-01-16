package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.service.MemberService;
import com.bnomad.IAteIt.global.result.ResultCode;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/join")
    public ResponseEntity<HashMap<String, String>> join() {
        HashMap<String, String> accessAndrefreshToken = new HashMap<String, String>();
        accessAndrefreshToken = memberService.join();
        return ResponseEntity.ok(accessAndrefreshToken);
    }

    @GetMapping("/profile")
    public ResponseEntity<ResultResponse> memberProfile() {
        Member byNickname = memberService.findByNickname();
        return ResponseEntity.ok(ResultResponse.of(MEMBER_FIND_SUCCESS, byNickname));
    }



}
