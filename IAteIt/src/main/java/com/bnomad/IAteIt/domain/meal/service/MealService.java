package com.bnomad.IAteIt.domain.meal.service;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealCreateDto;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.plate.service.PlateService;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MealService {

    /**
     * Util
     */

    private final JwtUtil jwtUtil;
    private final S3Uploader s3Uploader;

    /**
     * Service
     */

    private final PlateService plateService;

    /**
     * Repository
     */
    private final MemberRepository memberRepository;
    private final MealRepository mealRepository;


    public void createMeal(MealCreateDto mealCreateDto) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new BusinessException());

        String url = s3Uploader.imageUpload(mealCreateDto.getImage(), AwsConstant.PLATE_IMAGE_DIR);

        Meal meal = new Meal(mealCreateDto, member);
        Meal savedMeal = mealRepository.save(meal);

        plateService.createPlate(savedMeal, url);
    }



}
