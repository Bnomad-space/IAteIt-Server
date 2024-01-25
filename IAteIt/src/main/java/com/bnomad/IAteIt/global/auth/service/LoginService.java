package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.repository.LoginRepository;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;
    private final LoginRepository loginRepository;

    // TODO: MultipartFile S3에 이미지로 올리고, URL 반환해서 MemberEntity에 넣어야 함
    public Member joinMember(JoinRequestDto request) {
        Long memberId = jwtUtil.currentMemberId();
        Member findMember = loginRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        findMember.join(request);
        return findMember;
    }

}
