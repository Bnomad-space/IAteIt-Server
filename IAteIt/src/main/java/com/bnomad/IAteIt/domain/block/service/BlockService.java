package com.bnomad.IAteIt.domain.block.service;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.block.repository.BlockRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockService {

    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;

    public void blockMember(Long blockingId, Long blockedId) {
        Member blockingMember = memberRepository.findById(blockingId)
                .orElseThrow(() -> new RuntimeException());
        Member blockedMember = memberRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException());

        Block block = Block.builder()
                .blocking_member(blockingMember)
                .blocked_member(blockedMember)
                .build();
        blockRepository.save(block);
    }

}
