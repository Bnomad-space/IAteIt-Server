package com.bnomad.IAteIt.domain.block.service;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockedMemberResponse;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockingMemberRequest;
import com.bnomad.IAteIt.domain.block.repository.BlockRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BlockService {

    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;

    // TODO: 같은 유저를 차단하는 중복 처리 필요
    public void blockMember(BlockingMemberRequest blockingMemberRequest) {
        Member blockingMember = memberRepository.findById(blockingMemberRequest.getBlockingMemberId())
                .orElseThrow(() -> new RuntimeException("blocking 멤버 id가 없습니다."));
        Member blockedMember = memberRepository.findById(blockingMemberRequest.getBlockedMemberId())
                .orElseThrow(() -> new RuntimeException("blocked 멤버 id가 없습니다"));

        Block block = Block.builder()
                .blockingMember(blockingMember)
                .blockedMember(blockedMember)
                .build();
        blockRepository.save(block);
    }

    public List<BlockedMemberResponse> blockedMemberList(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("유효한 멤버 id가 아닙니다."));
        List<Block> blockedMemberList = blockRepository.findAllByBlockingMemberId(findMember.getId());
        ArrayList<BlockedMemberResponse> temp = new ArrayList<>();
        for (Block block : blockedMemberList) {
            temp.add(new BlockedMemberResponse(block.getBlockedMember()));
        }
        return temp;
    }
}
