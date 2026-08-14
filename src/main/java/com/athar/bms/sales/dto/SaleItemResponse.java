package com.athar.bms.sales.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal sellingPrice;

    private BigDecimal subtotal;
}