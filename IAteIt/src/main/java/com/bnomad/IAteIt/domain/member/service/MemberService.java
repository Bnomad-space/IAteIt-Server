package com.bnomad.IAteIt.domain.member.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.global.util.JwtUtil;
import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final JwtUtil jwtUtil;
    private final S3Uploader s3Uploader;

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public MemberProfileDto findById() {
        Long memberId = jwtUtil.currentMemberId();
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow();
        return new MemberProfileDto(findMember);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    /**
     * 멤버 정보 변경
     */
    public void editProfile(MemberEditRequest memberEditRequest) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member findMember = memberRepository.findById(currentMemberId)
                .orElseThrow();
        String url = "";
        if (memberEditRequest.getProfileImage() != null) {
            s3Uploader.deleteImage(findMember.getProfileImage());
            url = s3Uploader.imageUpload(memberEditRequest.getProfileImage(), AwsConstant.PROFILE_IMAGE_DIR);
        }
        findMember.edit(memberEditRequest.getNickname(), url);
    }

}
