package com.bnomad.IAteIt.domain.block.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Block {

    @Id
    @GeneratedValue
    @Column(name = "block_id")
    private Long id;

    private Long blockingMemberId;
    private Long blockedMemberId;

}
