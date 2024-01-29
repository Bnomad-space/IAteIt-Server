package com.bnomad.IAteIt.domain.feed.service;

import com.bnomad.IAteIt.domain.feed.entity.dto.MealResponseDto;
import com.bnomad.IAteIt.domain.feed.entity.dto.PlateResponseDto;
import com.bnomad.IAteIt.domain.feed.repository.FeedRepository;
import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.plate.entity.Plate;
import com.bnomad.IAteIt.domain.plate.repository.PlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedService {

    private final FeedRepository feedRepository;
    private final PlateRepository plateRepository;

    public List<MealResponseDto> getFeedWithPage(int pageNum) {
        Pageable createdDateTime = PageRequest.of(pageNum, 5, Sort.by(Sort.Direction.DESC, "createdDateTime"));
        Page<Meal> meals = feedRepository.findAll(createdDateTime);
        List<MealResponseDto> tempMeals = new ArrayList<>();

        for (Meal meal : meals) {
            List<Plate> plates = plateRepository.findAllByMealId(meal.getId());
            List<PlateResponseDto> tempPlates = new ArrayList<>();

            // 하루보다 이전의 데이터는 가져오지 않음
            // 쿼리로 할수도 있고, 가져온 데이터에서 return도 가능
            // TODO: 방식의 고민이 필요해보임
            if (meal.getCreatedDateTime().isBefore(LocalDateTime.now().minusDays(1))) {
                break;
            }
            for (Plate plate : plates) {
                PlateResponseDto plateResponseDto = new PlateResponseDto(plate);
                tempPlates.add(plateResponseDto);
            }

            MealResponseDto mealResponseDto = new MealResponseDto(meal, tempPlates);
            tempMeals.add(mealResponseDto);
        }

        return tempMeals;
    }
}
