package com.bnomad.IAteIt.domain.plate.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PlateAddDto {
    private Long mealId;
    private MultipartFile image;
}
