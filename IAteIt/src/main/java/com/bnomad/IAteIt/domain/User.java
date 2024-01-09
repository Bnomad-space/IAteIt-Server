package com.bnomad.IAteIt.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Meal> meals = new ArrayList<>();

    private String nickname;
    private String profileImage;




}
