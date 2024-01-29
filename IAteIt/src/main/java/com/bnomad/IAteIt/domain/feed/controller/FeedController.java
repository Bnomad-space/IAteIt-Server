package com.bnomad.IAteIt.domain.feed.controller;

import com.bnomad.IAteIt.domain.feed.entity.dto.MealResponseDto;
import com.bnomad.IAteIt.domain.feed.service.FeedService;
import com.bnomad.IAteIt.global.result.ResultCode;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping("")
    public ResponseEntity<ResultResponse> getRecent(@RequestParam("page") int pageNum) {
        List<MealResponseDto> feedWithPage = feedService.getFeedWithPage(pageNum);
        if (feedWithPage.size() == 0) {
            throw new RuntimeException("페이지가 없습니다");
        } else if (feedWithPage.size() != 5) {
            return ResponseEntity.ok(ResultResponse.of(FEED_DONE_SUCCESS, feedWithPage));
        } else {
            return ResponseEntity.ok(ResultResponse.of(FEED_REQUEST_SUCCESS, feedWithPage));
        }
    }
}
