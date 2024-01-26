package com.bnomad.IAteIt.domain.meal.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealEditDto {

    private Long mealId;
    private String caption;
    private String location;
}
