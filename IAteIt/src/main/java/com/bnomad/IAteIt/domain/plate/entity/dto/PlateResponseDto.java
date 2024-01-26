package com.bnomad.IAteIt.domain.plate.entity.dto;

import com.bnomad.IAteIt.domain.plate.entity.Plate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateResponseDto {

    private Long plateId;
    private Long mealId;
    private String imageUrl;

    public PlateResponseDto(Plate plate) {
        this.plateId = plate.getId();
        this.mealId = plate.getMeal().getId();
        this.imageUrl = plate.getImageUrl();
    }
}
