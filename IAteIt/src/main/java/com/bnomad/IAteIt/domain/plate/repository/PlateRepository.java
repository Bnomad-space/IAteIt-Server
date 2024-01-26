package com.bnomad.IAteIt.domain.plate.repository;

import com.bnomad.IAteIt.domain.plate.entity.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Long> {

    List<Plate> findAllByMealId(Long id);

}
