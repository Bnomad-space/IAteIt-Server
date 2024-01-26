package com.bnomad.IAteIt.domain.plate.service;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.plate.repository.PlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlateService {

    private final PlateRepository plateRepository;

    public Plate createPlate(Meal meal, String url) {
        Plate plate = new Plate(meal, url);

        return plateRepository.save(plate);
    }




}
