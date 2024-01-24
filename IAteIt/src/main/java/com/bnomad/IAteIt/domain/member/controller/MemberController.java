package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.domain.member.service.MemberService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 멤버 정보 가져오기
     */
    @GetMapping("")
    public ResponseEntity<ResultResponse> memberProfile() {
        MemberProfileDto findMember = memberService.findById();
        return ResponseEntity.ok(ResultResponse.of(MEMBER_FIND_SUCCESS, findMember));
    }

    @PostMapping("")
    public ResponseEntity<HashMap<String, String>> join() {
        HashMap<String, String> accessAndrefreshToken = new HashMap<String, String>();
        accessAndrefreshToken = memberService.join();
        return ResponseEntity.ok(accessAndrefreshToken);
    }

    /**
     * 멤버 닉네임 수정
     */
    @PutMapping("/edit")
    public ResponseEntity<ResultResponse> memberNicknameModify(@RequestBody MemberEditRequest memberEditRequest) {
        memberService.editProfile(memberEditRequest);
        return ResponseEntity.ok(ResultResponse.of(MEMBER_EDIT_SUCCESS));
    }

}
