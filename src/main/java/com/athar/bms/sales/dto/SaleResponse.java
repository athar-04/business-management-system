package com.athar.bms.sales.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponse {

    private Long id;

    private Long customerId;

    private String customerName;

    private Long godownId;

    private String godownName;

    private LocalDate saleDate;

    private BigDecimal totalAmount;

    private List<SaleItemResponse> items;
}