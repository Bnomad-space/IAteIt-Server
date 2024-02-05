package com.bnomad.IAteIt.domain.member.service;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.block.repository.BlockRepository;
import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.comment.repository.CommentRepository;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberEditRequest;
import com.bnomad.IAteIt.domain.member.entity.dto.MemberProfileDto;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.plate.repository.PlateRepository;
import com.bnomad.IAteIt.domain.report.entity.Report;
import com.bnomad.IAteIt.domain.report.repository.ReportRepository;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.global.error.BusinessException;

import com.bnomad.IAteIt.global.error.custom.EntityNotFoundException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    /**
     * Util
     */
    private final JwtUtil jwtUtil;
    private final S3Uploader s3Uploader;

    /**
     * Repository
     */
    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;
    private final PlateRepository plateRepository;
    private final CommentRepository commentRepository;
    private final BlockRepository blockRepository;
    private final ReportRepository reportRepository;


    @Transactional(readOnly = true)
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public MemberProfileDto getMemberProfile() {
        Long memberId = jwtUtil.currentMemberId();
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));
        return new MemberProfileDto(findMember);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public void editProfile(MemberEditRequest memberEditRequest) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member findMember = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));
        String url = "";
        if (memberEditRequest.getProfileImage() != null) {
            s3Uploader.deleteImage(findMember.getProfileImage());
            url = s3Uploader.imageUpload(memberEditRequest.getProfileImage(), AwsConstant.PROFILE_IMAGE_DIR);
        }
        findMember.edit(memberEditRequest.getNickname(), url);
    }

    public void deleteMember() {
        // report -> comment -> plate (s3 이미지 삭제) -> meal -> block -> member (s3 이미지 삭제)
        Long currentMemberId = jwtUtil.currentMemberId();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        // report
        List<Report> reports = reportRepository.findAllByMemberId(currentMemberId);
        reportRepository.deleteAll(reports);

        // comment
        List<Comment> comments = commentRepository.findAllByFromMemberId(currentMemberId);
        commentRepository.deleteAll(comments);

        // plate 다 삭제하면, meal은 plate 다 삭제하고 삭제
        List<Meal> meals = mealRepository.findAllByMemberId(currentMemberId);

        for (Meal meal : meals) {
            List<Plate> plates = plateRepository.findAllByMealId(meal.getId());
            for (Plate plate : plates) {
                s3Uploader.deleteImage(plate.getImageUrl());
            }
            plateRepository.deleteAll(plates);
            mealRepository.delete(meal);
        }

        // block
        List<Block> blocks = blockRepository.findAllByBlockingMemberId(currentMemberId);
        blockRepository.deleteAll(blocks);

        // member
        s3Uploader.deleteImage(member.getProfileImage());
        memberRepository.delete(member);
    }

}
