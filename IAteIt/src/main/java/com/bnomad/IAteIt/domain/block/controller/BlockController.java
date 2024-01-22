package com.bnomad.IAteIt.domain.block.controller;

import com.bnomad.IAteIt.domain.block.entity.dto.BlockedMemberResponse;
import com.bnomad.IAteIt.domain.block.entity.dto.BlockingMemberRequest;
import com.bnomad.IAteIt.domain.block.service.BlockService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bnomad.IAteIt.global.result.ResultCode.BLOCKED_MEMBER_FIND_SUCCESS;
import static com.bnomad.IAteIt.global.result.ResultCode.BLOCK_MEMBER_SUCCESS;

@RestController
@RequestMapping("/api/v1/block")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;
    private final HttpSession httpSession;

    /**
     * 멤버 차단
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> blockMember(@RequestBody BlockingMemberRequest blockingMemberRequest) {
        blockService.blockMember(blockingMemberRequest);
        return ResponseEntity.ok(ResultResponse.of(BLOCK_MEMBER_SUCCESS));
    }

    /**
     * 멤버 차단 목록 확인
     * httpSession으로부터 멤버 정보를 가져옴
     */
    @GetMapping("/list")
    public ResponseEntity<ResultResponse> blockedMemberList(@RequestParam("memberId") Long memberId) {
        System.out.println("memberId = " + memberId);
        List<BlockedMemberResponse> blockedMemberList = blockService.blockedMemberList(memberId);
        return ResponseEntity.ok(ResultResponse.of(BLOCKED_MEMBER_FIND_SUCCESS, blockedMemberList));
    }

}
