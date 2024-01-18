package com.bnomad.IAteIt.domain.block.controller;

import com.bnomad.IAteIt.domain.block.service.BlockService;
import com.bnomad.IAteIt.global.result.ResultCode;
import com.bnomad.IAteIt.global.result.ResultResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/block")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> blockUser(HttpServletRequest request) {
        Long blockingId = Long.parseLong(request.getParameter("blockingId"));
        Long blockedId = Long.parseLong(request.getParameter("blockedId"));
        System.out.println("blockedId = " + blockedId + " " +  blockingId);

        blockService.blockMember(blockingId, blockedId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.MEMBER_FIND_SUCCESS));

    }

}
