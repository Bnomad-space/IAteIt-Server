package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.global.auth.dto.JoinRequestDto;
import com.bnomad.IAteIt.global.auth.repository.LoginRepository;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.util.JwtUtil;

import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;
    private final LoginRepository loginRepository;
    private final S3Uploader s3Uploader;

    private static String PROFILE_DIR_PATH = "profileImage";


    // TODO: MultipartFile S3에 이미지로 올리고, URL 반환해서 MemberEntity에 넣어야 함
    public MemberProfileDto joinMember(JoinRequestDto request) {
        Long memberId = jwtUtil.currentMemberId();
        Member findMember = loginRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
        String url = s3Uploader.imageUpload(request.getProfileImage(), AwsConstant.PROFILE_IMAGE_DIR);
        findMember.join(request, url);
        return new MemberProfileDto(findMember);
    }

}
