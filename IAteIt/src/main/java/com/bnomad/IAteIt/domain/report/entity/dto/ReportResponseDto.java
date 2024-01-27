package com.bnomad.IAteIt.domain.report.entity.dto;

import com.bnomad.IAteIt.domain.report.entity.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReportResponseDto {

    private Long reportId;
    private Long memberId;
    private String reason;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public ReportResponseDto(Report report) {
        this.reportId = report.getId();
        this.memberId = report.getMember().getId();
        this.reason = report.getReason();
        this.createdDateTime = report.getCreatedDateTime();
        this.modifiedDateTime = report.getModifiedDateTime();
    }

}
