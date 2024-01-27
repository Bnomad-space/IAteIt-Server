package com.bnomad.IAteIt.domain.report.entity;

import com.bnomad.IAteIt.domain.meal.entity.Meal;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Report extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Meal meal;

    private String reason;

    public Report() {
    }

    public Report(Member member, Meal meal, String reason) {
        this.member = member;
        this.meal = meal;
        this.reason = reason;

    }
}