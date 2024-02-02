package com.bnomad.IAteIt.domain.feed.entity.dto;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealResponseDto {
    private Long mealId;
    private Long memberId;
    private String caption;
    private String location;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    private List<MealRelatedPlateResponseDto> plates = new ArrayList<>();

    public MealResponseDto(Meal meal, List<MealRelatedPlateResponseDto> plates) {
        this.mealId = meal.getId();
        this.memberId = meal.getMember().getId();
        this.caption = meal.getCaption();
        this.location = meal.getLocation();
        this.createdDateTime = meal.getCreatedDateTime();
        this.modifiedDateTime = meal.getModifiedDateTime();
        this.plates = plates;
    }

}
