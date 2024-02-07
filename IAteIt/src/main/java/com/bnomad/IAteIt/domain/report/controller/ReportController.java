package com.bnomad.IAteIt.domain.report.controller;

import com.bnomad.IAteIt.domain.report.entity.dto.ReportRequestDto;
import com.bnomad.IAteIt.domain.report.entity.dto.ReportResponseDto;
import com.bnomad.IAteIt.domain.report.service.ReportService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.REPORT_CREATE_SUCCESS;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController implements ReportControllerSwagger {

    private final ReportService reportService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> report(@RequestBody ReportRequestDto reportRequestDto) {
        ReportResponseDto reportResponseDto = reportService.report(reportRequestDto);
        return ResponseEntity.ok(ResultResponse.of(REPORT_CREATE_SUCCESS, reportResponseDto));
    }


}
