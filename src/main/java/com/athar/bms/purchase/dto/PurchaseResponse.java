package com.athar.bms.purchase.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponse {

    private Long id;

    private Long supplierId;

    private String supplierName;

    private Long godownId;

    private String godownName;

    private LocalDate purchaseDate;

    private BigDecimal totalAmount;

    private List<PurchaseItemResponse> items;
}