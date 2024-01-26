package com.bnomad.IAteIt.domain.meal.controller;


import com.bnomad.IAteIt.domain.meal.entity.dto.MealCreateDto;
import com.bnomad.IAteIt.domain.meal.entity.dto.MealEditDto;
import com.bnomad.IAteIt.domain.meal.service.MealService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meal")
public class MealController {

    private final MealService mealService;


    /**
     * Meal 생성
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> createMeal(@ModelAttribute MealCreateDto mealCreateDto) {
        mealService.createMeal(mealCreateDto);

        return ResponseEntity.ok(ResultResponse.of(MEAL_CREATE_SUCCESS));
    }

    /**
     * Meal 수정
     */
    @PutMapping("")
    public ResponseEntity<ResultResponse> editMeal(@RequestBody MealEditDto mealEditDto) {
        mealService.editMeal(mealEditDto);

        return ResponseEntity.ok(ResultResponse.of(MEAL_EDIT_SUCCESS));
    }

    /**
     * Meal 삭제
     */
    @DeleteMapping("")
    public ResponseEntity<ResultResponse> deleteMeal(@RequestParam("mealId") Long mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.ok(ResultResponse.of(MEAL_DELETE_SUCCESS));
    }
}
