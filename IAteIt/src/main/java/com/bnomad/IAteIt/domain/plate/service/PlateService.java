package com.bnomad.IAteIt.domain.plate.service;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.meal.repository.MealRepository;
import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.plate.entity.dto.PlateAddDto;
import com.bnomad.IAteIt.domain.plate.entity.dto.PlateResponseDto;
import com.bnomad.IAteIt.domain.plate.repository.PlateRepository;
import com.bnomad.IAteIt.global.constant.AwsConstant;
import com.bnomad.IAteIt.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlateService {

    /**
     * Util
     */
    private final S3Uploader s3Uploader;

    /**
     * Repository
     */
    private final MealRepository mealRepository;
    private final PlateRepository plateRepository;

    public Plate createPlate(Meal meal, String url) {
        Plate plate = new Plate(meal, url);

        return plateRepository.save(plate);
    }

    public PlateResponseDto addPlate(PlateAddDto plateAddDto) {
        Long mealId = plateAddDto.getMealId();
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("해당 mealId가 없습니다"));
        String url = s3Uploader.imageUpload(plateAddDto.getImage(), AwsConstant.PLATE_IMAGE_DIR);
        Plate savedPlate = plateRepository.save(new Plate(meal, url));
        return new PlateResponseDto(savedPlate);
    }

    public void deleteAllByMealId(Long mealId) {
        List<Plate> plates = plateRepository.findAllByMealId(mealId);
        plateRepository.deleteAll(plates);
    }

}
