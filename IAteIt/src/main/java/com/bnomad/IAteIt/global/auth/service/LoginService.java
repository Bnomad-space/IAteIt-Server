package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    // TODO: MultipartFile S3에 이미지로 올리고, URL 반환해서 MemberEntity에 넣어야 함
    public Member joinMember(JoinRequestDto request) {
        Member findMember = loginRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("해당 id의 멤버가 없습니다. 다시 로그인해주세요"));

        findMember.join(request);
        return findMember;
    }

}
