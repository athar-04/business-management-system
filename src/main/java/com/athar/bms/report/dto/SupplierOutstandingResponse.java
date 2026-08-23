package com.athar.bms.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierOutstandingResponse {

    private Long supplierId;
    private String supplierName;
    private BigDecimal totalPurchases;
    private BigDecimal totalPaid;
    private BigDecimal outstandingAmount;
}