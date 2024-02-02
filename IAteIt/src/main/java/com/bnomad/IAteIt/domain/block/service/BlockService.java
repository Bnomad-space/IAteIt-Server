package com.bnomad.IAteIt.domain.block.service;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockedMemberResponse;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockingMemberRequest;
import com.bnomad.IAteIt.domain.block.repository.BlockRepository;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.error.custom.EntityAlreadyExistException;
import com.bnomad.IAteIt.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.bnomad.IAteIt.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Service
public class BlockService {

    /**
     * util
     */
    private final JwtUtil jwtUtil;

    /**
     * Repository
     */
    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;


    public List<BlockedMemberResponse> blockedMemberList() {
        Long currentMemberId = jwtUtil.currentMemberId();

        List<Block> blockedMemberList = blockRepository.findAllByBlockingMemberId(currentMemberId);
        ArrayList<BlockedMemberResponse> blockedMemberResponses = new ArrayList<>();
        for (Block block : blockedMemberList) {
            blockedMemberResponses.add(new BlockedMemberResponse(block));
        }
        return blockedMemberResponses;
    }

    public BlockedMemberResponse block(BlockingMemberRequest blockingMemberRequest) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Member blockingMember = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
        Member blockedMember = memberRepository.findById(blockingMemberRequest.getBlockedMemberId())
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        List<Block> allByBlockingMemberId = blockRepository.findAllByBlockingMemberId(blockingMember.getId());
        if ((allByBlockingMemberId.stream().filter(findMember -> (findMember.getBlockedMember().getId() == blockedMember.getId())).count() != 0L)) {
            throw new EntityAlreadyExistException(BLOCKED_ALREADY);
        }

        Block block = Block.builder()
                .blockingMember(blockingMember)
                .blockedMember(blockedMember)
                .build();
        blockRepository.save(block);
        return new BlockedMemberResponse(block);
    }

    public void unblock(Long blockedMemberId) {
        Long currentMemberId = jwtUtil.currentMemberId();
        Block block = blockRepository.findByBlockingMemberIdAndBlockedMemberId(currentMemberId, blockedMemberId);

        blockRepository.delete(block);
    }
}
