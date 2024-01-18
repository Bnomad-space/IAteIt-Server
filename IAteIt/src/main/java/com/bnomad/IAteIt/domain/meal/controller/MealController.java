package com.bnomad.IAteIt.domain.meal.controller;


import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meal")
public class MealController {

    @PostMapping("")
    public ResponseEntity<ResultResponse> createMeal() {
        System.out.println("create api called");
        return ResponseEntity.ok(ResultResponse.of(MEAL_CREATE_SUCCESS, "sdf"));
    }
}
