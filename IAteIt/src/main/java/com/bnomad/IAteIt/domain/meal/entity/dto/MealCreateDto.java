package com.bnomad.IAteIt.domain.meal.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MealCreateDto {

    private String caption;
    private String location;
    private MultipartFile image;


}
