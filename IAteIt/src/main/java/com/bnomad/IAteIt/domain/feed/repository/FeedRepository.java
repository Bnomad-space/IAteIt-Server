package com.bnomad.IAteIt.domain.feed.repository;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Meal, Long> {

    Page<Meal> findAll(Pageable pageable);

}
