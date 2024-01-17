package com.bnomad.IAteIt.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Oauth {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String accessToken;
    private String refreshToken;

}
