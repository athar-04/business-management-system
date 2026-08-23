package com.athar.bms.report.controller;

import com.athar.bms.report.dto.BusinessReportResponse;
import com.athar.bms.report.dto.CustomerOutstandingResponse;
import com.athar.bms.report.dto.SupplierOutstandingResponse;
import com.athar.bms.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/business")
    public BusinessReportResponse getBusinessReport() {
        return reportService.getBusinessReport();
    }

    @GetMapping("/customer-outstandings")
    public List<CustomerOutstandingResponse> getCustomerOutstandings() {
        return reportService.getCustomerOutstandings();
    }

    @GetMapping("/supplier-outstandings")
    public List<SupplierOutstandingResponse> getSupplierOutstandings() {
        return reportService.getSupplierOutstandings();
    }
}