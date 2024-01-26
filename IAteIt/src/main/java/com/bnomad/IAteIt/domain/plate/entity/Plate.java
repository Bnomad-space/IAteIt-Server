package com.bnomad.IAteIt.domain.plate.entity;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter

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

    public Plate() {
    }

    public Plate(Meal meal, String url) {
        this.meal = meal;
        this.imageUrl = url;
    }
}
