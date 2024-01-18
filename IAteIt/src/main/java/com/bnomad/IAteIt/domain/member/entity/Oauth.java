package com.bnomad.IAteIt.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Entity
@Getter
public class Oauth {

    @Id
    @GeneratedValue
    @Column(name = "oauth_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    private String accessToken;
    private String refreshToken;

}
