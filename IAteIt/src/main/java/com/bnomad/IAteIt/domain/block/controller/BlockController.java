package com.bnomad.IAteIt.domain.block.controller;

import com.bnomad.IAteIt.domain.block.entity.dto.BlockedMemberResponse;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockingMemberRequest;
import com.bnomad.IAteIt.domain.block.service.BlockService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/block")
@RequiredArgsConstructor
public class BlockController implements BlockControllerSwagger {

    private final BlockService blockService;

    /**
     * 멤버 차단 목록 확인
     */
    @GetMapping("")
    public ResponseEntity<ResultResponse> blockedMemberList() {
        List<BlockedMemberResponse> blockedMemberList = blockService.blockedMemberList();
        return ResponseEntity.ok(ResultResponse.of(BLOCKED_MEMBER_FIND_SUCCESS, blockedMemberList));
    }

    /**
     * 멤버 차단
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> blockMember(@RequestBody BlockingMemberRequest blockingMemberRequest) {
        BlockedMemberResponse blockedMemberResponse = blockService.block(blockingMemberRequest);
        return ResponseEntity.ok(ResultResponse.of(BLOCK_MEMBER_SUCCESS, blockedMemberResponse));
    }

    /**
     * 멤버 차단 해제
     */
    @DeleteMapping("")
    public ResponseEntity<ResultResponse> unblock(@RequestParam("blockedMemberId") Long blockedMemberId) {
        blockService.unblock(blockedMemberId);
        return ResponseEntity.ok(ResultResponse.of(UNBLOCK_MEMBER_SUCCESS));
    }

}
