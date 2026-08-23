package com.athar.bms.report.controller;

import com.athar.bms.report.dto.BusinessReportResponse;
import com.athar.bms.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/business")
    public BusinessReportResponse getBusinessReport() {
        return reportService.getBusinessReport();
    }
}