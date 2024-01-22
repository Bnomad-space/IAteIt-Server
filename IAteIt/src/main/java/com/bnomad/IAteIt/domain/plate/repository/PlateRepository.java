package com.bnomad.IAteIt.domain.plate.repository;

import com.bnomad.IAteIt.domain.plate.entity.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Long> {

}
