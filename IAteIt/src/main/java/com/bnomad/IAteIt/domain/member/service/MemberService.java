package com.bnomad.IAteIt.domain.member.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.member.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public HashMap<String, String> join() {
        HashMap<String, String> returnToken = new HashMap<>();
        returnToken.put("access", "asfsdf");
        returnToken.put("refresh", "refresh nono");
        return returnToken;
    }

    @Transactional(readOnly = true)
    public Member findByNickname() {
        return memberRepository.findByNickname("user1");
    }

    /**
     * 멤버 정보 변경
     */
    public void editProfile(MemberEditRequest memberEditRequest) {
        Member findMember = memberRepository.findById(memberEditRequest.getMemberId())
                .orElseThrow();
        findMember.editProfile(memberEditRequest);
    }

}
