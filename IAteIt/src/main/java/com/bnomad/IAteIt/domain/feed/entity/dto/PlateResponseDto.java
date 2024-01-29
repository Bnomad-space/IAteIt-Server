package com.bnomad.IAteIt.domain.feed.entity.dto;

import com.bnomad.IAteIt.domain.plate.entity.Plate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlateResponseDto {

    private LocalDateTime createdDateTime;
    private String imgaeUrl;

    public PlateResponseDto(Plate plate) {
        this.createdDateTime = plate.getCreatedDateTime();
        this.imgaeUrl = plate.getImageUrl();
    }
}
