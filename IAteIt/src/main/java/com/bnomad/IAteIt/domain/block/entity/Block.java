package com.bnomad.IAteIt.domain.block.entity;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;


@Getter
@Entity
public class Block extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "block_id")
    private Long id;

    @Builder
    public Block(Member blocking_member, Member blocked_member) {
        this.blocking_member = blocking_member;
        this.blocked_member = blocked_member;
    }

    public Block() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member blocking_member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_member_id")
    private Member blocked_member;


}
