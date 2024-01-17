package com.bnomad.IAteIt.domain;

import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
@Entity
public class Plate extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "plate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private String imageUrl;

}
