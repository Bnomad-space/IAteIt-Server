package com.bnomad.IAteIt.domain.block.entity;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Block extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member blocking_member;


    private Long blockedMemberId;

}
