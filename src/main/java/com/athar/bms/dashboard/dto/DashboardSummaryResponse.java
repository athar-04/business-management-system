package com.athar.bms.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryResponse {

    private BigDecimal totalSales;
    private BigDecimal totalPurchases;
    private BigDecimal totalExpenses;
    private BigDecimal totalReceivables;
    private BigDecimal totalPayables;

    private Long totalProducts;
    private Long totalGodowns;
    private Long totalInventoryQuantity;
}