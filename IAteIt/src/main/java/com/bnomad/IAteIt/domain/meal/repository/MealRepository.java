package com.bnomad.IAteIt.domain.meal.repository;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
