package com.bnomad.IAteIt.domain.report.service;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.report.entity.Report;
import com.bnomad.IAteIt.domain.report.entity.dto.ReportRequestDto;
import com.bnomad.IAteIt.domain.report.entity.dto.ReportResponseDto;
import com.bnomad.IAteIt.domain.report.repository.ReportRepository;
import com.bnomad.IAteIt.global.error.custom.EntityNotFoundException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    /**
     * Util
     */
    private final JwtUtil jwtUtil;

    /**
     * Repository
     */
    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;
    private final ReportRepository reportRepository;

    public ReportResponseDto report(ReportRequestDto reportRequestDto) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        Meal meal = mealRepository.findById(reportRequestDto.getMealId())
                .orElseThrow(() -> new EntityNotFoundException(MEAL_NOT_FOUND));

        Report report = new Report(member, meal, reportRequestDto.getReason());
        Report savedReport = reportRepository.save(report);
        return new ReportResponseDto(savedReport);

    }
}
