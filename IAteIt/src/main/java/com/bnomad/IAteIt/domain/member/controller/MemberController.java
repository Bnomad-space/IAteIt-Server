package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.domain.member.service.MemberService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        MemberProfileDto findMember = memberService.getMemberProfile();
        return ResponseEntity.ok(ResultResponse.of(MEMBER_FIND_SUCCESS, findMember));
    }

    /**
     * 멤버 닉네임 수정
     */
    @PutMapping("")
    public ResponseEntity<ResultResponse> memberNicknameEdit(@ModelAttribute MemberEditRequest memberEditRequest) {
        memberService.editProfile(memberEditRequest);
        return ResponseEntity.ok(ResultResponse.of(MEMBER_EDIT_SUCCESS));
    }

    /**
     * 멤버 탈퇴
     */
    @DeleteMapping("")
    public ResponseEntity<ResultResponse> deleteMember() {
        memberService.deleteMember();
        return ResponseEntity.ok(ResultResponse.of(MEMBER_DELETE_SUCCESS));
    }

}
