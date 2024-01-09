package com.bnomad.IAteIt.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
public class Meal {

    @Id
    @GeneratedValue
    @Column(name = "meal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "meal")
    private List<Plate> plates = new ArrayList<>();

    @OneToMany(mappedBy = "meal")
    private List<Comment> comments = new ArrayList<>();


    private String caption;

    private String location;


}
