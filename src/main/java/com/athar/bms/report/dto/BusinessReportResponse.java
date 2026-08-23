package com.athar.bms.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessReportResponse {

    private BigDecimal totalSales;
    private BigDecimal totalPurchases;
    private BigDecimal totalExpenses;

    private BigDecimal totalCustomerPayments;
    private BigDecimal totalSupplierPayments;

    private BigDecimal netProfit;
}