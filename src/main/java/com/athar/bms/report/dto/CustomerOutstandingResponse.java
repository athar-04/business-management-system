package com.athar.bms.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOutstandingResponse {

    private Long customerId;
    private String customerName;
    private BigDecimal totalSales;
    private BigDecimal totalPaid;
    private BigDecimal outstandingAmount;
}