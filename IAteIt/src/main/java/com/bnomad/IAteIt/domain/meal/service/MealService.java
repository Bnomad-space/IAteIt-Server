package com.bnomad.IAteIt.domain.meal.service;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealCreateDto;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealEditDto;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.domain.plate.service.PlateService;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.global.error.custom.EntityNotFoundException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;


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
        if (mealCreateDto.getImage() == null) {
            throw new RuntimeException("이미지가 포함되어 있지 않습니다.");
        }
        Long currentMemberId = jwtUtil.currentMemberId();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        String url = s3Uploader.imageUpload(mealCreateDto.getImage(), AwsConstant.PLATE_IMAGE_DIR);

        Meal meal = new Meal(mealCreateDto, member);
        Meal savedMeal = mealRepository.save(meal);

        plateService.createPlate(savedMeal, url);
    }

    public void editMeal(MealEditDto mealEditDto) {
        Meal findMeal = mealRepository.findById(mealEditDto.getMealId())
                .orElseThrow(() -> new EntityNotFoundException(MEAL_NOT_FOUND));

        findMeal.edit(mealEditDto);
    }

    public void deleteMeal(Long mealId) {
        plateService.deleteAllByMealId(mealId);
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException(MEAL_NOT_FOUND));
        mealRepository.delete(meal);
    }



}
