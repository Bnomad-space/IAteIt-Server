package com.bnomad.IAteIt.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class LoginInfo {

    @Id
    @GeneratedValue
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
    private Long memberId;

    private String accessToken;
    private String refreshToken;

}
