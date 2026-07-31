package com.athar.bms.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String sku;

    private String description;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private String unit;

    private Integer minimumStock;

    private Boolean isActive;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;
}