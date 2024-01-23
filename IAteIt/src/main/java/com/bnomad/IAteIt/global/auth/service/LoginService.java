package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.provider.JwtProvider;
import com.bnomad.IAteIt.global.auth.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final JwtProvider jwtProvider;

    // TODO: MultipartFile S3에 이미지로 올리고, URL 반환해서 MemberEntity에 넣어야 함
    public String joinMember(JoinRequestDto request) {
        Member findMember = loginRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("해당 id의 멤버가 없습니다. 다시 로그인해주세요"));

        List<String> roleList = new ArrayList<>();
        roleList.add(findMember.getMemberRole().name());
        String token = jwtProvider.createToken(findMember.getEmail(), roleList);

        findMember.join(request);
        return token;
    }

}
