package com.athar.bms.report.service;

import com.athar.bms.report.dto.BusinessReportResponse;

import com.athar.bms.report.dto.CustomerOutstandingResponse;
import com.athar.bms.report.dto.SupplierOutstandingResponse;

import java.util.List;

public interface ReportService {

    BusinessReportResponse getBusinessReport();

    List<CustomerOutstandingResponse> getCustomerOutstandings();

    List<SupplierOutstandingResponse> getSupplierOutstandings();
}